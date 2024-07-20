package com.qian.qianbotbackend.enums;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色枚举
 */
@Getter
public enum ChartStatusEnum {

    CHART_STATUS_ENUM_WAITING("待生成", 0),

    CHART_STATUS_ENUM_RUNNING("生成中", 1),

    CHART_STATUS_ENUM_SUCCESS("已生成", 2),

    CHART_STATUS_ENUM_FAILURE("已失败", 3);

    private final String text;

    private final Integer value;

    ChartStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value value
     * @return 枚举
     */
    public static ChartStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (ChartStatusEnum anEnum : ChartStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
