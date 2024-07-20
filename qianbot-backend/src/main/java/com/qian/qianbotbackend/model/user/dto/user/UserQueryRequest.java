package com.qian.qianbotbackend.model.user.dto.user;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}