package com.qian.qianbotbackend.model.oj.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OjQuestionDetailVO {
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
     * 提示(json数组)
     */
    private List<String> tips;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
