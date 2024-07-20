package com.qian.qianbotbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.manager.EmailManager;
import com.qian.qianbotbackend.mapper.UserMapper;
import com.qian.qianbotbackend.model.user.domain.User;
import com.qian.qianbotbackend.model.user.dto.user.UserLoginRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserQueryRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserRegisterRequest;
import com.qian.qianbotbackend.model.user.dto.user.UserUpdateRequest;
import com.qian.qianbotbackend.model.user.vo.LoginUserVO;
import com.qian.qianbotbackend.model.user.vo.UserVO;
import com.qian.qianbotbackend.service.UserService;
import com.qian.qianbotbackend.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.stream.Collectors;

import static com.qian.qianbotbackend.constant.UserConstant.*;

/**
 * @author qian
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-07-07 14:10:49
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private EmailManager emailManager;

    @Override
    public void validUser(User user) {
        Long id = user.getId();
        String userAccount = user.getUserAccount();
        String userName = user.getUserName();
        String userAvatar = user.getUserAvatar();
        String userProfile = user.getUserProfile();
        String userRole = user.getUserRole();
        String userEmail = user.getUserEmail();
        String userPhone = user.getUserPhone();
        Integer userGender = user.getUserGender();
        Date userBirthday = user.getUserBirthday();
        String userAddress = user.getUserAddress();
        if (StringUtils.isNotBlank(userAccount) && userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_ACCOUNT_SHORT);
        }
        if (StringUtils.isNotBlank(userName) && (userName.length() < 2 || userName.length() > 30)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_NAME_ERROR);
        }
    }

    @Override
    public Boolean verifyEmail(String email, Boolean isRegister) {
        if (StringUtils.isBlank(email)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 校验邮箱是否已注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userEmail", email);
        long count = this.count(queryWrapper);
        if (isRegister && count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_EMAIL_EXIST);
        } else if (!isRegister && count == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_EMAIL_NOT_EXIST);

        }
        return true;
    }

    @Override
    public Long userRegister(UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 校验
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String userName = userRegisterRequest.getUserName();
        String userProfile = userRegisterRequest.getUserProfile();
        String userEmail = userRegisterRequest.getUserEmail();
        String userPhone = userRegisterRequest.getUserPhone();
        Integer userGender = userRegisterRequest.getUserGender();
        Date userBirthday = userRegisterRequest.getUserBirthday();
        String userAddress = userRegisterRequest.getUserAddress();
        String code = userRegisterRequest.getCode();
        // 检查是否存在为空的必要参数
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userName, userEmail, code)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_PARAMS_NULL);
        }
        if (userName.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_NAME_ERROR);
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_ACCOUNT_SHORT);
        }
        if (userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_PASSWORD_SHORT);
        }
        // 验证验证码是否正确
        ThrowUtils.throwIf(!emailManager.verifyCode(userEmail, code), ErrorCode.PARAMS_ERROR, USER_EMAIL_CODE_ERROR);
        // 校验密码是否相同
        ThrowUtils.throwIf(!userPassword.equals(checkPassword), ErrorCode.PARAMS_ERROR, USER_PASSWORD_NOT_SAME);
        synchronized (userAccount.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_ACCOUNT_EXIST);
            }
            // 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((USER_SALT + userPassword).getBytes());
            // 插入数据
            User user = new User();
            BeanUtils.copyProperties(userRegisterRequest, user);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, USER_DATABASE_FAILURE);
            }
            return user.getId();
        }
    }

    @Override
    public LoginUserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 校验
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_ACCOUNT_FAILURE);
        }
        if (userPassword.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_PASSWORD_FAILURE);
        }
        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((USER_SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, USER_EXISTS_ERROR);
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public Boolean userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, USRE_NOT_LOGIN);
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public void retryPassword(UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        String userPassword = userUpdateRequest.getUserPassword();
        String checkPassword = userUpdateRequest.getCheckPassword();
        String userEmail = userUpdateRequest.getUserEmail();
        String code = userUpdateRequest.getCode();
        // 验证验证码是否正确
        ThrowUtils.throwIf(!emailManager.verifyCode(userEmail, code), ErrorCode.PARAMS_ERROR, USER_EMAIL_CODE_ERROR);
        // 校验密码是否相同
        ThrowUtils.throwIf(!userPassword.equals(checkPassword), ErrorCode.PARAMS_ERROR, USER_PASSWORD_NOT_SAME);
        // 查找账号
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userEmail", userEmail);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 校验账号是否存在
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, USER_NOT_EXISTS);
        // 修改密码
        String encryptPassword = DigestUtils.md5DigestAsHex((USER_SALT + userPassword).getBytes());
        user.setUserPassword(encryptPassword);
        boolean update = this.updateById(user);
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
        if (request != null && request.getSession().getAttribute(USER_LOGIN_STATE) != null) {
            // 如果之前是登录状态，则登出
            request.getSession().removeAttribute(USER_LOGIN_STATE);
        }
    }

    @Override
    public void userUpdate(UserUpdateRequest userUpdateRequest) {
        Long id = BaseContext.getUserId();
        // 查找账号
        User user = this.getById(id);
        // 校验账号是否存在
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, USER_NOT_EXISTS);
        BeanUtils.copyProperties(userUpdateRequest, user);
        user.setId(id);
        validUser(user);
        boolean update = this.updateById(user);
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public User getUserById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = this.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return user;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String userAccount = userQueryRequest.getUserAccount();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(userAccount), "userAccount", userAccount);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public Page<User> listUserByPage(UserQueryRequest userQueryRequest) {
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        return this.page(new Page<>(current, size),
                this.getQueryWrapper(userQueryRequest));
    }

    @Override
    public Page<UserVO> listUserVOByPage(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<User> userPage = this.page(new Page<>(current, size),
                this.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        userVOPage.setRecords(userPage.getRecords().stream().map(this::getUserVO).collect(Collectors.toList()));
        return userVOPage;
    }

    @Override
    public boolean isAdmin(Long userId) {
        if (userId == null || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = this.getUserById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        } else {
            return user.getUserRole().equals(ROLE_ADMIN);
        }
    }
}




