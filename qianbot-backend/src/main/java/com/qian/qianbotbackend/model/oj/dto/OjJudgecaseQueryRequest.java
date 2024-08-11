package com.qian.qianbotbackend.model.oj.dto;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class OjJudgecaseQueryRequest extends PageRequest implements Serializable {
    /**
     * 题目id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}