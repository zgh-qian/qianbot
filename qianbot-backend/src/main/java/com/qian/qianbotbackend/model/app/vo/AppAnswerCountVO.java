package com.qian.qianbotbackend.model.app.vo;

import lombok.Data;


@Data
public class AppAnswerCountVO {
    /**
     * id
     */
    private Long appId;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * 应用图标
     */
    private String appIcon;

    /**
     * 回答数量
     */
    private Long answerCount;
}
