package com.qian.qianbotbackend.model.app.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户答题记录
 * @TableName appanswer
 */
@TableName(value ="appanswer")
@Data
public class Appanswer implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 用户答案(JSON 数组)
     */
    private String userAnswer;

    /**
     * 结果状态，0-待判断，1-判断中，2-已完成，3-已失败
     */
    private Integer resultStatus;

    /**
     * 评分结果id
     */
    private Long resultId;

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
     * 结果图片
     */
    private String resultPic;

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