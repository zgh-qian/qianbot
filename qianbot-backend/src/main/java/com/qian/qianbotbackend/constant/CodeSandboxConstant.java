package com.qian.qianbotbackend.constant;

public interface CodeSandboxConstant {
    // region 退出码
    Integer EXIT_CODE_SUCCESS = 0;
    Integer EXIT_CODE_FAILURE = 1;
    // endregion

    // region 工厂类型
    String FACTORY_NATIVE = "native";
    String FACTORY_DOCKER = "docker";
    String FACTORY_REMOTE = "remote";
    // endregion

    // region 命令
    String[] WINDOW_CMD = {"cmd", "/c"};
    String[] LINUX_CMD = {"/bin/sh", "-c"};
    // endregion
}
