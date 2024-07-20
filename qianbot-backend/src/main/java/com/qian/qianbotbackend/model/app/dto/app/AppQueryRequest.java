package com.qian.qianbotbackend.model.app.dto.app;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 应用
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * 应用类型（0-得分类，1-测评类）
     */
    private Integer appType;

    /**
     * 评分策略（0-自定义，1-AI）
     */
    private Integer scoringStrategy;

    /**
     * 审核状态：0-待审核,1-审核中,2-通过,3-拒绝
     */
    private Integer reviewStatus;

    /**
     * 创建用户id
     */
    private Long userId;

    private String searchText;

    private static final long serialVersionUID = 1L;
}