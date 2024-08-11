package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.async.OjServiceAsync;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.oj.domain.OjQuestion;
import com.qian.qianbotbackend.model.oj.domain.OjSubmit;
import com.qian.qianbotbackend.model.oj.dto.OjSubmitAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjSubmitQueryRequest;
import com.qian.qianbotbackend.model.oj.vo.OjSubmitVO;
import com.qian.qianbotbackend.service.OjQuestionService;
import com.qian.qianbotbackend.service.OjSubmitService;
import com.qian.qianbotbackend.mapper.OjSubmitMapper;
import com.qian.qianbotbackend.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author qian
 * @description 针对表【ojsubmit(题目提交表)】的数据库操作Service实现
 * @createDate 2024-07-27 20:19:46
 */
@Service
public class OjSubmitServiceImpl extends ServiceImpl<OjSubmitMapper, OjSubmit>
        implements OjSubmitService {
    @Resource
    private OjQuestionService ojQuestionService;

    @Resource
    private OjServiceAsync ojServiceAsync;

    @Override
    public Long addOjSubmit(OjSubmitAddRequest ojSubmitAddRequest) {
        ThrowUtils.throwIf(ojSubmitAddRequest == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(StringUtils.isBlank(ojSubmitAddRequest.getLanguage()), ErrorCode.PARAMS_ERROR, "编程语言不能为空");
        ThrowUtils.throwIf(StringUtils.isBlank(ojSubmitAddRequest.getCode()), ErrorCode.PARAMS_ERROR, "代码不能为空");
        Long questionId = ojSubmitAddRequest.getQuestionId();
        ThrowUtils.throwIf(questionId == null, ErrorCode.PARAMS_ERROR, "问题id不能为空");
        ThrowUtils.throwIf(ojQuestionService.getById(questionId) == null, ErrorCode.PARAMS_ERROR, "问题不存在");
        OjSubmit ojSubmit = BeanUtil.copyProperties(ojSubmitAddRequest, OjSubmit.class);
        ojSubmit.setUserId(BaseContext.getUserId());
        ThrowUtils.throwIf(!this.save(ojSubmit), ErrorCode.SYSTEM_ERROR);
        // 异步判题
        ojServiceAsync.judge(ojSubmit.getId());
        return ojSubmit.getId();
    }

    @Override
    public Boolean deleteOjSubmit(Long id) {
        ThrowUtils.throwIf(!this.removeById(id), ErrorCode.SYSTEM_ERROR);
        return true;
    }

    @Override
    public Page<OjSubmit> getOjSubmitList(OjSubmitQueryRequest ojSubmitQueryRequest) {
        ThrowUtils.throwIf(ojSubmitQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = ojSubmitQueryRequest.getId();
        String language = ojSubmitQueryRequest.getLanguage();
        String status = ojSubmitQueryRequest.getStatus();
        String detail = ojSubmitQueryRequest.getDetail();
        Long timeUsed = ojSubmitQueryRequest.getTimeUsed();
        Long memoryUsed = ojSubmitQueryRequest.getMemoryUsed();
        Integer judgeStatus = ojSubmitQueryRequest.getJudgeStatus();
        Long questionId = ojSubmitQueryRequest.getQuestionId();
        int current = ojSubmitQueryRequest.getCurrent();
        int pageSize = ojSubmitQueryRequest.getPageSize();
        String sortField = ojSubmitQueryRequest.getSortField();
        String sortOrder = ojSubmitQueryRequest.getSortOrder();
        QueryWrapper<OjSubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(language != null, "language", language);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.like(detail != null, "detail", detail);
        queryWrapper.eq(timeUsed != null, "timeUsed", timeUsed);
        queryWrapper.eq(memoryUsed != null, "memoryUsed", memoryUsed);
        queryWrapper.eq(judgeStatus != null, "judgeStatus", judgeStatus);
        queryWrapper.eq(questionId != null, "questionId", questionId);
        queryWrapper.eq("userId", BaseContext.getUserId());
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<OjSubmitVO> getOjSubmitVOList(OjSubmitQueryRequest ojSubmitQueryRequest) {
        Page<OjSubmit> getOjSubmitList = getOjSubmitList(ojSubmitQueryRequest);
        Page<OjSubmitVO> ojSubmitVOPage = BeanUtil.copyProperties(getOjSubmitList, Page.class);
        ojSubmitVOPage.setRecords(getOjSubmitList.getRecords().stream().map(OjSubmitVO::objToVO).collect(Collectors.toList()));
        return ojSubmitVOPage;
    }

    @Override
    public OjSubmit getOjSubmit(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        OjSubmit ojSubmit = this.getById(id);
        ThrowUtils.throwIf(ojSubmit == null, ErrorCode.NOT_FOUND_ERROR);
        return ojSubmit;
    }

    @Override
    public OjSubmitVO getOjSubmitVO(Long id) {
        return OjSubmitVO.objToVO(getOjSubmit(id));
    }
}




