package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.oj.domain.OjDetail;
import com.qian.qianbotbackend.model.oj.domain.OjQuestion;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import com.qian.qianbotbackend.service.OjDetailService;
import com.qian.qianbotbackend.mapper.OjDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qian.qianbotbackend.constant.OjConstant.*;

/**
 * @author qian
 * @description 针对表【ojdetail(题目详情表)】的数据库操作Service实现
 * @createDate 2024-07-27 11:02:57
 */
@Service
public class OjDetailServiceImpl extends ServiceImpl<OjDetailMapper, OjDetail>
        implements OjDetailService {

    @Override
    public OjDetail validOjDetail(OjQuestionDetailDTO ojQuestionDetailDTO, boolean isAdd) {
        Long id = ojQuestionDetailDTO.getId();
        Long timeLimit = ojQuestionDetailDTO.getTimeLimit();
        Long memoryLimit = ojQuestionDetailDTO.getMemoryLimit();
        String content = ojQuestionDetailDTO.getContent();
        List<String> template = ojQuestionDetailDTO.getTemplate();
        List<String> answer = ojQuestionDetailDTO.getAnswer();
        List<String> tips = ojQuestionDetailDTO.getTips();
        if (isAdd && ObjectUtil.isAllEmpty(timeLimit, memoryLimit, content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        } else if (!isAdd) {
            ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        }
        if (content != null && content.length() > OJ_QUESTION_CONTENT_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_QUESTION_CONTENT_LENGTH_TOO_LONG);
        }
        if (template != null && template.size() > OJ_QUESTION_TEMPLATE_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_QUESTION_TEMPLATE_LENGTH_TOO_LONG);
        }
        if (answer != null && answer.size() > OJ_QUESTION_ANSWER_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_QUESTION_ANSWER_LENGTH_TOO_LONG);
        }
        if (tips != null && tips.size() > OJ_TIPS_LENGTH) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, OJ_TIPS_LENGTH_TOO_LONG);
        }
        OjDetail ojDetail = BeanUtil.copyProperties(ojQuestionDetailDTO, OjDetail.class);
        if (template != null && !template.isEmpty()) {
            ojDetail.setTemplate(JSONUtil.toJsonStr(template));
        }
        if (answer != null && !answer.isEmpty()) {
            ojDetail.setTemplate(JSONUtil.toJsonStr(answer));
        }
        if (tips != null && !tips.isEmpty()) {
            ojDetail.setTemplate(JSONUtil.toJsonStr(tips));
        }
        return ojDetail;
    }

    @Override
    public void updateOjDetailByQuestionId(OjDetail ojDetail) {
        boolean update = this.update(ojDetail, Wrappers.lambdaUpdate(OjDetail.class).eq(OjDetail::getQuestionId, ojDetail.getQuestionId()));
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
    }

    @Override
    public void deleteOjDetailByQuestionId(Long questionId) {
        boolean delete = this.remove(Wrappers.lambdaUpdate(OjDetail.class).eq(OjDetail::getQuestionId, questionId));
        ThrowUtils.throwIf(!delete, ErrorCode.SYSTEM_ERROR);
    }

    @Override
    public OjDetail getQjDetailByQuestionId(Long questionId) {
        OjDetail ojDetail = this.getOne(Wrappers.lambdaQuery(OjDetail.class).eq(OjDetail::getQuestionId, questionId));
        ThrowUtils.throwIf(ojDetail == null, ErrorCode.NOT_FOUND_ERROR);
        return ojDetail;
    }
}




