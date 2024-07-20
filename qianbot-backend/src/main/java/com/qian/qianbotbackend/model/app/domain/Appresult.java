package com.qian.qianbotbackend.model.app.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评分结果
 * @TableName appresult
 */
@TableName(value ="appresult")
@Data
public class Appresult implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

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
    private String resultProp;

    /**
     * 结果得分
     */
    private Integer resultScore;

    /**
     * 应用id
     */
    private Long appId;

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