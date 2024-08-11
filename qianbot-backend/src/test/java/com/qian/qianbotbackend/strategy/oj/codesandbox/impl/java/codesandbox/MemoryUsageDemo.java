package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.codesandbox;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class MemoryUsageDemo {

    public static void main(String[] args) {
        // 获取方法执行前的内存使用量
        long beforeUsedMemory = getMemoryUsage();
        // 执行目标方法，这里用一个示例方法进行演示
        runExampleMethod();
        // 获取方法执行后的内存使用量
        long afterUsedMemory = getMemoryUsage();
        // 计算内存使用量的差值
        long memoryUsageDiff = afterUsedMemory - beforeUsedMemory;
        System.out.println("Memory used before: " + beforeUsedMemory / 1024 + " kb");
        System.out.println("Memory used after : " + afterUsedMemory / 1024 + " kb");
        System.out.println("Memory used diff  : " + memoryUsageDiff / 1024 + " kb");
    }

    // 示例方法，可以替换成你要测试的具体方法
    public static void runExampleMethod() {
        // 这里简单模拟一些对象的创建和处理
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        }
    }

    // 获取当前JVM的内存使用量（堆内存 + 非堆内存）
    private static long getMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long heapMemoryUsed = memoryBean.getHeapMemoryUsage().getUsed();
        long nonHeapMemoryUsed = memoryBean.getNonHeapMemoryUsage().getUsed();
        return heapMemoryUsed + nonHeapMemoryUsed;
    }
}
