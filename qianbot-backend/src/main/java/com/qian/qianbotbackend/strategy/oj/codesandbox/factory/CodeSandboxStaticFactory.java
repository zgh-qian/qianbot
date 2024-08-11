package com.qian.qianbotbackend.strategy.oj.codesandbox.factory;

import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandbox;
import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandboxProxy;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.CodeSandboxDefaultImpl;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.CodeSandboxRemoteImpl;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.CodeSandboxThirdPartyImpl;

/**
 * 代码沙箱静态工厂实现
 */
public class CodeSandboxStaticFactory {

    private CodeSandboxStaticFactory() {
    }

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "default":
                return new CodeSandboxDefaultImpl();
            case "remote":
                return new CodeSandboxRemoteImpl();
            case "thirdparty":
                return new CodeSandboxThirdPartyImpl();
            default:
                return new CodeSandboxDefaultImpl();
        }
    }

    public static CodeSandboxProxy newInstanceWithProxy(String type) {
        return new CodeSandboxProxy(newInstance(type));
    }
}
