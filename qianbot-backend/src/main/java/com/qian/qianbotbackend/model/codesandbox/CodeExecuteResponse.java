package com.qian.qianbotbackend.model.codesandbox;

import lombok.Data;

@Data
public class CodeExecuteResponse {
    /**
     * 退出码
     */
    private int exitCode;

    /**
     * 执行信息
     */
    private String message;

    /**
     * 执行时间
     */
    private String timeUsed;

    /**
     * 内存使用
     */
    private String memoryUsed;
}
