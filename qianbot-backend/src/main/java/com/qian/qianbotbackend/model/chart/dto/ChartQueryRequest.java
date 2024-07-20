package com.qian.qianbotbackend.model.chart.dto;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 图表信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChartQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 图表名称
     */
    private String chartName;

    /**
     * 分析目标
     */
    private String chartGoal;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 状态，0-待生成，1-生成中，2-已生成，3-已失败
     */
    private Integer chartStatus;

    private static final long serialVersionUID = 1L;
}