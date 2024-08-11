package com.qian.qianbotbackend.strategy.oj.codesandbox;


import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;

/**
 * 代码沙箱接口
 */
public interface CodeSandbox {

    /**
     * 执行代码沙箱
     *
     * @param ojExecutionCodeRequest 请求参数
     * @return 执行结果
     */
    OjExecutionCodeResponse execute(OjExecutionCodeRequest ojExecutionCodeRequest);

}
