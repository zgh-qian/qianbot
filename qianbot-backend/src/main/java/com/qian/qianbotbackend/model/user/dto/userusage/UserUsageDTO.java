package com.qian.qianbotbackend.model.user.dto.userusage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户使用表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUsageDTO implements Serializable {
    /**
     * 使用id
     */
    private Long usageId;

    /**
     * 使用类型
     */
    private String usageType;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}