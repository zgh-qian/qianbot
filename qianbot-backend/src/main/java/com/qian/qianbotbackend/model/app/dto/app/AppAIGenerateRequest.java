package com.qian.qianbotbackend.model.app.dto.app;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppAIGenerateRequest implements Serializable {

    private Long appId;

    private Integer questionNumber = 10;

    private Integer optionNumber = 2;

    private static final long serialVersionUID = 1L;
}