package com.qian.qianbotbackend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.app.domain.Appoption;
import com.qian.qianbotbackend.model.app.domain.Appquestion;
import com.qian.qianbotbackend.model.app.dto.appoption.AppoptionAddRequest;
import com.qian.qianbotbackend.model.app.dto.appoption.AppoptionUpdateRequest;
import com.qian.qianbotbackend.service.AppoptionService;
import com.qian.qianbotbackend.mapper.AppoptionMapper;
import com.qian.qianbotbackend.service.AppquestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author qian
 * @description 针对表【appoption(选项)】的数据库操作Service实现
 * @createDate 2024-07-07 15:27:31
 */
@Service
public class AppoptionServiceImpl extends ServiceImpl<AppoptionMapper, Appoption>
        implements AppoptionService {
    @Resource
    private AppquestionService appquestionService;

    @Resource
    private AppoptionMapper appoptionMapper;

    @Override
    public void validAppOption(Appoption appoption, boolean isAdd) {
        ThrowUtils.throwIf(appoption == null, ErrorCode.PARAMS_ERROR);
        Long id = appoption.getId();
        String optionPic = appoption.getOptionPic();
        String optionKey = appoption.getOptionKey();
        String optionName = appoption.getOptionName();
        String optionResult = appoption.getOptionResult();
        Long questionId = appoption.getQuestionId();
        if (isAdd) {
            if (StringUtils.isAnyBlank(optionKey, optionName, optionResult)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            ThrowUtils.throwIf(questionId == null, ErrorCode.PARAMS_ERROR);
        } else {
            ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(optionKey)) {
            ThrowUtils.throwIf(optionKey.length() > 20, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(optionName)) {
            ThrowUtils.throwIf(optionName.length() > 80, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(optionResult)) {
            ThrowUtils.throwIf(optionResult.length() > 20, ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public Long addAppOption(AppoptionAddRequest appoptionAddRequest) {
        ThrowUtils.throwIf(appoptionAddRequest == null, ErrorCode.PARAMS_ERROR);
        // question 是否存在
        Appquestion appquestion = appquestionService.getById(appoptionAddRequest.getQuestionId());
        ThrowUtils.throwIf(appquestion == null, ErrorCode.NOT_FOUND_ERROR);
        Appoption appoption = new Appoption();
        BeanUtils.copyProperties(appoptionAddRequest, appoption);
        validAppOption(appoption, true);
        appoption.setUserId(BaseContext.getUserId());
        boolean save = this.save(appoption);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return appoption.getId();
    }

    @Override
    public Boolean deleteAppOption(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        Appoption appoption = this.getById(id);
        ThrowUtils.throwIf(appoption == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人可删除
        Long userId = BaseContext.getUserId();
        if (!appoption.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean remove = this.removeById(id);
        ThrowUtils.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean updateAppOption(AppoptionUpdateRequest appoptionUpdateRequest) {
        ThrowUtils.throwIf(appoptionUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Appoption appoption = new Appoption();
        BeanUtils.copyProperties(appoptionUpdateRequest, appoption);
        Appoption oldAppoption = this.getById(appoption.getId());
        ThrowUtils.throwIf(oldAppoption == null, ErrorCode.NOT_FOUND_ERROR);
        validAppOption(appoption, false);
        // 仅本人可编辑
        Long userId = BaseContext.getUserId();
        if (!oldAppoption.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = this.updateById(appoption);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Appoption getAppOption(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Appoption appoption = this.getById(id);
        if (appoption == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return appoption;
    }

    @Override
    public List<Appoption> getAppOptionList(Long questionId) {
        return appoptionMapper.selectList(Wrappers.lambdaQuery(Appoption.class).eq(Appoption::getQuestionId, questionId));
    }

    @Override
    public List<String> getOptionKeyList(List<Long> optionList) {
        return optionList.stream().map(option -> this.getById(option).getOptionKey()).collect(Collectors.toList());
    }
}




