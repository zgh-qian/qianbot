package com.qian.qianbotbackend.strategy.codesandbox.factory;

import com.qian.qianbotbackend.strategy.codesandbox.CodeSandbox;
import com.qian.qianbotbackend.strategy.codesandbox.CodeSandboxProxy;
import com.qian.qianbotbackend.strategy.codesandbox.impl.CodeSandboxTemplateDockerImpl;
import com.qian.qianbotbackend.strategy.codesandbox.impl.CodeSandboxTemplateNativeImpl;
import com.qian.qianbotbackend.strategy.codesandbox.impl.CodeSandboxTemplateRemoteImpl;

import static com.qian.qianbotbackend.constant.CodeSandboxConstant.*;

/**
 * 代码沙箱静态工厂类
 */
public class CodeSandboxStaticFactory {
    /**
     * 代码沙箱静态工厂类
     *
     * @param type 代码沙箱类型
     * @return 代码沙箱实例
     */
    public static CodeSandbox newInstance(String type) {
        CodeSandbox codeSandBox;
        switch (type) {
            case FACTORY_NATIVE:
                codeSandBox = new CodeSandboxTemplateNativeImpl();
                break;
            case FACTORY_DOCKER:
                codeSandBox = new CodeSandboxTemplateDockerImpl();
                break;
            case FACTORY_REMOTE:
                codeSandBox = new CodeSandboxTemplateRemoteImpl();
                break;
            default:
                codeSandBox = new CodeSandboxTemplateNativeImpl();
                break;
        }
        return codeSandBox;
    }

    /**
     * 代码沙箱静态工厂类
     *
     * @param type 代码沙箱类型
     * @return 代码沙箱代理实例
     */
    public static CodeSandbox newInstanceProxy(String type) {
        return new CodeSandboxProxy(newInstance(type));
    }
}
