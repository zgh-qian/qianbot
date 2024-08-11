package com.qian.qianbotbackend.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AIManagerTest {
    @Resource
    private AIManager aiManager;

    @Test
    void test(){
        String s = aiManager.doSyncStableRequest("你好，你是一个作家", "写一个小说的框架");
        System.out.println(s);
    }
}