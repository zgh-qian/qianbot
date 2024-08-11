package com.qian.qianbotbackend.strategy.codesandbox.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ProcessUtil {
    public String getJavaProcess(String[] cmd) {
        try {
            // 创建一个 ProcessBuilder 对象
            ProcessBuilder builder = new ProcessBuilder();
            builder.redirectErrorStream(true);
            // 设置要执行的命令
            builder.command(cmd);
            // 启动进程
            Process process = builder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            // 等待进程执行完成
            int exitCode = process.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
