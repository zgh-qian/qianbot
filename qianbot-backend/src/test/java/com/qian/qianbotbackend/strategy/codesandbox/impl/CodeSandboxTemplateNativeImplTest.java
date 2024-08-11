package com.qian.qianbotbackend.strategy.codesandbox.impl;

import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import org.junit.jupiter.api.Test;

class CodeSandboxTemplateNativeImplTest {
    @Test
    void test() {
        CodeSandboxTemplateNativeImpl codeSandboxTemplate = new CodeSandboxTemplateNativeImpl();
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "        while (true){\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "}");
        codeExecuteRequest.setLanguage("java");
        CodeExecuteResponse execute = codeSandboxTemplate.execute(codeExecuteRequest);
        System.out.println(execute);
    }
}