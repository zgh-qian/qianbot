package com.qian.qianbotbackend.strategy.oj.codesandbox;

import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理类，增强代码沙箱类
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {
    private final CodeSandbox codeSandBox;


    public CodeSandboxProxy(CodeSandbox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public OjExecutionCodeResponse execute(OjExecutionCodeRequest ojExecutionCodeRequest) {
        //log.info("代码沙箱请求信息：{}", ojExecutionCodeRequest);
        //log.info("代码沙箱响应信息：{}", ojExecutionCodeResponse);
        return codeSandBox.execute(ojExecutionCodeRequest);
    }
}
