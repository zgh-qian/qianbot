package com.qian.qianbotbackend.manager;

import cn.hutool.extra.mail.MailUtil;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.config.MailConfig;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.utils.EmailUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.qian.qianbotbackend.constant.MailConstant.*;
import static com.qian.qianbotbackend.constant.UserConstant.USER_EMAIL_FORMAT_ERROR;

/**
 * 邮件管理
 */
@Component
public class EmailManager {
    @Resource
    private MailConfig mailConfig;

    @Resource
    private Session session;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送注册验证码
     *
     * @param email 发送地址
     */
    public void sendCodeForRegister(String email, String code) {
        String content = EmailUtils.genRegisterMailContent(code);
        try {
            sendEmail(email, "注册验证码", content);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 发送密码重置验证码
     *
     * @param email 发送地址
     */
    public void sendCodeForPassword(String email, String code) {
        String content = EmailUtils.genPasswordMailContent(code);
        try {
            sendEmail(email, "密码重置验证码", content);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 发送邮件
     *
     * @param email   发送地址
     * @param title   发送标题
     * @param content 发送内容
     */
    public void sendEmail(String email, String title, String content) throws Exception {
        // 创建邮件
        MimeMessage message = new MimeMessage(session);
        // From: 发件人
        message.setFrom(new InternetAddress(mailConfig.getSendEmail(), mailConfig.getSendName(), MAIL_CHARSET));
        // To: 收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email, email, MAIL_CHARSET));
        // Subject: 邮件主题
        message.setSubject(title, MAIL_CHARSET);
        // Content: 邮件正文（可以使用html标签）
        message.setContent(content, MAIL_CONTENT_TYPE);
        // 设置发件时间
        message.setSentDate(new Date());
        // 保存设置
        message.saveChanges();
        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        transport.connect(mailConfig.getSendEmail(), mailConfig.getAuthorizationCode());
        // 发送邮件, 发到所有的收件地址, message.getAllRecipients()获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭传输连接
        transport.close();
    }

    /**
     * 生成和保存验证码
     *
     * @param email 邮件
     * @return 验证码
     */
    public String genAndSaveCode(String email) {
        // 验证邮箱格式是否正确
        ThrowUtils.throwIf(!EmailUtils.verifyEmail(email), ErrorCode.PARAMS_ERROR, USER_EMAIL_FORMAT_ERROR);
        String key = CODE_PREFIX + email;
        // 判断是否可以生成验证码
        boolean flag = tokenBucketAlgorithm(key);
        ThrowUtils.throwIf(!flag, ErrorCode.OPERATION_ERROR, "验证码发送过于频繁，请稍后再试");
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String code = EmailUtils.genCode();
        // 保存验证码到 redis
        operations.set(key, code, CODE_EXPIRE_TIME, TimeUnit.SECONDS);
        return code;
    }

    /**
     * 验证验证码是否正确
     *
     * @param email 邮箱
     * @param code  用户输入的验证码
     * @return 是否正确
     */
    public Boolean verifyCode(String email, String code) {
        String key = CODE_PREFIX + email;
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String genCode = operations.get(key);
        ThrowUtils.throwIf(genCode == null, ErrorCode.OPERATION_ERROR, "验证码已过期，请重新发送");
        boolean isEquals = genCode.equals(code);
        if (isEquals) {
            // 验证码正确，删除验证码
            operations.getOperations().delete(key);
        }
        return isEquals;
    }

    /**
     * 令牌桶算法（一分钟只能发送一条验证码）
     *
     * @param key 键
     * @return 是否可以生成
     */
    public boolean tokenBucketAlgorithm(String key) {
        // 一分钟内只能发送一条
        int permitsPerMinute = 1;
        // 令牌桶容量
        int maxPermits = 1;
        // 获取当前时间戳
        long now = System.currentTimeMillis();
        // 计算令牌桶内令牌数
        String redisTokens = stringRedisTemplate.opsForValue().get(key + "_tokens");
        int tokens = Integer.parseInt(redisTokens == null ? "0" : redisTokens);
        // 计算令牌桶上次填充的时间戳
        String redisLastRefillTime = stringRedisTemplate.opsForValue().get(key + "_last_refill_time");
        long lastRefillTime = Long.parseLong(redisLastRefillTime == null ? "0" : redisLastRefillTime);
        // 计算当前时间与上次填充时间的时间差
        long timeSinceLast = now - lastRefillTime;
        // 计算需要填充的令牌数
        int refill = (int) (timeSinceLast / 1000 * permitsPerMinute / 60);
        // 更新令牌桶内令牌数
        tokens = Math.min(refill + tokens, maxPermits);
        // 更新上次填充时间戳
        stringRedisTemplate.opsForValue().set(key + "_last_refill_time", String.valueOf(now), CODE_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        // 如果令牌数等于1，则获取令牌
        if (tokens == 1) {
            tokens--;
            stringRedisTemplate.opsForValue().set(key + "_tokens", String.valueOf(tokens), CODE_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            // 如果获取到令牌，则返回true
            return true;
        }
        // 如果没有获取到令牌，则返回false
        return false;
    }
}
