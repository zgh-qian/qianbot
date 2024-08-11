package com.qian.qianbotbackend.strategy.codesandbox.impl;

import cn.hutool.core.io.resource.ResourceUtil;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class CodeSandboxTemplateDockerImplTest {
    @Resource
    private CodeSandboxTemplateDockerImpl codeSandboxTemplateDocker;

    @Test
    void testJava() {
        String code = ResourceUtil.readStr("codesandbox/Main.java", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("java");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testCpp() {
        String code = ResourceUtil.readStr("codesandbox/Main.cpp", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testC() {
        String code = ResourceUtil.readStr("codesandbox/Main.c", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testPython() {
        String code = ResourceUtil.readStr("codesandbox/Main.py", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testJs() {
        String code = ResourceUtil.readStr("codesandbox/Main.js", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testTs() {
        String code = ResourceUtil.readStr("codesandbox/Main.ts", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testGo() {
        String code = ResourceUtil.readStr("codesandbox/Main.go", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("go");
        CodeExecuteResponse execute = codeSandboxTemplateDocker.execute(codeExecuteRequest);
        System.out.println(execute);
    }
}