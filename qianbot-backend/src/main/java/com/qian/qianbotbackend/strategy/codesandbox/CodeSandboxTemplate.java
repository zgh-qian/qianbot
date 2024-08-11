package com.qian.qianbotbackend.strategy.codesandbox;

import cn.hutool.core.io.FileUtil;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.codesandbox.CodeSandboxLanguageEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class CodeSandboxTemplate implements CodeSandbox {
    @Override
    public CodeExecuteResponse execute(CodeExecuteRequest codeExecuteRequest) {
        CodeExecuteResponse codeExecuteResponse = new CodeExecuteResponse();
        try {
            CodeSandboxLanguageEnum codeSandboxLanguageEnum = CodeSandboxLanguageEnum.getEnumByValue(codeExecuteRequest.getLanguage());
            if (codeSandboxLanguageEnum == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "不支持的编程语言");
            }
            // 保存用户代码到文件
            File userCodeFile = saveCodeToFile(codeExecuteRequest.getCode(), codeSandboxLanguageEnum.getSuffix());
            // 判断是否需要编译
            String[] compileCmd = codeSandboxLanguageEnum.getCompileCmd();
            if (compileCmd != null) {
                // 代码需要编译
                codeExecuteResponse = executeFile(userCodeFile, compileCmd);
                if (codeExecuteResponse.getExitCode() != 0) {
                    // 编译失败
                    deleteCodeFile(userCodeFile);
                    return codeExecuteResponse;
                }
            }
            // 代码不需要编译，直接执行
            codeExecuteResponse = executeFile(userCodeFile, codeSandboxLanguageEnum.getRunCmd());
            // 删除代码文件
            deleteCodeFile(userCodeFile);
        } catch (Exception e) {
            e.printStackTrace();
            codeExecuteResponse.setExitCode(1);
            codeExecuteResponse.setMessage(e.getMessage());
        }
        return codeExecuteResponse;
    }

    /**
     * 将代码保存到文件中
     *
     * @param code   代码
     * @param suffix 后缀
     * @return 文件
     */
    public File saveCodeToFile(String code, String suffix) {
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + "codesandbox";
        // 判断全局文件夹是否存在，不存在则新建
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        // 用户代码父路径
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        // 用户代码路径
        String userCodePath = userCodeParentPath + File.separator + "Main" + "." + suffix;
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }

    /**
     * 编译代码
     *
     * @param codeFile   用户代码文件
     * @param executeCmd 编译命令
     * @return 编译结果
     */
    public CodeExecuteResponse executeFile(File codeFile, String[] executeCmd) throws IOException, InterruptedException {
        long MaxTime = 3600L;
        CodeExecuteResponse codeExecuteResponse = new CodeExecuteResponse();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(new File(codeFile.getParentFile().getAbsolutePath()));
        processBuilder.command(executeCmd);
        // 启动线程
        Process process = processBuilder.start();
        // 启动监控线程
        startWatchdogThread(process, MaxTime);
        // 等待进程退出
        int exitCode = process.waitFor();
        BufferedReader bufferedReader;
        List<String> outputStrList = new ArrayList<>();
        if (exitCode == 0) {
            // 0 正常退出，编译/运行成功
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        } else {
            // 1 异常退出，编译/运行失败
            bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        }
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            outputStrList.add(line);
        }
        codeExecuteResponse.setExitCode(exitCode);
        if (!outputStrList.isEmpty()) {
            codeExecuteResponse.setMessage(StringUtils.join(outputStrList, '\n'));
        }
        return codeExecuteResponse;
    }

    /**
     * 启动监控线程
     *
     * @param process 被监控线程
     * @param maxTime 最大超时时间
     * @return 监控线程
     */
    private Thread startWatchdogThread(Process process, long maxTime) {
        Thread watchdogThread = new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + maxTime;
                while (System.currentTimeMillis() < endTime && process.isAlive()) {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                }
                if (process.isAlive()) {
                    process.destroy();
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "代码执行超时");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        });
        watchdogThread.setName("watchdogThread");
        watchdogThread.start();
        return watchdogThread;
    }

    /**
     * 删除代码文件
     *
     * @param codeFile 代码文件
     * @return true 删除成功，false 删除失败
     */
    public boolean deleteCodeFile(File codeFile) {
        // 删除全部代码文件
        String userCodeParentPath = codeFile.getParentFile().getAbsolutePath();
        return FileUtil.del(userCodeParentPath);
    }
}
