package com.qian.qianbotbackend.model.user.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 */
@Data
public class UserLoginRequest implements Serializable {
    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    private static final long serialVersionUID = 1L;
}