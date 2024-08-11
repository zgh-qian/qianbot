package com.qian.qianbotbackend.model.codesandbox;

import lombok.Data;

/**
 * 进程执行信息
 */
@Data
public class OjExecuteMessage {
    /**
     * 错误码
     */
    private Integer exitCode;

    /**
     * 进程输出信息
     */
    private String message;

    /**
     * 执行时间
     */
    private Long time;

    /**
     * 内存消耗
     */
    private Long memory;
}
