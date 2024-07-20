package com.qian.qianbotbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication()
@MapperScan("com.qian.qianbotbackend.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class QianbotBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QianbotBackendApplication.class, args);
    }

}
