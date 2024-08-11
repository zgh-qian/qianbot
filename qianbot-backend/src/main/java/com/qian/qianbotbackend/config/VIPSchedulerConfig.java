package com.qian.qianbotbackend.config;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Configuration
public class VIPSchedulerConfig {
    @Bean
    public Scheduler vipSchedulers() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger();

            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r, "VIP-ThreadPool-" + threadNumber.getAndIncrement());
                thread.setDaemon(false);
                return thread;
            }
        };
        return Schedulers.from(Executors.newScheduledThreadPool(10, threadFactory));
    }
}
