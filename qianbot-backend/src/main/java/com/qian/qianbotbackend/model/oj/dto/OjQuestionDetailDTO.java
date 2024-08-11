package com.qian.qianbotbackend.model.oj.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OjQuestionDetailDTO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 标签(json数组)
     */
    private List<String> tags;

    /**
     * 难度，0-简单，1-中等，2-困难
     */
    private Integer difficulty;

    /**
     * 时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 内存限制(kb)
     */
    private Long memoryLimit;

    /**
     * 内容
     */
    private String content;

    /**
     * 模板代码(json)
     */
    private List<String> template;

    /**
     * 题目答案(json)
     */
    private List<String> answer;

    /**
     * 提示(json数组)
     */
    private List<String> tips;

    private static final long serialVersionUID = 1L;
}
