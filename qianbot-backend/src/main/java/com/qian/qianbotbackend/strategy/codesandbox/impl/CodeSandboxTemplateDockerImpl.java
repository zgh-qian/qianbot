package com.qian.qianbotbackend.strategy.codesandbox.impl;

import cn.hutool.core.io.FileUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.StreamType;
import com.github.dockerjava.core.DockerClientBuilder;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.codesandbox.CodeSandboxLanguageEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import com.qian.qianbotbackend.strategy.codesandbox.CodeSandboxTemplate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 容器实现
 */
public class CodeSandboxTemplateDockerImpl extends CodeSandboxTemplate {
    private static final DockerClient DOCKER_CLIENT;

    static {
        DockerClient client = null;
        try {
            client = DockerClientBuilder.getInstance().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DOCKER_CLIENT = client;
    }

    final String image = "codesandbox:latest";
    long memoryLimit = 60 * 1000 * 1000L;
    long memorySwap = 0L;
    long cpuCount = 1L;
    long timeoutLimit = 3;
    TimeUnit timeUnit = TimeUnit.SECONDS;

    @Override
    public CodeExecuteResponse execute(CodeExecuteRequest codeExecuteRequest) {
        CodeExecuteResponse codeExecuteResponse;
        CodeSandboxLanguageEnum codeSandboxLanguageEnum = CodeSandboxLanguageEnum.getEnumByValue(codeExecuteRequest.getLanguage());
        if (codeSandboxLanguageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不支持的编程语言");
        }
        // 保存用户代码到文件
        File userCodeFile = saveCodeToFile(codeExecuteRequest.getCode(), codeSandboxLanguageEnum.getSuffix());
        // 创建容器
        String userCodePath = userCodeFile.getAbsolutePath();
        String userCodeParentPath = userCodeFile.getParent();
        String containerId = createContainer(userCodePath);
        // 判断是否需要编译
        String[] compileCmd = codeSandboxLanguageEnum.getCompileCmd();
        if (compileCmd != null) {
            codeExecuteResponse = execCmd(containerId, compileCmd);
            if (codeExecuteResponse.getExitCode() != 0) {
                // 编译失败，清理文件和容器
                cleanFileAndContainer(userCodeParentPath, containerId);
                return codeExecuteResponse;
            }
        }
        // 运行
        codeExecuteResponse = execCmd(containerId, codeSandboxLanguageEnum.getRunCmd());
        // 清理文件和容器
        cleanFileAndContainer(userCodeParentPath, containerId);
        return codeExecuteResponse;
    }

    /**
     * 创建容器
     *
     * @param codeFile 代码文件
     * @return 容器id
     */
    private String createContainer(String codeFile) {
        CreateContainerCmd containerCmd = DOCKER_CLIENT.createContainerCmd(image);
        HostConfig hostConfig = new HostConfig();
        hostConfig.withMemory(memoryLimit);
        hostConfig.withMemorySwap(memorySwap);
        hostConfig.withCpuCount(cpuCount);
        CreateContainerResponse createContainerResponse = containerCmd
                .withHostConfig(hostConfig)
                .withNetworkDisabled(true)
                .withAttachStdin(true)
                .withAttachStderr(true)
                .withAttachStdout(true)
                .withTty(true)
                .exec();
        // 启动容器
        String containerId = createContainerResponse.getId();
        DOCKER_CLIENT.startContainerCmd(containerId).exec();
        // 将代码文件复制到容器中
        DOCKER_CLIENT.copyArchiveToContainerCmd(containerId)
                .withHostResource(codeFile)
                .withRemotePath("/app")
                .exec();
        return containerId;
    }

    private CodeExecuteResponse execCmd(String containerId, String[] cmd) {
        CodeExecuteResponse codeExecuteResponse = new CodeExecuteResponse();
        // 返回信息
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 结果
        final boolean[] result = {true};
        final boolean[] timeout = {true};
        try (ResultCallback.Adapter<Frame> frameAdapter = new ResultCallback.Adapter<Frame>() {
            @Override
            public void onNext(Frame frame) {
                StreamType streamType = frame.getStreamType();
                byte[] payload = frame.getPayload();
                if (StreamType.STDERR.equals(streamType)) {
                    try {
                        result[0] = false;
                        outputStream.write(payload);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        result[0] = true;
                        outputStream.write(payload);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                super.onNext(frame);
            }

            @Override
            public void onComplete() {
                timeout[0] = false;
                super.onComplete();
            }
        }) {
            ExecCreateCmdResponse execCreateCmdResponse = DOCKER_CLIENT.execCreateCmd(containerId)
                    .withCmd(cmd)
                    .withAttachStderr(true)
                    .withAttachStdin(true)
                    .withAttachStdout(true)
                    .exec();
            String execId = execCreateCmdResponse.getId();
            DOCKER_CLIENT.execStartCmd(execId).exec(frameAdapter).awaitCompletion(timeoutLimit, timeUnit);
            if (timeout[0]) {
                codeExecuteResponse.setExitCode(1);
                codeExecuteResponse.setMessage("执行超时");
            } else {
                codeExecuteResponse.setExitCode(result[0] ? 0 : 1);
                codeExecuteResponse.setMessage(outputStream.toString());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            codeExecuteResponse.setExitCode(1);
            codeExecuteResponse.setMessage(e.getMessage());
        }
        return codeExecuteResponse;
    }

    /**
     * 清理文件和容器
     *
     * @param userCodePath 用户代码路径
     * @param containerId  容器id
     */
    private void cleanFileAndContainer(String userCodePath, String containerId) {
        // 清理临时目录
        FileUtil.del(userCodePath);
        // 停止并删除容器
        DOCKER_CLIENT.stopContainerCmd(containerId).exec();
        DOCKER_CLIENT.removeContainerCmd(containerId).exec();
    }
}
