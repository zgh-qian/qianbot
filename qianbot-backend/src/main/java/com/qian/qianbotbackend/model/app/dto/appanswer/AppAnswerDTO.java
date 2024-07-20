package com.qian.qianbotbackend.model.app.dto.appanswer;

import com.qian.qianbotbackend.model.app.domain.App;
import lombok.Data;

import java.util.List;

@Data
public class AppAnswerDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 用户答案(JSON 数组)
     */
    private List<Long> userAnswer;

    /**
     * app
     */
    private App app;
}
