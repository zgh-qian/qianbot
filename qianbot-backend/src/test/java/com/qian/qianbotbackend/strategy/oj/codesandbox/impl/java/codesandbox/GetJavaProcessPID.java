package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.codesandbox;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class GetJavaProcessPID {
    public static void main(String[] args) throws IOException {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String jvmName = runtimeMXBean.getName();
        int pid = Integer.parseInt(jvmName.split("@")[0]);
        System.out.println("Java process PID: " + pid);
    }
}
