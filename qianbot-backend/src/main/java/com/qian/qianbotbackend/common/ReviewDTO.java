package com.qian.qianbotbackend.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewDTO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 审核状态：0-待审核,1-审核中,2-通过,3-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

    private static final long serialVersionUID = 1L;
}
