package com.qian.qianbotbackend.model.app.vo;

import com.qian.qianbotbackend.model.app.domain.Appquestion;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 题目
 */
@Data
public class AppquestionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题目图片
     */
    private String questionPic;

    /**
     * 题目
     */
    private String questionName;

    private static final long serialVersionUID = 1L;

    public static AppquestionVO objToVO(Appquestion appquestion) {
        if (appquestion == null) {
            return null;
        }
        AppquestionVO appquestionVO = new AppquestionVO();
        BeanUtils.copyProperties(appquestion, appquestionVO);
        return appquestionVO;
    }
}