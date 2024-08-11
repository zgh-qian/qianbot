package com.qian.qianbotbackend.enums.codesandbox;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum CodeSandboxLanguageEnum {

    JAVA(
            "Java",
            "java",
            "java",
            new String[]{"javac", "-encoding", "utf-8", "Main.java"},// javac -encoding utf-8 Main.java
            new String[]{"java", "-Dfile.encoding=UTF-8", "Main"},// java -Dfile.encoding=UTF-8 Main
            "mvn dependency:get -Dartifact=%s",
            null
    ),

    PYTHON3(
            "Python",
            "python",
            "py",
            null,
            new String[]{"python3", "Main.py"},// python3 Main.py
            "pip install -i https://pypi.tuna.tsinghua.edu.cn/simple %s",
            "pip uninstall -y %s"
    ),

    GO(
            "Go",
            "go",
            "go",
            null,
            new String[]{"go", "run", "Main.go"},// go run Main.go
            "go get -u %s",
            "go clean -i %s"
    ),

    C(
            "C",
            "c",
            "c",
            new String[]{"gcc", "-finput-charset=UTF-8", "-fexec-charset=UTF-8", "-o", "Main", "Main.c"},// gcc -finput-charset=UTF-8 -fexec-charset=UTF-8 -o Main Main.c
            new String[]{"./Main"},// ./Main
            null,
            null
    ),

    CPP(
            "C++",
            "cpp",
            "cpp",
            new String[]{"g++", "-o", "-finput-charset=UTF-8", "-fexec-charset=UTF-8", "-o", "Main", "Main.cpp"},// g++ -o -finput-charset=UTF-8 -fexec-charset=UTF-8 -o Main Main.cpp
            new String[]{"./Main"},// ./Main
            null,
            null
    ),

    JAVASCRIPT(
            "JavaScript",
            "javascript",
            "js",
            null,
            new String[]{"node", "Main.js"},
            "npm install -g --registry=https://registry.npmmirror.com/ %s",
            "npm uninstall -y %s"
    ),

    TYPESCRIPT(
            "TypeScript",
            "typescript",
            "ts",
            null,
            new String[]{"node", "Main.ts"},
            "npm install -g --registry=https://registry.npmmirror.com/ %s",
            "npm uninstall -y %s"
    );

    private final String text;

    private final String value;

    private final String suffix;

    private final String[] compileCmd;

    private final String[] runCmd;

    private final String installCmd;

    private final String uninstallCmd;

    CodeSandboxLanguageEnum(String text, String value, String suffix, String[] compileCmd, String[] runCmd, String installCmd, String uninstallCmd) {
        this.text = text;
        this.value = value;
        this.suffix = suffix;
        this.compileCmd = compileCmd;
        this.runCmd = runCmd;
        this.installCmd = installCmd;
        this.uninstallCmd = uninstallCmd;
    }

    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public static List<CodeSandboxLanguage> getTextAndValues() {
        return Arrays.stream(values()).map(item -> {
            CodeSandboxLanguage codeSandboxLanguage = new CodeSandboxLanguage();
            codeSandboxLanguage.setText(item.getText());
            codeSandboxLanguage.setValue(item.getValue());
            codeSandboxLanguage.setTemplateCode(ResourceUtil.readStr("codesandbox/Main." + item.getSuffix(), StandardCharsets.UTF_8));
            return codeSandboxLanguage;
        }).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static CodeSandboxLanguageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        // 转小写
        value = value.toLowerCase();
        for (CodeSandboxLanguageEnum anEnum : CodeSandboxLanguageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    @Data
    public static class CodeSandboxLanguage {
        public String text;
        public String value;
        public String templateCode;
    }
}

