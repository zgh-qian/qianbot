package com.qian.qianbotbackend.strategy.codesandbox.impl;

import cn.hutool.core.io.resource.ResourceUtil;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class CodeSandboxTemplateRemoteImplTest {
    @Test
    void testJava() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.java", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("java");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testCpp() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.cpp", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testC() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.c", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testPython() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.py", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testJs() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.js", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testTs() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.ts", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("cpp");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }

    @Test
    void testGo() {
        CodeSandboxTemplateRemoteImpl codeSandboxTemplateRemote = new CodeSandboxTemplateRemoteImpl();
        String code = ResourceUtil.readStr("codesandbox/Main.go", StandardCharsets.UTF_8);
        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
        codeExecuteRequest.setCode(code);
        codeExecuteRequest.setLanguage("go");
        CodeExecuteResponse execute = codeSandboxTemplateRemote.execute(codeExecuteRequest);
        System.out.println(execute);
    }
}