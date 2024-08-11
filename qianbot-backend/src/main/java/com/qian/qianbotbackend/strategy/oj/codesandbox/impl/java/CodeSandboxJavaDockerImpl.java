package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.ArrayUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.qian.qianbotbackend.model.codesandbox.OjExecuteMessage;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeConfig;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.qian.qianbotbackend.constant.OjConstant.*;

/**
 * Java Docker 代码沙箱模板实现类
 */
public class CodeSandboxJavaDockerImpl extends CodeSandboxJavaTemplate {
    private Boolean IS_EXIST_JAVA_IMAGE = false;

    /**
     * 初始化dockerClient
     *
     * @param dockerClient dockerClient
     */
    private void dockerClientInit(DockerClient dockerClient) {
        if (IS_EXIST_JAVA_IMAGE) {
            return;
        }
        // 检查docker本地是否存在 JAVA_IMAGE
        List<Image> imageList = dockerClient.listImagesCmd().exec();
        for (Image image : imageList) {
            if (Arrays.asList(image.getRepoTags()).contains(JAVA_IMAGE)) {
                IS_EXIST_JAVA_IMAGE = true;
                return;
            }
        }
        // 不存在则拉取镜像
        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(JAVA_IMAGE);
        PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
            @Override
            public void onNext(PullResponseItem item) {
                //System.out.println("下载镜像：" + item.getStatus() + "," + item.getProgress());
                super.onNext(item);
            }
        };
        try {
            pullImageCmd
                    .exec(pullImageResultCallback)
                    .awaitCompletion();
            IS_EXIST_JAVA_IMAGE = true;
        } catch (InterruptedException e) {
            System.out.println("拉取镜像异常：" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建容器
     *
     * @param dockerClient       dockerClient
     * @param userCodeParentPath 用户代码父路径
     * @return createContainerResponse
     */
    private CreateContainerResponse createContainer(DockerClient dockerClient, String userCodeParentPath) {
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(JAVA_IMAGE);
        HostConfig hostConfig = new HostConfig();
        hostConfig
                .withCpuCount(1L) // 设置CPU核心数
                .withMemorySwap(0L) // 设置内存限制
                .withMemory(100 * 1000 * 1000L) // 设置最大内存资源限制
                // .withSecurityOpts(Arrays.asList("seccomp=" + SECCOMP_PROFILE_PATH)) // 设置安全策略
                .withAutoRemove(true) // 容器退出后自动删除
                .setBinds(new Bind(userCodeParentPath, new Volume(JAVA_VOLUME_PATH)));
        CreateContainerResponse createContainerResponse = containerCmd
                .withNetworkDisabled(true) // 禁止用户代码使用网络资源
                .withHostConfig(hostConfig)
                .withReadonlyRootfs(true) // 禁止用户代码在 root 目录下写入文件
                .withAttachStdin(true) // 开启容器attach标准输入
                .withAttachStdout(true) // 开启容器attach标准输出
                .withAttachStderr(true) // 开启容器attach标准错误
                .withTty(true) // 开启tty
                .exec();
        return createContainerResponse;
    }

    /**
     * 运行class代码并获取输出
     *
     * @param userCodeFile 用户class代码文件
     * @param inputList    输入用例
     * @param judgeConfig  判题配置
     * @return 输出结果
     */
    @Override
    public List<OjExecuteMessage> runClassGetOutput(File userCodeFile, List<String> inputList, OjJudgeConfig judgeConfig) {
        // 设置超时时间
        long MaxTime = judgeConfig.getTimeLimit() + 1000L;
        String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
        DefaultDockerClientConfig.Builder dockerClientConfig = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock");
        DockerClient dockerClient = DockerClientBuilder
                .getInstance(dockerClientConfig)
                .build();
        // 初始化 dockerClient
        dockerClientInit(dockerClient);
        // 创建容器
        CreateContainerResponse createContainerResponse = createContainer(dockerClient, userCodeParentPath);
        String containerId = createContainerResponse.getId();
        // 启动容器
        dockerClient.startContainerCmd(containerId).exec();
        // docker exec xxx_xxx java -cp /app Main 1 3
        List<OjExecuteMessage> executeMessageList = new ArrayList<>();
        for (String inputArgs : inputList) {
            StopWatch stopWatch = new StopWatch();
            String[] inputArgsArray = inputArgs.split(" ");
            String[] cmdArray = ArrayUtil.append(new String[]{"java", "-cp", "/app", "Main"}, inputArgsArray);
            ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                    .withCmd(cmdArray)
                    .withAttachStderr(true)
                    .withAttachStdin(true)
                    .withAttachStdout(true)
                    .exec();
            OjExecuteMessage executeMessage = new OjExecuteMessage();
            final String[] message = {null};
            final int[] exitCode = {CODE_SUCCESS};
            // 判断是否超时
            final boolean[] isTimeOut = {true};
            long time = 0L;
            final long[] maxMemory = {0L};
            String execId = execCreateCmdResponse.getId();
            ExecStartResultCallback execStartResultCallback = new ExecStartResultCallback() {
                @Override
                public void onNext(Frame frame) {
                    StreamType streamType = frame.getStreamType();
                    if (StreamType.STDERR.equals(streamType)) {
                        exitCode[0] = CODE_ERROR;
                    }
                    message[0] = new String(frame.getPayload());
                    super.onNext(frame);
                }

                @Override
                public void onComplete() {
                    // 如果执行完成，则设置为不超时
                    isTimeOut[0] = false;
                    super.onComplete();
                }
            };
            // 获取占用的内存
            StatsCmd statsCmd = dockerClient.statsCmd(containerId);
            ResultCallback<Statistics> statisticsResultCallback = new ResultCallback<Statistics>() {
                @Override
                public void onStart(Closeable closeable) {

                }

                @Override
                public void onNext(Statistics statistics) {
                    Long memory = statistics.getMemoryStats().getMaxUsage();
                    maxMemory[0] = Math.max(maxMemory[0], memory);
                    //System.out.println("当前容器内存占用：" + memory);
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }

                @Override
                public void close() throws IOException {

                }
            };
            statsCmd.exec(statisticsResultCallback);
            try {
                stopWatch.start();
                dockerClient
                        .execStartCmd(execId)
                        .exec(execStartResultCallback)
                        .awaitCompletion(MaxTime, TimeUnit.MILLISECONDS); // 超时控制
                stopWatch.stop();
                time = stopWatch.getLastTaskTimeMillis();
                statsCmd.close();
            } catch (InterruptedException e) {
                //System.out.println("程序执行异常：" + e);
                throw new RuntimeException(e);
            }
            executeMessage.setExitCode(exitCode[0]);
            executeMessage.setMessage(message[0]);
            executeMessage.setTime(time);
            executeMessage.setMemory(maxMemory[0]);
            executeMessageList.add(executeMessage);
        }
        return executeMessageList;
    }
}