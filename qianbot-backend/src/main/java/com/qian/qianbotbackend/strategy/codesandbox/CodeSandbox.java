package com.qian.qianbotbackend.strategy.codesandbox;


import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;

/**
 * 代码沙箱接口
 */
public interface CodeSandbox {

    /**
     * 执行代码沙箱
     *
     * @param codeExecuteRequest 请求参数
     * @return 执行结果
     */
    CodeExecuteResponse execute(CodeExecuteRequest codeExecuteRequest);
}
