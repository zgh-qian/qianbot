package com.qian.qianbotbackend.model.oj.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OjSubmitAddRequest implements Serializable {
    /**
     * 编程语言
     */
    private String language;

    /**
     * 代码
     */
    private String code;

    /**
     * 题目id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}