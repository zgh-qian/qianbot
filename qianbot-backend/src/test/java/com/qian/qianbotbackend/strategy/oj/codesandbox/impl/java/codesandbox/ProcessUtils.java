package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.codesandbox;

import ch.qos.logback.core.util.SystemInfo;
import cn.hutool.core.date.StopWatch;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.io.IOException;
import java.util.List;

public class ProcessUtils {
    public static int getProcessId(Process process) {
        int hashCode = process.hashCode();
        String string = process.toString();
        oshi.SystemInfo systemInfo = new oshi.SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();
        // 获取所有正在运行的进程
        List<OSProcess> processList = os.getProcesses(10, OperatingSystem.ProcessSort.CPU);
        // 查找指定进程的 PID
        for (OSProcess p : processList) {
            String processName = p.getName();
            System.out.println(processName + ":" + p.getProcessID());
            if (processName.equals("java")) {
                System.out.println(p);
                return p.getProcessID();
            }
        }
        return -1; // 如果未找到则返回 -1
    }

    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("jps");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int pid = getProcessId(process);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println("Process ID: " + pid);
    }
}
