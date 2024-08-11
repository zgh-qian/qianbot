package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java;

import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class CodeSandboxJavaNativeImplTest {

    @Test
    void execute() {
        OjExecutionCodeRequest ojExecutionCodeRequest = new OjExecutionCodeRequest();
        ojExecutionCodeRequest.setInputList(new ArrayList<>(Arrays.asList("1 2", "3 4")));
        String code = "import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n" +
                "        // 读取两个整数\n" +
                "        int num1 = scanner.nextInt();\n" +
                "        int num2 = scanner.nextInt();\n" +
                "        // 计算并输出结果\n" +
                "        int result = num1 + num2;\n" +
                "        System.out.println(result);\n" +
                "        scanner.close();\n" +
                "    }\n" +
                "}\n";
        ojExecutionCodeRequest.setCode(code);
        ojExecutionCodeRequest.setLanguage("java");
        OjJudgeConfig ojJudgeConfig = new OjJudgeConfig();
        ojJudgeConfig.setTimeLimit(3000L);
        ojJudgeConfig.setMemoryLimit(30000L);
        ojExecutionCodeRequest.setOjJudgeConfig(ojJudgeConfig);
        CodeSandboxJavaNativeImpl codeSandboxJavaNative = new CodeSandboxJavaNativeImpl();
        OjExecutionCodeResponse execute = codeSandboxJavaNative.execute(ojExecutionCodeRequest);
        System.out.println(execute);
    }
}