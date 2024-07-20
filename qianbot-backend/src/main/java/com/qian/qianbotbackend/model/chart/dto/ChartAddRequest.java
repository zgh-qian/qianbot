package com.qian.qianbotbackend.model.chart.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 图表信息表
 */
@Data
public class ChartAddRequest implements Serializable {
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
     * 图表数据
     */
    private String chartData;

    private static final long serialVersionUID = 1L;
}