package com.qian.qianbotbackend.model.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
public class UserVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

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
     * 用户角色：admin/vip/user/ban
     */
    private String userRole;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}