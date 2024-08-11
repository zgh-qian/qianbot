package com.qian.qianbotbackend.model.oj.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OjJudgecaseUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

    private static final long serialVersionUID = 1L;
}