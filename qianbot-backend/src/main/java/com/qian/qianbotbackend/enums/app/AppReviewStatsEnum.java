package com.qian.qianbotbackend.enums.app;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 审核状态枚举
 */
@Getter
public enum AppReviewStatsEnum {

    REVIEW_STATS_ENUM_WAITING("待审核", 0),

    REVIEW_STATS_ENUM_RUNNING("审核中", 1),

    REVIEW_STATS_ENUM_PASS("审核通过", 2),

    REVIEW_STATS_ENUM_REJECT("审核拒绝", 3);

    private final String text;

    private final Integer value;

    AppReviewStatsEnum(String text, Integer value) {
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
    public static AppReviewStatsEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (AppReviewStatsEnum anEnum : AppReviewStatsEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
