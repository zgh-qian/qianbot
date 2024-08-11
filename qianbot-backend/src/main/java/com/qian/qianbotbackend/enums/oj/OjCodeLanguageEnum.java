package com.qian.qianbotbackend.enums.oj;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum OjCodeLanguageEnum {

    OJ_CODE_LANGUAGE_ENUM_JAVA("Java", "java"),
    OJ_CODE_LANGUAGE_ENUM_PYTHON("Python", "python"),
    OJ_CODE_LANGUAGE_ENUM_C("C", "c"),
    OJ_CODE_LANGUAGE_ENUM_CPP("C++", "cpp"),
    OJ_CODE_LANGUAGE_ENUM_GO("Go", "go");

    private final String text;

    private final String value;

    OjCodeLanguageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static OjCodeLanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (OjCodeLanguageEnum anEnum : OjCodeLanguageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}

