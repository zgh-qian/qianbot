package com.qian.qianbotbackend.model.oj.dto;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OjQuestionQueryRequest extends PageRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}