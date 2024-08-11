package com.qian.qianbotbackend.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.async.AppServiceAsync;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.enums.app.AppAnswerStatusEnum;
import com.qian.qianbotbackend.enums.app.AppReviewStatsEnum;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppanswerAddRequest;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppanswerQueryRequest;
import com.qian.qianbotbackend.model.app.vo.AppAnswerCountVO;
import com.qian.qianbotbackend.model.app.vo.AppAnswerResultNameCountVO;
import com.qian.qianbotbackend.model.app.vo.AppanswerVO;
import com.qian.qianbotbackend.service.AppService;
import com.qian.qianbotbackend.service.AppanswerService;
import com.qian.qianbotbackend.mapper.AppanswerMapper;
import com.qian.qianbotbackend.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @description 针对表【appanswer(用户答题记录)】的数据库操作Service实现
 * @createDate 2024-07-07 15:27:31
 */
@Service
@Slf4j
public class AppanswerServiceImpl extends ServiceImpl<AppanswerMapper, Appanswer>
        implements AppanswerService {
    @Resource
    private AppService appService;

    @Resource
    private AppServiceAsync appServiceAsync;

    @Override
    public Long addAppAnswer(AppanswerAddRequest appanswerAddRequest) {
        ThrowUtils.throwIf(appanswerAddRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = appanswerAddRequest.getId();
        Long appId = appanswerAddRequest.getAppId();
        List<Long> userAnswer = appanswerAddRequest.getUserAnswer();
        // 校验app
        App app = appService.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!app.getReviewStatus().equals(AppReviewStatsEnum.REVIEW_STATS_ENUM_PASS.getValue()), ErrorCode.FORBIDDEN_ERROR, "应用待审核");
        ThrowUtils.throwIf(userAnswer.isEmpty(), ErrorCode.PARAMS_ERROR);
        Appanswer appanswer = new Appanswer();
        appanswer.setId(id);
        appanswer.setAppId(appId);
        appanswer.setUserAnswer(JSONUtil.toJsonStr(userAnswer));
        appanswer.setResultStatus(AppAnswerStatusEnum.WAITING.getValue());
        appanswer.setUserId(BaseContext.getUserId());
        boolean save = this.save(appanswer);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        // 异步判题
        AppAnswerDTO appAnswerDTO = new AppAnswerDTO();
        appAnswerDTO.setId(id);
        appAnswerDTO.setAppId(appId);
        appAnswerDTO.setUserAnswer(userAnswer);
        appAnswerDTO.setApp(app);
        appServiceAsync.asyncUpdateAppAnswer(appAnswerDTO);
        return appanswer.getId();
    }

    @Override
    public Boolean deleteAppAnswer(Long id) {
        getAppAnswerById(id);
        boolean remove = this.removeById(id);
        ThrowUtils.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean deleteAppAnswerByAppId(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        boolean remove = this.remove(new QueryWrapper<Appanswer>().eq("appId", appId));
        ThrowUtils.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Appanswer getAppAnswerById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        Appanswer appanswer = this.getById(id);
        ThrowUtils.throwIf(appanswer == null, ErrorCode.NOT_FOUND_ERROR);
        return appanswer;
    }

    @Override
    public AppanswerVO getAppAnswerVOById(Long id) {
        Appanswer appanswer = getAppAnswerById(id);
        Integer resultStatus = appanswer.getResultStatus();
        ThrowUtils.throwIf(resultStatus.equals(AppAnswerStatusEnum.WAITING.getValue()), ErrorCode.OPERATION_ERROR, "待判题");
        ThrowUtils.throwIf(resultStatus.equals(AppAnswerStatusEnum.JUDGING.getValue()), ErrorCode.OPERATION_ERROR, "判题中");
        AppanswerVO appanswerVO = AppanswerVO.objToVO(appanswer);
        appanswerVO.setAppName(appService.getAppVOById(appanswer.getAppId()).getAppName());
        return appanswerVO;
    }

    @Override
    public Page<Appanswer> getAppAnswerPage(AppanswerQueryRequest appanswerQueryRequest) {
        ThrowUtils.throwIf(appanswerQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = appanswerQueryRequest.getId();
        Long appId = appanswerQueryRequest.getAppId();
        Integer resultStatus = appanswerQueryRequest.getResultStatus();
        String resultName = appanswerQueryRequest.getResultName();
        String resultDesc = appanswerQueryRequest.getResultDesc();
        Integer resultScore = appanswerQueryRequest.getResultScore();
        String searchText = appanswerQueryRequest.getSearchText();
        int current = appanswerQueryRequest.getCurrent();
        int pageSize = appanswerQueryRequest.getPageSize();
        String sortField = appanswerQueryRequest.getSortField();
        String sortOrder = appanswerQueryRequest.getSortOrder();
        QueryWrapper<Appanswer> queryWrapper = new QueryWrapper<>();
        // 模糊查询
        queryWrapper.like(StringUtils.isNotBlank(resultName), "resultName", resultName);
        queryWrapper.like(StringUtils.isNotBlank(resultDesc), "resultDesc", resultDesc);
        // 精确查询
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(resultStatus), "resultStatus", resultStatus);
        queryWrapper.eq(ObjectUtils.isNotEmpty(resultScore), "resultScore", resultScore);
        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("resultName", searchText).or().like("resultDesc", searchText));
        }
        queryWrapper.eq("userId", BaseContext.getUserId());
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<AppanswerVO> getAppAnswerPageVO(AppanswerQueryRequest appanswerQueryRequest) {
        Page<Appanswer> appAnswerPage = getAppAnswerPage(appanswerQueryRequest);
        Page<AppanswerVO> appAnswerVOPage = new Page<>();
        BeanUtils.copyProperties(appAnswerPage, appAnswerVOPage);
        appAnswerVOPage.setRecords(appAnswerPage.getRecords().stream().map(appanswer -> {
            AppanswerVO appanswerVO = AppanswerVO.objToVO(appanswer);
            appanswerVO.setAppName(appService.getAppVOById(appanswer.getAppId()).getAppName());
            return appanswerVO;
        }).collect(Collectors.toList()));
        return appAnswerVOPage;
    }

    @Override
    public List<AppAnswerCountVO> getAppAnswerCount() {
        return null;
    }

    @Override
    public List<AppAnswerResultNameCountVO> getAppResultCount(Long appId) {
        return null;
    }
}




