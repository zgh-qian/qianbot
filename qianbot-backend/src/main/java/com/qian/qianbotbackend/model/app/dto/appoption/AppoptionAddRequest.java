package com.qian.qianbotbackend.model.app.dto.appoption;

import lombok.Data;

import java.io.Serializable;

/**
 * 选项
 */
@Data
public class AppoptionAddRequest implements Serializable {
    /**
     * 选项图片
     */
    private String optionPic;

    /**
     * 选项键
     */
    private String optionKey;

    /**
     * 选项
     */
    private String optionName;

    /**
     * 选项结果
     */
    private String optionResult;

    /**
     * 问题id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}