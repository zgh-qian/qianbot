package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.codesandbox;

public class MemoryInfo {
    public static void main(String[] args) {
       /* int sum = 0;
        for (int i = 1; i < 1000000; i++) {
            String str = "Hello World" + i;
            sum += i;
        }
        System.out.println(sum);*/
        for (int i = 1; i < 1000000; i++) {
        }
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory(); // Java虚拟机试图使用的最大内存
        long totalMemory = runtime.totalMemory(); // 当前已经申请的内存
        long freeMemory = runtime.freeMemory(); // 当前已经申请的内存中的剩余部分
        long usedMemory = totalMemory - freeMemory; // 当前已经使用的内存

        System.out.println("最大内存: " + maxMemory / 1024 / 1024 + " mb");
        System.out.println("总内存: " + totalMemory / 1024 / 1024 + " mb");
        System.out.println("可用内存: " + freeMemory / 1024 / 1024 + " mb");
        System.out.println("已使用内存: " + usedMemory / 1024 / 1024 + " mb");
    }
}