package com.qian.qianbotbackend.strategy.codesandbox.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExecuteCommandInDirectory {
    public static void main(String[] args) {
        try {
            // 指定要进入的目录路径
            String directoryPath = "E:\\CodeFiles\\QianBot\\qianbot-backend\\src\\main\\resources\\codesandbox\\code";
            // 创建一个 ProcessBuilder 对象
            ProcessBuilder builder = new ProcessBuilder();
            // 设置工作目录
            builder.directory(new File(directoryPath));
            // 设置要执行的命令
            String[] compileCmd = {"java", "Main"};
            builder.command(compileCmd);
            // 启动进程
            Process process = builder.start();
            // 等待进程执行完成
            int exitCode = process.waitFor();
            BufferedReader bufferedReader;
            List<String> outputStrList = new ArrayList<>();
            if (exitCode == 0) {
                // 0 正常退出，编译/运行成功
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            } else {
                // 1 异常退出，编译/运行失败
                bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outputStrList.add(line);
            }
            System.out.println(outputStrList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
