package com.qian.qianbotbackend.constant;

/**
 * 邮件相关常量
 */
public interface MailConstant {

    // region mail
    /**
     * 验证码保存时间
     */
    int MAIL_TIMEOUT = 600;

    /**
     * 邮箱编码
     */
    String MAIL_CHARSET = "UTF-8";

    /**
     * html
     */
    String MAIL_CONTENT_TYPE = "text/html;charset=UTF-8";

    /**
     * 使用的协议
     */
    String MAIL_PROTOCOL = "smtp";

    /**
     * SMTP 服务器地址
     */
    String MAIL_SMTP_HOST = "smtp.qq.com";

    /**
     * SMTP 发送端口
     */
    String MAIL_SMTP_PORT = "465";

    /**
     * 是否需要请求认证
     */
    String MAIL_SMTP_AUTH = "true";

    /**
     * socket 工厂类
     */
    String MAIL_SMTP_SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";

    /**
     * socket 连接超时时间
     */
    String MAIL_SMTP_CONNECTION_TIMEOUT = "10000";

    /**
     * socket 读取超时时间
     */
    String MAIL_SMTP_TIMEOUT = "10000";

    /**
     * socket 写入超时时间
     */
    String MAIL_SMTP_WRITE_TIMEOUT = "10000";

    /**
     * 是否启用 SSL
     */
    String MAIL_SSL_ENABLE = "true";

    /**
     * SSL 版本
     */
    String MAIL_SSL_PROTOCOLS = "TLSv1.2";
    // endregion

    // region redis
    String PUBLIC_PREFIX = "qianbot:";

    /**
     * 验证码前缀
     */
    String CODE_PREFIX = PUBLIC_PREFIX + "verification_code:";

    /**
     * 验证码过期时间，单位：s
     */
    Long CODE_EXPIRE_TIME = 600L;

    /**
     * 令牌桶过期时间，单位：s
     */
    Long CODE_TOKEN_EXPIRE_TIME = 600L;
    // endregion
}
