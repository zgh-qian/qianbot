package com.qian.qianbotbackend.model.codesandbox;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeExecuteRequest implements Serializable {
    /**
     * 代码
     */
    private String code;

    /**
     * 编程语言
     */
    private String language;

    private static final long serialVersionUID = 1L;
}
