package com.qian.qianbotbackend.model.app.vo;

import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户答题记录
 */
@Data
public class AppanswerVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 用户答案(JSON 数组)
     */
    private List<Long> userAnswer;

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

    private static final long serialVersionUID = 1L;

    public static AppanswerVO objToVO(Appanswer appanswer) {
        if (appanswer == null) {
            return null;
        }
        AppanswerVO appanswerVO = new AppanswerVO();
        BeanUtils.copyProperties(appanswer, appanswerVO);
        String userAnswer = appanswer.getUserAnswer();
        if (StringUtils.isNotBlank(userAnswer)) {
            appanswerVO.setUserAnswer(JSONUtil.toList(userAnswer, Long.class));
        }
        return appanswerVO;
    }
}