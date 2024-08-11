package com.qian.qianbotbackend.strategy.codesandbox;

import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
public class CodeSandboxProxy implements CodeSandbox {
    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public CodeExecuteResponse execute(CodeExecuteRequest codeExecuteRequest) {
        log.info("代码沙箱请求信息：{}", codeExecuteRequest);
        CodeExecuteResponse codeExecuteResponse = codeSandbox.execute(codeExecuteRequest);
        log.info("代码沙箱响应信息：{}", codeExecuteResponse);
        return codeExecuteResponse;
    }
}
