package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java;

import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;

/**
 * Java原生代码沙箱实现，直接调用父类方法
 */
public class CodeSandboxJavaNativeImpl extends CodeSandboxJavaTemplate {
    @Override
    public OjExecutionCodeResponse execute(OjExecutionCodeRequest ojExecutionCodeRequest) {
        return super.execute(ojExecutionCodeRequest);
    }
}
