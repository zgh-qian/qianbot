package com.qian.qianbotbackend.enums.user;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色枚举
 */
@Getter
public enum UserUsageEnum {

    USER_USAGE_ENUM_COS("cos", 1000),
    USER_USAGE_ENUM_APP_AI("app_ai", 10),
    USER_USAGE_ENUM_CHART("chart_ai", 10);

    private final String text;

    private final Integer value;

    UserUsageEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    public static UserUsageEnum getEnumByText(String text) {
        for (UserUsageEnum enumItem : UserUsageEnum.values()) {
            if (enumItem.getText().equalsIgnoreCase(text)) {
                return enumItem;
            }
        }
        return null;
    }
}
