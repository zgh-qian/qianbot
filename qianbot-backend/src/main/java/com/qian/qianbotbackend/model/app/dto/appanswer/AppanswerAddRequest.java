package com.qian.qianbotbackend.model.app.dto.appanswer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户答题记录
 */
@Data
public class AppanswerAddRequest implements Serializable {
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

    private static final long serialVersionUID = 1L;
}