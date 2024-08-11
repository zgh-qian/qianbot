package com.qian.qianbotbackend.strategy.oj.codesandbox.impl;

import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;
import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandbox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandboxRemoteImpl implements CodeSandbox {
    @Override
    public OjExecutionCodeResponse execute(OjExecutionCodeRequest ojExecutionCodeRequest) {
        log.info("CodeSandboxRemoteImpl execute: {}", ojExecutionCodeRequest);
        return null;
    }
}
