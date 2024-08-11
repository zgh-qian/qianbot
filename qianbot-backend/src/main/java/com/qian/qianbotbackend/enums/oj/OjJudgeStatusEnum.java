package com.qian.qianbotbackend.enums.oj;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OJ判题状态枚举
 */
@Getter
public enum OjJudgeStatusEnum {

    OJ_JUDGE_STATUS_ENUM_WAITING("待判题", 0),

    OJ_JUDGE_STATUS_ENUM_RUNNING("判题中", 1),

    OJ_JUDGE_STATUS_ENUM_SUCCESS("判题成功", 2),

    OJ_JUDGE_STATUS_ENUM_FAILURE("判题失败", 3);

    private final String text;

    private final Integer value;

    OjJudgeStatusEnum(String text, Integer value) {
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
    public static OjJudgeStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (OjJudgeStatusEnum anEnum : OjJudgeStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
