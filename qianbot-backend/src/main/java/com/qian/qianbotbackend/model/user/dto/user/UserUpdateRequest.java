package com.qian.qianbotbackend.model.user.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 邮箱，唯一
     */
    private String userEmail;

    /**
     * 手机号，唯一
     */
    private String userPhone;

    /**
     * 性别
     */
    private Integer userGender;

    /**
     * 生日
     */
    private Date userBirthday;

    /**
     * 地址
     */
    private String userAddress;

    /**
     * 验证码
     */
    private String code;

    private static final long serialVersionUID = 1L;
}