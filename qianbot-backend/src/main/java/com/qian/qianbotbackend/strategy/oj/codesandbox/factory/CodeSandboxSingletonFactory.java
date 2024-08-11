package com.qian.qianbotbackend.strategy.oj.codesandbox.factory;

import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandbox;
import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandboxProxy;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.CodeSandboxDefaultImpl;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.CodeSandboxRemoteImpl;
import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.CodeSandboxThirdPartyImpl;

/**
 * 代码沙箱单例工厂类
 */
public class CodeSandboxSingletonFactory {
    /**
     * 私有静态变量，用于保存唯一实例
     */
    private static CodeSandboxSingletonFactory instance;

    /**
     * 私有构造函数，防止外部直接实例化
     */
    private CodeSandboxSingletonFactory() {
    }

    /**
     * 公有静态方法，用于获取唯一实例
     *
     * @return 代码沙箱实例
     */
    public static synchronized CodeSandboxSingletonFactory getInstance() {
        // 如果实例为空，则创建新实例
        if (instance == null) {
            instance = new CodeSandboxSingletonFactory();
        }
        return instance;
    }

    /**
     * 代码沙箱单例工厂类
     *
     * @param type 代码沙箱类型
     * @return 代码沙箱实例
     */
    public CodeSandbox newInstanceBySingletonFactory(String type) {
        CodeSandbox codeSandBox = null;
        switch (type) {
            case "default":
                codeSandBox = new CodeSandboxDefaultImpl();
                break;
            case "remote":
                codeSandBox = new CodeSandboxRemoteImpl();
                break;
            case "thirdparty":
                codeSandBox = new CodeSandboxThirdPartyImpl();
                break;
            default:
                codeSandBox = new CodeSandboxDefaultImpl();
                break;
        }
        return codeSandBox;
    }

    /**
     * 代码沙箱单例工厂代理类
     *
     * @param type 代码沙箱类型
     * @return 代码沙箱代理类
     */
    public CodeSandbox newInstanceBySingletonFactoryProxy(String type) {
        return new CodeSandboxProxy(newInstanceBySingletonFactory(type));
    }
}
