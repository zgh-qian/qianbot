package com.qian.qianbotbackend.enums.app;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用类型枚举
 */
@Getter
public enum AppAnswerStatusEnum {

    WAITING("待判断", 0),
    JUDGING("判断中", 1),
    SUCCESS("已完成", 2),
    FAILURE("已失败", 3);

    private final String text;

    private final Integer value;

    AppAnswerStatusEnum(String text, Integer value) {
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
     * @param value 值
     * @return 枚举
     */
    public static AppAnswerStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (AppAnswerStatusEnum anEnum : AppAnswerStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
