package com.qian.qianbotbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Session;
import java.util.Properties;

import static com.qian.qianbotbackend.constant.MailConstant.*;
import static com.qian.qianbotbackend.constant.MailConstant.MAIL_SMTP_WRITE_TIMEOUT;

@Data
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfig {
    /**
     * 发送人名称
     */
    private String sendName;

    /**
     * 发送人邮箱
     */
    private String sendEmail;

    /**
     * 发送人邮箱授权码
     */
    private String authorizationCode;

    @Bean
    public Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", MAIL_PROTOCOL); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", MAIL_SMTP_HOST); // 指定smtp服务器地址
        props.setProperty("mail.smtp.port", MAIL_SMTP_PORT); // 指定smtp端口号
        // 使用smtp身份验证
        props.setProperty("mail.smtp.auth", MAIL_SMTP_AUTH); // 需要请求认证
        props.put("mail.smtp.ssl.enable", MAIL_SSL_ENABLE); // 开启SSL
        props.put("mail.smtp.ssl.protocols", MAIL_SSL_PROTOCOLS); // 指定SSL版本
        props.put("mail.smtp.socketFactory.class", MAIL_SMTP_SOCKET_FACTORY_CLASS);
        // 由于Properties默认不限制请求时间，可能会导致线程阻塞，所以指定请求时长
        props.setProperty("mail.smtp.connectiontimeout", MAIL_SMTP_CONNECTION_TIMEOUT);// 与邮件服务器建立连接的时间限制
        props.setProperty("mail.smtp.timeout", MAIL_SMTP_TIMEOUT);// 邮件smtp读取的时间限制
        props.setProperty("mail.smtp.writetimeout", MAIL_SMTP_WRITE_TIMEOUT);// 邮件内容上传的时间限制
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false); // 设置为debug模式, 可以查看详细的发送log
        return session;
    }
}
