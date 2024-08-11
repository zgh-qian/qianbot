package com.qian.qianbotbackend.model.oj.judge;

import lombok.Data;

@Data
public class OjJudgeConfig {
    /**
     * 时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 内存限制(KB)
     */
    private Long memoryLimit;
}
