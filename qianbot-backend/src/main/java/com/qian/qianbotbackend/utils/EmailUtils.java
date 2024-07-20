package com.qian.qianbotbackend.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 邮件相关工具类
 */
public class EmailUtils {
    /**
     * 生成 6 位的验证码
     *
     * @return 6位验证码
     */
    public static String genCode() {
        return genCode(6);
    }

    /**
     * 生成 n 位验证码
     *
     * @param n 位
     * @return n 位验证码
     */
    public static String genCode(int n) {
        int num1, num2;
        Random random = new Random();
        num2 = (int) Math.pow(10, n - 1);
        num1 = 9 * num2;
        int code = random.nextInt(num1) + num2;
        return String.valueOf(code);
    }

    /**
     * 生成注册邮件内容
     *
     * @param code 验证码
     * @return 注册邮件内容
     */
    public static String genRegisterMailContent(String code) {
        return "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            font-size: 16px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            font-size: 24px;\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "        p {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .code {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px;\n" +
                "            background-color: #f0f0f0;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 18px;\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>注册验证码</h1>\n" +
                "    <p>尊敬的用户：</p>\n" +
                "    <p>感谢您注册我们的服务，请将下面的验证码输入到相关页面中完成注册：</p>\n" +
                "    <p>验证码：<span class=\"code\">" + code + "</span></p>\n" +
                "    <p>请注意，此验证码将在10分钟内过期，过期后需重新申请获取验证码。</p>\n" +
                "    <p>如果您没有注册，请忽略本邮件。</p>\n" +
                "</body>\n" +
                "</html>";
    }

    /**
     * 生成找回密码邮件内容
     *
     * @param code 验证码
     * @return 找回密码邮件内容
     */
    public static String genPasswordMailContent(String code) {
        return "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            font-size: 16px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            font-size: 24px;\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "        p {\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .code {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px;\n" +
                "            background-color: #f0f0f0;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 18px;\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>找回密码验证码</h1>\n" +
                "    <p>尊敬的用户：</p>\n" +
                "    <p>您正在通过电子邮件申请找回密码，请将下面的验证码输入到相关页面中完成后续操作：</p>\n" +
                "    <p>验证码：<span class=\"code\">" + code + "</span></p>\n" +
                "    <p>请注意，此验证码将在10分钟内过期，过期后需重新申请获取验证码。</p>\n" +
                "    <p>如果您没有申请此服务，可能是其他人误输入了您的电子邮件地址，请忽略本邮件。</p>\n" +
                "</body>\n" +
                "</html>";
    }

    /**
     * 验证邮箱格式
     *
     * @param email 邮箱
     * @return 格式是否正确
     */
    public static Boolean verifyEmail(String email) {
        // 邮箱正则表达式
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(emailRegex);
        // 验证邮箱格式
        return pattern.matcher(email).matches();
    }
}
