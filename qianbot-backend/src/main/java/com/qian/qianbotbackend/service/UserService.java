package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.model.user.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.user.dto.user.UserLoginRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserQueryRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserRegisterRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserUpdateRequest;
import com.qian.qianbotbackend.model.user.vo.LoginUserVO;
import com.qian.qianbotbackend.model.user.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qian
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-07-07 14:10:49
 */
public interface UserService extends IService<User> {

    void validUser(User user);

    Boolean verifyEmail(String email, Boolean isRegister);

    Long userRegister(UserRegisterRequest userRegisterRequest);

    LoginUserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);

    Boolean userLogout(HttpServletRequest request);

    void retryPassword(UserUpdateRequest userUpdateRequest, HttpServletRequest request);

    void userUpdate(UserUpdateRequest userUpdateRequest);

    User getLoginUser(HttpServletRequest request);

    User getUserById(long id);

    UserVO getUserVO(User user);

    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    Page<User> listUserByPage(UserQueryRequest userQueryRequest);

    Page<UserVO> listUserVOByPage(UserQueryRequest userQueryRequest);

    boolean isAdmin(Long userId);
}
