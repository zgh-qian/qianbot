package com.qian.qianbotbackend.strategy.app;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface AppStrategyConfig {
    /**
     * 应用类型
     */
    int appType();

    /**
     * 评分策略
     */
    int scoringStrategy();
}
