package com.qian.qianbotbackend.enums.oj;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OJ题目难度枚举
 */
@Getter
public enum OjQuestionDifficultyEnum {

    OJ_QUESTION_DIFFICULTY_ENUM_EASY("简单", 0),

    OJ_QUESTION_DIFFICULTY_ENUM_MEDIUM("中等", 1),

    OJ_QUESTION_DIFFICULTY_ENUM_DIFF("困难", 2);


    private final String text;

    private final Integer value;

    OjQuestionDifficultyEnum(String text, Integer value) {
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
    public static OjQuestionDifficultyEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (OjQuestionDifficultyEnum anEnum : OjQuestionDifficultyEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
