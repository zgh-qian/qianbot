package com.qian.qianbotbackend.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.*;
import com.qian.qianbotbackend.common.ReviewDTO;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.dto.app.*;
import com.qian.qianbotbackend.model.app.vo.*;
import com.qian.qianbotbackend.model.user.domain.User;
import com.qian.qianbotbackend.model.user.dto.userusage.UserUsageDTO;
import com.qian.qianbotbackend.service.AppService;
import com.qian.qianbotbackend.service.UserUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.qian.qianbotbackend.constant.UserConstant.USER_LOGIN_STATE;
import static com.qian.qianbotbackend.enums.user.UserUsageEnum.USER_USAGE_ENUM_APP_AI;

@Slf4j
@RestController
@RequestMapping("/app")
public class AppController {
    @Resource
    private AppService appService;

    @Resource
    private UserUsageService userUsageService;

    // region 用户相关接口

    /**
     * 创建应用
     *
     * @param appAddRequest 应用创建请求
     * @return 应用id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addApp(@RequestBody AppAddRequest appAddRequest) {
        return ResultUtils.success(appService.addApp(appAddRequest));
    }

    /**
     * 删除应用
     *
     * @param deleteRequest 删除应用请求
     * @return 删除结果
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteApp(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(appService.deleteApp(deleteRequest));
    }

    /**
     * 编辑应用（给用户使用）
     *
     * @param appEditRequest 编辑应用请求
     * @return 编辑结果
     */
    @PostMapping("/edit")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> editApp(@RequestBody AppEditRequest appEditRequest) {
        return ResultUtils.success(appService.editApp(appEditRequest));
    }

    /**
     * 根据 id 获取应用（封装类）
     *
     * @param id 应用id
     * @return 应用封装类
     */
    @GetMapping("/get/vo")
    public BaseResponse<AppVO> getAppVOById(Long id) {
        return ResultUtils.success(appService.getAppVOById(id));
    }

    /**
     * 分页获取应用列表（封装类）
     *
     * @param appQueryRequest 分页获取应用列表请求
     * @return 应用列表封装类
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<AppVO>> listAppVOByPage(@RequestBody AppQueryRequest appQueryRequest) {
        return ResultUtils.success(appService.listAppVOByPage(appQueryRequest));
    }

    /**
     * 分页获取当前登录用户创建的应用列表
     *
     * @param appQueryRequest 分页获取应用列表请求
     * @return 应用列表封装类
     */
    @PostMapping("/my/list/page/vo")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Page<AppVO>> listMyAppVOByPage(@RequestBody AppQueryRequest appQueryRequest) {
        return ResultUtils.success(appService.listMyAppVOByPage(appQueryRequest));
    }

    // endregion

    // region  管理员相关接口

    /**
     * 更新应用（仅管理员可用）
     *
     * @param appUpdateRequest 更新应用请求
     * @return 更新结果
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> updateApp(@RequestBody AppUpdateRequest appUpdateRequest) {
        return ResultUtils.success(appService.updateApp(appUpdateRequest));
    }

    /**
     * 分页获取应用列表（仅管理员可用）
     *
     * @param appQueryRequest 分页获取应用列表请求
     * @return 应用列表
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Page<App>> listAppByPage(@RequestBody AppQueryRequest appQueryRequest) {
        return ResultUtils.success(appService.listAppByPage(appQueryRequest));
    }

    @GetMapping("/detail")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<AppDetailVO> getAppDetail(Long appId) {
        return ResultUtils.success(appService.getAppDetail(appId));
    }

    /**
     * 审核应用（仅管理员可用）
     *
     * @param reviewDTO 审核应用请求
     * @return 审核结果
     */
    @PostMapping("/review")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> doAppReview(@RequestBody ReviewDTO reviewDTO) {
        return ResultUtils.success(appService.doAppReview(reviewDTO));
    }
    // endregion

    // region 问题和选项接口

    /**
     * 添加或更新选项或问题
     *
     * @param appQuestionOptionUpdateRequest 请求
     * @return 是否成功
     */
    @PostMapping("/ques/opt/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> addAndUpdateAppQuestionAndOption(@RequestBody AppQuestionOptionUpdateRequest appQuestionOptionUpdateRequest) {
        return ResultUtils.success(appService.addAndUpdateAppQuestionAndOption(appQuestionOptionUpdateRequest));
    }

    /**
     * 获取应用问题和选项
     *
     * @param appId 应用id
     * @return 应用问题和选项
     */
    @GetMapping("/ques/opt/get")
    public BaseResponse<List<AppQuestionAndOptionVO>> getAppQuestionAndOption(Long appId) {
        return ResultUtils.success(appService.getAppQuestionAndOption(appId));
    }

    /**
     * AI 生成应用问题和选项（同步）
     *
     * @param appAIGenerateRequest 应用ai生成请求
     * @return 获取应用问题和选项
     */
    @PostMapping("/ai/generate/sync")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<List<AppAIGenerateVO>> getAIGenerateQuestionSync(@RequestBody AppAIGenerateRequest appAIGenerateRequest) {
        // 校验是否存在次数
        ThrowUtils.throwIf(
                !userUsageService.subUserUsage(new UserUsageDTO(null, USER_USAGE_ENUM_APP_AI.getText(), null)),
                ErrorCode.OPERATION_ERROR,
                "AI 生成次数已用完");
        return ResultUtils.success(appService.getAIGenerateQuestionSync(appAIGenerateRequest));
    }

    /**
     * AI 生成应用问题和选项（sse）
     *
     * @param appAIGenerateRequest 应用ai生成请求
     * @return sseEmitter
     */
    @GetMapping("/ai/generate/sse")
    public SseEmitter getAIGenerateQuestionSSE(AppAIGenerateRequest appAIGenerateRequest, HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (userObj != null) {
            User user = (User) userObj;
            if (user.getUserRole().equals(UserConstant.ROLE_BAN)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
            BaseContext.setUserId(user.getId());
            // 校验是否存在次数
            ThrowUtils.throwIf(
                    !userUsageService.subUserUsage(new UserUsageDTO(null, USER_USAGE_ENUM_APP_AI.getText(), null)),
                    ErrorCode.OPERATION_ERROR,
                    "AI 生成次数已用完");
            return appService.getAIGenerateQuestionBySSE(appAIGenerateRequest);
        }
        throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
    }

    /**
     * AI 审核应用（仅管理员可用）
     *
     * @param appId 应用Id
     * @return 是否成功
     */
    @GetMapping("/ai/review/app")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> doAIAppReview(Long appId) {
        return ResultUtils.success(appService.doAIAppReview(appId));
    }
    // endregion
}
