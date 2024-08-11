package com.qian.qianbotbackend.strategy.oj.codesandbox.impl;

import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;
import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandbox;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.CodeSandboxJavaDockerImpl;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.CodeSandboxJavaNativeImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandboxDefaultImpl implements CodeSandbox {
    @Override
    public OjExecutionCodeResponse execute(OjExecutionCodeRequest ojExecutionCodeRequest) {
        log.info("CodeSandboxDefaultImpl execute: {}", ojExecutionCodeRequest);
        return new CodeSandboxJavaNativeImpl().execute(ojExecutionCodeRequest);
        // return new CodeSandboxJavaDockerImpl().execute(ojExecutionCodeRequest);
    }
}
