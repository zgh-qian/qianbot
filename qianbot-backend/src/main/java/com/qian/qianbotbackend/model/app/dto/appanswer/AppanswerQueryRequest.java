package com.qian.qianbotbackend.model.app.dto.appanswer;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户答题记录
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppanswerQueryRequest extends PageRequest implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 结果状态，0-待判断，1-判断中，2-已完成，3-已失败
     */
    private Integer resultStatus;

    /**
     * 结果名称
     */
    private String resultName;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 得分
     */
    private Integer resultScore;

    /**
     * 用户id
     */
    private Long userId;

    private String searchText;

    private static final long serialVersionUID = 1L;
}