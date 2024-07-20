package com.qian.qianbotbackend.model.app.vo;

import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.model.app.domain.Appresult;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评分结果
 */
@Data
public class AppresultVO implements Serializable {
    /**
     * id
     */
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
    private List<String> resultProp;

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
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public static AppresultVO objToVO(Appresult appresult) {
        if (appresult == null) {
            return null;
        }
        AppresultVO appresultVO = new AppresultVO();
        BeanUtils.copyProperties(appresult, appresultVO);
        String resultProp = appresult.getResultProp();
        if (resultProp != null) {
            appresultVO.setResultProp(JSONUtil.toList(resultProp, String.class));
        }
        return appresultVO;
    }
}