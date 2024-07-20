package com.qian.qianbotbackend.model.user.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户使用表
 *
 * @TableName userusage
 */
@TableName(value = "userusage")
@Data
public class UserUsage implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 使用id
     */
    private Long usageId;

    /**
     * 使用类型
     */
    private String usageType;

    /**
     * 使用次数
     */
    private Integer usedCount;

    /**
     * 剩余次数
     */
    private Integer remainingCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户id
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