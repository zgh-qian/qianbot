package com.qian.qianbotbackend.model.app.dto.appresult;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 评分结果
 */
@Data
public class AppresultAddRequest implements Serializable {
    /**
     * 结果名称
     */
    private String resultName;

    /**
     * 结果图片
     */
    private String resultPic;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 结果属性集合(JSON数组)
     */
    private List<String> resultProp;

    /**
     * 结果得分
     */
    private Integer resultScore;

    /**
     * 应用id
     */
    private Long appId;

    private static final long serialVersionUID = 1L;
}