package com.qian.qianbotbackend.model.oj.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 题目详情表
 * @TableName ojdetail
 */
@TableName(value ="ojdetail")
@Data
public class OjDetail implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 内存限制(kb)
     */
    private Long memoryLimit;

    /**
     * 内容
     */
    private String content;

    /**
     * 模板代码(json)
     */
    private String template;

    /**
     * 题目答案(json)
     */
    private String answer;

    /**
     * 提示(json数组)
     */
    private String tips;

    /**
     * 题目id
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