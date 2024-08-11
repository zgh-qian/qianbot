package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.enums.oj.OjQuestionDifficultyEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.oj.domain.OjDetail;
import com.qian.qianbotbackend.model.oj.domain.OjQuestion;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailUpdateRequest;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionQueryRequest;
import com.qian.qianbotbackend.model.oj.vo.OjDetailVO;
import com.qian.qianbotbackend.model.oj.vo.OjQuestionDetailVO;
import com.qian.qianbotbackend.model.oj.vo.OjQuestionVO;
import com.qian.qianbotbackend.service.OjDetailService;
import com.qian.qianbotbackend.service.OjJudgecaseService;
import com.qian.qianbotbackend.service.OjQuestionService;
import com.qian.qianbotbackend.mapper.OjQuestionMapper;
import com.qian.qianbotbackend.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.qian.qianbotbackend.constant.OjConstant.*;

/**
 * @author qian
 * @description 针对表【ojquestion(题目表)】的数据库操作Service实现
 * @createDate 2024-07-27 11:02:57
 */
@Service
public class OjQuestionServiceImpl extends ServiceImpl<OjQuestionMapper, OjQuestion>
        implements OjQuestionService {
    @Resource
    private OjDetailService ojDetailService;

    @Resource
    private OjJudgecaseService ojJudgecaseService;

    @Override
    public OjQuestion validOjQuestion(OjQuestionDetailDTO ojQuestionDetailDTO, boolean isAdd) {
        Long id = ojQuestionDetailDTO.getId();
        String title = ojQuestionDetailDTO.getTitle();
        List<String> tags = ojQuestionDetailDTO.getTags();
        Integer difficulty = ojQuestionDetailDTO.getDifficulty();
        if (isAdd && ObjectUtil.isAllEmpty(title, tags, difficulty)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        } else if (!isAdd) {
            ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(title) && title.length() > OJ_QUESTION_TITLE_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_QUESTION_TITLE_LENGTH_TOO_LONG);
        }
        if (!tags.isEmpty() && tags.size() > OJ_TAGS_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_TAGS_LENGTH_TOO_LONG);
        }
        if (difficulty != null && OjQuestionDifficultyEnum.getEnumByValue(difficulty) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_QUESTION_DIFFICULTY_ERROR);
        }
        OjQuestion ojQuestion = BeanUtil.copyProperties(ojQuestionDetailDTO, OjQuestion.class);
        if (!tags.isEmpty()) {
            ojQuestion.setTags(JSONUtil.toJsonStr(tags));
        }
        return ojQuestion;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addOjQuestionDetail(OjQuestionDetailAddRequest ojQuestionDetailAddRequest) {
        ThrowUtils.throwIf(ojQuestionDetailAddRequest == null, ErrorCode.PARAMS_ERROR);
        OjQuestionDetailDTO ojQuestionDetailDTO = BeanUtil.copyProperties(ojQuestionDetailAddRequest, OjQuestionDetailDTO.class);
        OjQuestion ojQuestion = validOjQuestion(ojQuestionDetailDTO, true);
        OjDetail ojDetail = ojDetailService.validOjDetail(ojQuestionDetailDTO, true);
        Long userId = BaseContext.getUserId();
        ojQuestion.setUserId(userId);
        ThrowUtils.throwIf(!this.save(ojQuestion), ErrorCode.SYSTEM_ERROR);
        Long id = ojQuestion.getId();
        ojDetail.setQuestionId(id);
        ojDetail.setUserId(userId);
        ThrowUtils.throwIf(!ojDetailService.save(ojDetail), ErrorCode.SYSTEM_ERROR);
        return id;
    }

    @Override
    @Transactional
    public Boolean updateOjQuestionDetail(OjQuestionDetailUpdateRequest ojQuestionDetailUpdateRequest) {
        ThrowUtils.throwIf(ojQuestionDetailUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        // 查看题目是否存在
        OjQuestion oldOjQuestion = this.getById(ojQuestionDetailUpdateRequest.getId());
        ThrowUtils.throwIf(oldOjQuestion == null, ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        OjQuestionDetailDTO ojQuestionDetailDTO = BeanUtil.copyProperties(ojQuestionDetailUpdateRequest, OjQuestionDetailDTO.class);
        OjQuestion ojQuestion = validOjQuestion(ojQuestionDetailDTO, false);
        OjDetail ojDetail = ojDetailService.validOjDetail(ojQuestionDetailDTO, false);
        ThrowUtils.throwIf(!this.updateById(ojQuestion), ErrorCode.SYSTEM_ERROR);
        ojDetail.setQuestionId(ojQuestion.getId());
        ojDetail.setId(null);
        ojDetailService.updateOjDetailByQuestionId(ojDetail);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteOjQuestionDetail(Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        // 先删除判题用例
        ojJudgecaseService.deleteOjJudgecaseByQuestionId(id);
        // 再删除详情
        ojDetailService.deleteOjDetailByQuestionId(id);
        // 最后删除问题
        ThrowUtils.throwIf(!this.removeById(id), ErrorCode.SYSTEM_ERROR);
        return true;
    }

    @Override
    public Page<OjQuestion> getOjQuestionList(OjQuestionQueryRequest ojQuestionQueryRequest) {
        ThrowUtils.throwIf(ojQuestionQueryRequest == null, ErrorCode.PARAMS_ERROR);
        QueryWrapper<OjQuestion> queryWrapper = new QueryWrapper<>();
        String title = ojQuestionQueryRequest.getTitle();
        List<String> tags = ojQuestionQueryRequest.getTags();
        Integer difficulty = ojQuestionQueryRequest.getDifficulty();
        int current = ojQuestionQueryRequest.getCurrent();
        int pageSize = ojQuestionQueryRequest.getPageSize();
        String sortField = ojQuestionQueryRequest.getSortField();
        String sortOrder = ojQuestionQueryRequest.getSortOrder();
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        if (tags != null && !tags.isEmpty()) {
            tags.forEach(tag -> queryWrapper.like("tags", tag));
        }
        queryWrapper.eq(difficulty != null, "difficulty", difficulty);
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<OjQuestionVO> getOjQuestionVOList(OjQuestionQueryRequest ojQuestionQueryRequest) {
        Page<OjQuestion> ojQuestionList = getOjQuestionList(ojQuestionQueryRequest);
        Page<OjQuestionVO> ojQuestionVOList = BeanUtil.copyProperties(ojQuestionList, Page.class);
        List<OjQuestion> questionList = ojQuestionList.getRecords();
        if (!questionList.isEmpty()) {
            ojQuestionVOList.setRecords(questionList.stream().map(OjQuestionVO::objToVO).collect(Collectors.toList()));
        }
        return ojQuestionVOList;
    }

    @Override
    public OjQuestionDetailVO getOjQuestionDetail(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        OjQuestion ojQuestion = this.getById(id);
        ThrowUtils.throwIf(ojQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        OjDetail ojDetail = ojDetailService.getQjDetailByQuestionId(id);
        OjQuestionDetailVO ojQuestionDetailVO = new OjQuestionDetailVO();
        BeanUtils.copyProperties(OjQuestionVO.objToVO(ojQuestion), ojQuestionDetailVO);
        BeanUtils.copyProperties(OjDetailVO.objToVO(ojDetail), ojQuestionDetailVO);
        return ojQuestionDetailVO;
    }

    @Override
    public OjQuestionDetailDTO getOjQuestionDetailDTO(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        OjQuestion ojQuestion = this.getById(id);
        ThrowUtils.throwIf(ojQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        OjDetail ojDetail = ojDetailService.getQjDetailByQuestionId(id);
        OjQuestionDetailDTO ojQuestionDetailDTO = new OjQuestionDetailDTO();
        BeanUtils.copyProperties(OjQuestionVO.objToVO(ojQuestion), ojQuestionDetailDTO);
        BeanUtils.copyProperties(OjDetailVO.objToVO(ojDetail), ojQuestionDetailDTO);
        return ojQuestionDetailDTO;
    }
}




