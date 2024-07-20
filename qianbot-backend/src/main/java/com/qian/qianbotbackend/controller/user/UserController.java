package com.qian.qianbotbackend.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.manager.EmailManager;
import com.qian.qianbotbackend.model.user.domain.User;
import com.qian.qianbotbackend.model.user.dto.user.UserLoginRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserQueryRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserRegisterRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserUpdateRequest;
import com.qian.qianbotbackend.model.user.vo.LoginUserVO;
import com.qian.qianbotbackend.model.user.vo.UserVO;
import com.qian.qianbotbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private EmailManager emailManager;

    // region 用户

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResultUtils.success(userService.userRegister(userRegisterRequest));
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求
     * @param request          请求
     * @return 登录用户信息
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        return ResultUtils.success(userService.userLogin(userLoginRequest, request));
    }

    /**
     * 用户重置密码
     *
     * @param userUpdateRequest 请求
     * @return 是否成功
     */
    @PostMapping("/retry/password")
    public BaseResponse<Boolean> userRetryPassword(@RequestBody UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        userService.retryPassword(userUpdateRequest, request);
        return ResultUtils.success(true);
    }

    /**
     * 用户更新
     *
     * @param userUpdateRequest 请求
     * @return 是否成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> userUpdate(@RequestBody UserUpdateRequest userUpdateRequest) {
        userService.userUpdate(userUpdateRequest);
        return ResultUtils.success(true);
    }

    /**
     * 用户注销
     *
     * @param request 请求
     * @return 是否成功
     */
    @GetMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        return ResultUtils.success(userService.userLogout(request));
    }

    /**
     * 获取当前登录用户
     *
     * @param request 请求
     * @return 登录用户信息
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        return ResultUtils.success(userService.getLoginUserVO(userService.getLoginUser(request)));
    }
    // endregion

    // region 增删改查

    /**
     * 根据 id 获取用户（仅管理员）
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<User> getUserById(long id) {
        return ResultUtils.success(userService.getUserById(id));
    }

    /**
     * 根据 id 获取包装类
     *
     * @param id 用户id
     * @return userVO
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id) {
        return ResultUtils.success(userService.getUserVO(userService.getUserById(id)));
    }

    /**
     * 分页获取用户列表（仅管理员）
     *
     * @param userQueryRequest 分页请求
     * @return 用户列表
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest) {
        return ResultUtils.success(userService.listUserByPage(userQueryRequest));
    }

    /**
     * 分页获取用户封装列表
     *
     * @param userQueryRequest 分页请求
     * @return 用户封装列表
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {
        return ResultUtils.success(userService.listUserVOByPage(userQueryRequest));
    }
    // endregion

    // region 验证码

    /**
     * 获取注册验证码
     *
     * @param email 邮箱
     * @return 是否成功
     */
    @GetMapping("/send/code/register")
    public BaseResponse<Boolean> getRegisterCode(String email) {
        userService.verifyEmail(email, true);
        String code = emailManager.genAndSaveCode(email);
        emailManager.sendCodeForRegister(email, code);
        return ResultUtils.success(true);
    }

    /**
     * 获取密码重置验证码
     *
     * @param email 邮箱
     * @return 是否成功
     */
    @GetMapping("/send/code/password")
    public BaseResponse<Boolean> getPasswordCode(String email) {
        userService.verifyEmail(email, false);
        String code = emailManager.genAndSaveCode(email);
        emailManager.sendCodeForPassword(email, code);
        return ResultUtils.success(true);
    }
    // endregion
}
