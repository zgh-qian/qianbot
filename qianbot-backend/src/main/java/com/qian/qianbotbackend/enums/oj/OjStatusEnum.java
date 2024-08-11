package com.qian.qianbotbackend.enums.oj;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum OjStatusEnum {

    // 正确答案
    ACCEPTED("Accepted", "AC"),

    // 错误答案
    WRONG_ANSWER("Wrong Answer", "WA"),

    // 编译错误
    COMPILE_ERROR("Compile Error", "CE"),

    // 内存超限
    MEMORY_LIMIT_EXCEEDED("Memory Limit Exceeded", "MLE"),

    // 时间超限
    TIME_LIMIT_EXCEEDED("Time Limit Exceeded", "TLE"),

    // 输出溢出
    OUTPUT_LIMIT_EXCEEDED("Output Limit Exceeded", "OLE"),

    // 等待判题
    PENDING("Pending", "PE"),

    // 危险系统调用
    Dangerous_System_Call("Dangerous System Call", "DSC"),

    // 系统错误
    SYSTEM_ERROR("System Error", "SE"),

    // 运行时错误
    RUNTIME_ERROR("Runtime Error", "RE"),

    // 未知错误
    UNKNOWN("Unknown", "UK");

    private final String text;

    private final String value;

    OjStatusEnum(String text, String value) {
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
    public static OjStatusEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (OjStatusEnum anEnum : OjStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}

