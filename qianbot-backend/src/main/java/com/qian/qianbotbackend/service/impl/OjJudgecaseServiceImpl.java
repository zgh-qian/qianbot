package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.oj.domain.OjQuestion;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseQueryRequest;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseUpdateRequest;
import com.qian.qianbotbackend.model.oj.vo.OjJudgecaseVO;
import com.qian.qianbotbackend.service.OjJudgecaseService;
import com.qian.qianbotbackend.model.oj.domain.OjJudgecase;
import com.qian.qianbotbackend.mapper.OjJudgecaseMapper;
import com.qian.qianbotbackend.service.OjQuestionService;
import com.qian.qianbotbackend.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @description 针对表【ojjudgecase(题目判题用例表)】的数据库操作Service实现
 * @createDate 2024-07-27 11:02:57
 */
@Service
public class OjJudgecaseServiceImpl extends ServiceImpl<OjJudgecaseMapper, OjJudgecase>
        implements OjJudgecaseService {
    @Lazy
    @Resource
    private OjQuestionService ojQuestionService;

    @Override
    public void validOjJudgecase(OjJudgecase ojJudgecase, boolean isAdd) {
        Long id = ojJudgecase.getId();
        String input = ojJudgecase.getInput();
        String output = ojJudgecase.getOutput();
        Long questionId = ojJudgecase.getQuestionId();
        if (isAdd) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(input, output) || questionId == null, ErrorCode.PARAMS_ERROR);
        } else {
            ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    @Transactional
    public Boolean addOjJudgecase(OjJudgecaseAddRequest ojJudgecaseAddRequest) {
        ThrowUtils.throwIf(ojJudgecaseAddRequest == null, ErrorCode.PARAMS_ERROR);
        Long questionId = ojJudgecaseAddRequest.getQuestionId();
        // 校验问题是否存在
        ThrowUtils.throwIf(ojQuestionService.getById(questionId) == null, ErrorCode.PARAMS_ERROR);
        OjJudgecase ojJudgecase = new OjJudgecase();
        ojJudgecase.setInput(ojJudgecaseAddRequest.getInput());
        ojJudgecase.setOutput(ojJudgecaseAddRequest.getOutput());
        ojJudgecase.setQuestionId(questionId);
        validOjJudgecase(ojJudgecase, true);
        ojJudgecase.setUserId(BaseContext.getUserId());
        ThrowUtils.throwIf(!this.save(ojJudgecase), ErrorCode.PARAMS_ERROR);
        return true;
    }

    @Override
    public Boolean deleteOjJudgecase(Long id) {
        getOjJudgecaseById(id);
        ThrowUtils.throwIf(!this.removeById(id), ErrorCode.PARAMS_ERROR);
        return true;
    }

    @Override
    public Boolean deleteOjJudgecaseByQuestionId(Long questionId) {
        boolean remove = this.remove(Wrappers.lambdaQuery(OjJudgecase.class).eq(OjJudgecase::getQuestionId, questionId));
        ThrowUtils.throwIf(!remove, ErrorCode.PARAMS_ERROR);
        return true;
    }

    @Override
    public Boolean updateOjJudgecase(OjJudgecaseUpdateRequest ojJudgecaseUpdateRequest) {
        ThrowUtils.throwIf(ojJudgecaseUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        OjJudgecase ojJudgecase = BeanUtil.copyProperties(ojJudgecaseUpdateRequest, OjJudgecase.class);
        validOjJudgecase(ojJudgecase, false);
        ThrowUtils.throwIf(!this.updateById(ojJudgecase), ErrorCode.PARAMS_ERROR);
        return true;
    }

    @Override
    public Page<OjJudgecase> getOjJudgecaseList(OjJudgecaseQueryRequest ojJudgecaseQueryRequest) {
        ThrowUtils.throwIf(ojJudgecaseQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Long questionId = ojJudgecaseQueryRequest.getQuestionId();
        int current = ojJudgecaseQueryRequest.getCurrent();
        int pageSize = ojJudgecaseQueryRequest.getPageSize();
        String sortField = ojJudgecaseQueryRequest.getSortField();
        String sortOrder = ojJudgecaseQueryRequest.getSortOrder();
        QueryWrapper<OjJudgecase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(questionId != null, "questionId", questionId);
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<OjJudgecaseVO> getOjJudgecaseVOList(OjJudgecaseQueryRequest ojJudgecaseQueryRequest) {
        Page<OjJudgecase> ojJudgecaseList = getOjJudgecaseList(ojJudgecaseQueryRequest);
        Page<OjJudgecaseVO> ojJudgecaseVOPage = BeanUtil.copyProperties(ojJudgecaseList, Page.class);
        List<OjJudgecase> questionList = ojJudgecaseList.getRecords();
        if (!questionList.isEmpty()) {
            ojJudgecaseVOPage.setRecords(questionList.stream().map(OjJudgecaseVO::objToVO).collect(Collectors.toList()));
        }
        return ojJudgecaseVOPage;
    }

    @Override
    public OjJudgecase getOjJudgecaseById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        OjJudgecase ojJudgecase = this.getById(id);
        ThrowUtils.throwIf(ojJudgecase == null, ErrorCode.NOT_FOUND_ERROR);
        return ojJudgecase;
    }

    @Override
    public OjJudgecaseVO getOjJudgecaseVOById(Long id) {
        return OjJudgecaseVO.objToVO(this.getOjJudgecaseById(id));
    }

    @Override
    public List<OjJudgecase> getOjJudgecaseByQuestionId(Long questionId) {
        ThrowUtils.throwIf(questionId == null || questionId <= 0, ErrorCode.PARAMS_ERROR);
        QueryWrapper<OjJudgecase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionId", questionId);
        return this.list(queryWrapper);
    }
}




