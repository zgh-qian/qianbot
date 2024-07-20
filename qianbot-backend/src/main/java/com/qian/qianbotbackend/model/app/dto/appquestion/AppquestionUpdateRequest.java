package com.qian.qianbotbackend.model.app.dto.appquestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目
 */
@Data
public class AppquestionUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目图片
     */
    private String questionPic;

    /**
     * 题目
     */
    private String questionName;

    private static final long serialVersionUID = 1L;
}