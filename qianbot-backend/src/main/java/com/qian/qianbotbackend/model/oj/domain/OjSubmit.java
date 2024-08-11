package com.qian.qianbotbackend.model.oj.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 题目提交表
 *
 * @TableName ojsubmit
 */
@TableName(value = "ojsubmit")
@Data
public class OjSubmit implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 代码
     */
    private String code;

    /**
     * 运行结果，比如：AC
     */
    private String status;

    /**
     * 运行结果信息
     */
    private String detail;

    /**
     * 时间消耗(ms)
     */
    private Long timeUsed;

    /**
     * 内存消耗(kb)
     */
    private Long memoryUsed;

    /**
     * 判题状态：0-待判题，1-判题中，2-判题成功，3-判题失败
     */
    private Integer judgeStatus;

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