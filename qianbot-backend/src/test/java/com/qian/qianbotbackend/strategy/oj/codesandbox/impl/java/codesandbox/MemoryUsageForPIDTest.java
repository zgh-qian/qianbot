package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.codesandbox;

import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MemoryUsageForPIDTest {
    @Test
    void testPID() {
        int pid = 19188; // 你要获取内存消耗的进程的PID
        // 初始化系统信息
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        // 获取进程
        OSProcess process = operatingSystem.getProcess(pid);
        if (process != null) {
            // 获取进程的最大内存消耗量
            long maxMemory = process.getVirtualSize();
            // 打印结果
            System.out.println("PID " + pid + " 的最大内存消耗量: " + maxMemory / 1024 + "kb");
            System.out.println("PID " + pid + " 的最大内存消耗量: " + maxMemory / 1024 / 1024 + "mb");
            System.out.println("PID " + pid + " 的最大内存消耗量: " + FormatUtil.formatBytes(maxMemory));
        } else {
            System.out.println("没有找到PID为 " + pid + " 的进程。");
        }
    }

    @Test
    void testJPS() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("jps");
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> jpsOutput = new ArrayList<>();
            Integer MainPid = -1;
            while ((line = bufferedReader.readLine()) != null) {
                jpsOutput.add(line);
                if (line.endsWith("Main")) {
                    MainPid = Integer.parseInt(line.split(" ")[0]);
                }
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("JPS 执行成功");
                System.out.println("Exit code: " + exitCode);
                System.out.println(jpsOutput);
                System.out.println(MainPid);
            } else {
                System.out.println("JPS 执行失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
