package com.qian.qianbotbackend.model.oj.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.model.oj.domain.OjQuestion;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 题目表
 */
@Data
public class OjQuestionVO {
    /**
     * id
     */
    private Long id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 标签(json数组)
     */
    private List<String> tags;

    /**
     * 难度，0-简单，1-中等，2-困难
     */
    private Integer difficulty;

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

    public static OjQuestionVO objToVO(OjQuestion ojQuestion) {
        if (ojQuestion == null) {
            return null;
        }
        OjQuestionVO ojQuestionVO = BeanUtil.copyProperties(ojQuestion, OjQuestionVO.class);
        String tags = ojQuestion.getTags();
        if (tags != null) {
            ojQuestionVO.setTags(JSONUtil.toList(tags, String.class));
        }
        return ojQuestionVO;
    }
}