package com.qian.qianbotbackend.model.oj.judge;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class OjJudgeInfo {
    /**
     * 状态
     */
    private String status;
    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 消耗时间(ms)
     */
    private Long time;

    /**
     * 消耗内存(KB)
     */
    private Long memory;

    /**
     * 出错的用例输入
     */
    private String errorInput;
}
