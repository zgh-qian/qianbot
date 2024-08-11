package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java;

import cn.hutool.core.date.StopWatch;
import com.qian.qianbotbackend.model.codesandbox.OjExecuteMessage;
import org.apache.commons.lang3.StringUtils;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.qian.qianbotbackend.constant.OjConstant.JAVA_CLASS_NAME_WITHOUT_SUFFIX;

public class JavaProcessUtil {
    /**
     * 编译Java代码
     *
     * @param process 编译Java代码的进程
     * @return 编译结果
     */
    public static OjExecuteMessage compileJavaCode(Process process) {
        OjExecuteMessage executeMessage = new OjExecuteMessage();
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int pid = getPID();
            CompletableFuture<Long> future = getMaxPIDMemoryAsync(pid);
            int exitValue = process.waitFor();
            BufferedReader bufferedReader;
            List<String> outputStrList = new ArrayList<>();
            if (exitValue == 0) {
                // 0 正常退出，编译成功
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            } else {
                // 1 异常退出，编译失败
                bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            }
            stopWatch.stop();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outputStrList.add(line);
            }
            // 设置输出码
            executeMessage.setExitCode(exitValue);
            // 设置进程的正常/错误输出
            executeMessage.setMessage(StringUtils.join(outputStrList, '\n').toString());
            // 设置执行时间消耗
            executeMessage.setTime(stopWatch.getTotalTimeMillis());
            // 设置执行内存消耗
            Long memory = future.get();
            executeMessage.setMemory(memory == -1L ? 0L : memory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return executeMessage;
    }

    /**
     * 非交互式执行Java代码
     *
     * @param process Java代码执行的进程
     * @return 执行结果
     */
    public static OjExecuteMessage executeJavaCodeNonInteractive(Process process) {
        return compileJavaCode(process);
    }

    /**
     * 交互式执行Java代码
     *
     * @param process Java代码交互式执行的进程
     * @param input   input参数
     * @return 交互式执行结果
     */
    public static OjExecuteMessage executeJavaCodeInteractive(Process process, String input) {
        int pid = getPID();
        CompletableFuture<Long> future = getMaxPIDMemoryAsync(pid);
        OjExecuteMessage executeMessage = new OjExecuteMessage();
        try {
            StopWatch stopWatch = new StopWatch();
            // 向进程输入数据
            OutputStream outputStream = process.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(input + "\n");
            // 刷新缓冲区
            outputStreamWriter.flush();
            // 回车后才会执行，开始计时
            stopWatch.start();
            // 获取进程的输出
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder executeOutputStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                executeOutputStringBuilder.append(line);
            }
            stopWatch.stop();
            executeMessage.setExitCode(process.exitValue());
            // 设置执行信息
            executeMessage.setMessage(executeOutputStringBuilder.toString());
            // 设置执行时间
            executeMessage.setTime(stopWatch.getTotalTimeMillis());
            // 设置执行内存消耗
            Long memory = future.get();
            executeMessage.setMemory(memory == -1L ? 0L : memory);
            // 等待进程退出
            outputStreamWriter.close();
            outputStream.close();
            inputStream.close();
            process.destroy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return executeMessage;
    }


    private static CompletableFuture<Long> getMaxPIDMemoryAsync(int pid) {
        return CompletableFuture.supplyAsync(() -> {
            int PID = -1;
            if (pid != -1) {
                PID = pid;
            } else {
                int loopCount = 10;
                while (PID == -1 && loopCount-- > 0) {
                    PID = getPID();
                }
            }
            // System.out.println("PID:" + PID);
            long maxPIDMemory = -1L;
            // 获取进程的最大内存消耗量（KB）
            while (true) {
                long pidMemory = getPIDMemory(PID);
                if (pidMemory > maxPIDMemory) {
                    maxPIDMemory = pidMemory;
                } else if (pidMemory == -1) {
                    break;
                }
            }
            // System.out.println("maxPIDMemory:" + maxPIDMemory);
            return maxPIDMemory;
        });
    }

    /**
     * 初始化系统信息
     */
    private static final OperatingSystem operatingSystem;

    static {
        SystemInfo systemInfo = new SystemInfo();
        operatingSystem = systemInfo.getOperatingSystem();
    }

    /**
     * 获取内存
     *
     * @param pid 进程id
     * @return 内存
     */
    private static long getPIDMemory(int pid) {
        if (pid == -1) {
            return -1;
        }
        // 获取进程
        OSProcess process = operatingSystem.getProcess(pid);
        if (process != null) {
            // System.out.println("getResidentSetSize:" + FormatUtil.formatBytes(process.getResidentSetSize()));
            // System.out.println("getVirtualSize:" + FormatUtil.formatBytes(process.getVirtualSize()));
            // 获取进程的最大内存消耗量（KB）
            return (process.getResidentSetSize() / 1024);
        } else {
            return -1;
        }
    }

    /**
     * 获取进程id
     *
     * @return 进程id
     */
    private static int getPID() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("jps");
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int pid = -1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.endsWith(JAVA_CLASS_NAME_WITHOUT_SUFFIX)) {
                    pid = Integer.parseInt(line.split(" ")[0]);
                    break;
                }
            }
            bufferedReader.close();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return pid;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}
