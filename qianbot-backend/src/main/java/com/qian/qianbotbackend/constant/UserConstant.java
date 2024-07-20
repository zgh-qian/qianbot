package com.qian.qianbotbackend.constant;

/**
 * 用户常量
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "qianbot_user_login";

    String USER_SALT = "qianbot_user_salt";

    //  region 权限

    /**
     * 管理员
     */
    String ROLE_ADMIN = "admin";

    /**
     * VIP
     */
    String ROLE_VIP = "vip";

    /**
     * 用户
     */
    String ROLE_USER = "user";

    /**
     * 封号
     */
    String ROLE_BAN = "ban";

    /**
     * 未登录
     */
    String ROLE_NOT_LOGIN = "not_login";

    // endregion

    // region
    String USER_PARAMS_NULL = "参数为空";
    String USER_ACCOUNT_SHORT = "用户账号过短";
    String USER_PASSWORD_SHORT = "用户密码过短";
    String USER_NAME_ERROR = "用户名长度错误";
    String USER_PASSWORD_NOT_SAME = "两次输入的密码不一致";
    String USER_ACCOUNT_EXIST = "账号重复";
    String USER_DATABASE_FAILURE = "注册失败，数据库错误";
    String USER_NOT_EXISTS = "用户不存在";
    String USER_ACCOUNT_FAILURE = "账号错误";
    String USER_PASSWORD_FAILURE = "密码错误";
    String USER_EXISTS_ERROR = "用户不存在或密码错误";
    String USRE_NOT_LOGIN = "未登录";
    String USER_EMAIL_CODE_ERROR = "验证码错误";
    String USER_EMAIL_FORMAT_ERROR = "邮箱格式错误";
    String USER_EMAIL_EXIST = "邮箱已被注册";
    String USER_EMAIL_NOT_EXIST = "邮箱未被注册";
    // endregion
}
