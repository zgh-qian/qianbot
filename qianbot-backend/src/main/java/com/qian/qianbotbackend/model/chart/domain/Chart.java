package com.qian.qianbotbackend.model.chart.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 图表信息表
 *
 * @TableName chart
 */
@TableName(value = "chart")
@Data
public class Chart implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 图表数据
     */
    private String chartData;

    /**
     * 生成的图表数据
     */
    private String genData;

    /**
     * 生成的分析结论
     */
    private String genResult;

    /**
     * 状态，0-待生成，1-生成中，2-已生成，3-已失败
     */
    private Integer chartStatus;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}