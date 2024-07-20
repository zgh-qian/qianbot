package com.qian.qianbotbackend.model.app.vo;

import com.qian.qianbotbackend.model.app.domain.Appoption;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 选项
 */
@Data
public class AppoptionVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 选项图片
     */
    private String optionPic;

    /**
     * 选项键
     */
    private String optionKey;

    /**
     * 选项
     */
    private String optionName;

    /**
     * 选项结果
     */
    private String optionResult;

    private static final long serialVersionUID = 1L;

    public static AppoptionVO objToVO(Appoption appoption) {
        if (appoption == null) {
            return null;
        }
        AppoptionVO appoptionVO = new AppoptionVO();
        BeanUtils.copyProperties(appoption, appoptionVO);
        return appoptionVO;
    }
}