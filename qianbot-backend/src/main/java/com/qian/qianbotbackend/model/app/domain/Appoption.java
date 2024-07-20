package com.qian.qianbotbackend.model.app.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 选项
 *
 * @TableName appoption
 */
@TableName(value = "appoption")
@Data
public class Appoption implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 选项图片
     */
    private String optionPic;

    /**
     * 选项键
     */
    private String optionKey;

    /**
     * 选项
     */
    private String optionName;

    /**
     * 选项结果
     */
    private String optionResult;

    /**
     * 问题id
     */
    private Long questionId;

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