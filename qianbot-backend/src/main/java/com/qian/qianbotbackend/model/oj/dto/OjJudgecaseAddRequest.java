package com.qian.qianbotbackend.model.oj.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OjJudgecaseAddRequest implements Serializable {
    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

    /**
     * 题目id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}