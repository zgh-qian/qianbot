package com.qian.qianbotbackend.model.oj.vo;

import cn.hutool.core.bean.BeanUtil;
import com.qian.qianbotbackend.model.oj.domain.OjJudgecase;
import lombok.Data;

import java.util.Date;


@Data
public class OjJudgecaseVO {
    /**
     * id
     */
    private Long id;

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;

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

    public static OjJudgecaseVO objToVO(OjJudgecase ojJudgecase) {
        if (ojJudgecase == null) {
            return null;
        }
        return BeanUtil.copyProperties(ojJudgecase, OjJudgecaseVO.class);
    }
}