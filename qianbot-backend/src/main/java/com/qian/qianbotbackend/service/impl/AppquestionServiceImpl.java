package com.qian.qianbotbackend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appquestion;
import com.qian.qianbotbackend.model.app.dto.appquestion.AppquestionAddRequest;
import com.qian.qianbotbackend.model.app.dto.appquestion.AppquestionUpdateRequest;
import com.qian.qianbotbackend.service.AppService;
import com.qian.qianbotbackend.service.AppquestionService;
import com.qian.qianbotbackend.mapper.AppquestionMapper;
import com.qian.qianbotbackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qian
 * @description 针对表【appquestion(题目)】的数据库操作Service实现
 * @createDate 2024-07-07 15:27:31
 */
@Service
public class AppquestionServiceImpl extends ServiceImpl<AppquestionMapper, Appquestion>
        implements AppquestionService {
    @Resource
    private AppService appService;

    @Resource
    private UserService userService;

    @Resource
    private AppquestionMapper appquestionMapper;

    @Override
    public void validAppQuestion(Appquestion appquestion, boolean isAdd) {
        ThrowUtils.throwIf(appquestion == null, ErrorCode.PARAMS_ERROR);
        Long id = appquestion.getId();
        String questionPic = appquestion.getQuestionPic();
        String questionName = appquestion.getQuestionName();
        Long appId = appquestion.getAppId();
        if (isAdd) {
            ThrowUtils.throwIf(StringUtils.isBlank(questionName), ErrorCode.PARAMS_ERROR);
            ThrowUtils.throwIf(appId == null, ErrorCode.PARAMS_ERROR);
        } else {
            ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(questionName)) {
            ThrowUtils.throwIf(questionName.length() > 80, ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public Long addAppQuestion(AppquestionAddRequest appquestionAddRequest) {
        ThrowUtils.throwIf(appquestionAddRequest == null, ErrorCode.PARAMS_ERROR);
        // app 是否存在
        App app = appService.getById(appquestionAddRequest.getAppId());
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        Appquestion appquestion = new Appquestion();
        BeanUtils.copyProperties(appquestionAddRequest, appquestion);
        validAppQuestion(appquestion, true);
        appquestion.setUserId(BaseContext.getUserId());
        boolean save = this.save(appquestion);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return appquestion.getId();
    }

    @Override
    public Boolean deleteAppQuestion(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        Appquestion appquestion = this.getById(id);
        ThrowUtils.throwIf(appquestion == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        Long userId = BaseContext.getUserId();
        if (!appquestion.getUserId().equals(userId) && !userService.isAdmin(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean remove = this.removeById(id);
        ThrowUtils.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean updateAppQuestion(AppquestionUpdateRequest appquestionUpdateRequest) {
        ThrowUtils.throwIf(appquestionUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Appquestion appquestion = new Appquestion();
        BeanUtils.copyProperties(appquestionUpdateRequest, appquestion);
        Appquestion oldAppquestion = this.getById(appquestion.getId());
        ThrowUtils.throwIf(oldAppquestion == null, ErrorCode.NOT_FOUND_ERROR);
        validAppQuestion(appquestion, false);
        // 仅本人可编辑
        Long userId = BaseContext.getUserId();
        if (!oldAppquestion.getUserId().equals(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = this.updateById(appquestion);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Appquestion getAppQuestionById(Long appquestionId) {
        if (appquestionId == null || appquestionId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Appquestion appquestion = this.getById(appquestionId);
        if (appquestion == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return appquestion;
    }

    @Override
    public List<Appquestion> getAppQuestionList(Long appId) {
        return appquestionMapper.selectList(Wrappers.lambdaQuery(Appquestion.class).eq(Appquestion::getAppId, appId));
    }
}




