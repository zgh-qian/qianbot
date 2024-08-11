package com.qian.qianbotbackend.model.oj.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.model.oj.domain.OjDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OjDetailVO {
    /**
     * id
     */
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
    private List<String> template;

    /**
     * 提示(json数组)
     */
    private List<String> tips;

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

    public static OjDetailVO objToVO(OjDetail ojDetail) {
        if (ojDetail == null) {
            return null;
        }
        OjDetailVO ojDetailVO = BeanUtil.copyProperties(ojDetail, OjDetailVO.class);
        String template = ojDetail.getTemplate();
        if (template != null) {
            ojDetailVO.setTemplate(JSONUtil.toList(template, String.class));
        }
        String tips = ojDetail.getTips();
        if (tips != null) {
            ojDetailVO.setTips(JSONUtil.toList(tips, String.class));
        }
        return ojDetailVO;
    }
}