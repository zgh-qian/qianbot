package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.FoundWord;
import cn.hutool.dfa.WordTree;
import com.qian.qianbotbackend.enums.oj.OjStatusEnum;
import com.qian.qianbotbackend.model.codesandbox.OjExecuteMessage;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeConfig;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeInfo;
import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandbox;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.qian.qianbotbackend.constant.OjConstant.*;

public abstract class CodeSandboxJavaTemplate implements CodeSandbox {
    /**
     * 词树，用于过滤敏感词
     */
    private static final WordTree WORD_TREE;

    static {
        WORD_TREE = new WordTree();
        WORD_TREE.addWords(BLACK_LIST);
    }

    @Override
    public OjExecutionCodeResponse execute(OjExecutionCodeRequest ojExecutionCodeRequest) {
        OjExecutionCodeResponse ojExecutionCodeResponse = null;
        try {
            List<String> inputList = ojExecutionCodeRequest.getInputList();
            OjJudgeConfig judgeConfig = ojExecutionCodeRequest.getOjJudgeConfig();
            String code = ojExecutionCodeRequest.getCode();
            FoundWord foundWord = WORD_TREE.matchWord(code);
            if (foundWord != null) {
                return getErrorMessage("代码中包含敏感词：" + foundWord.getFoundWord());
            }
            // 1. 保存用户代码到文件
            File userCodeFile = saveCodeToFile(code);
            // 2. 编译代码
            OjExecuteMessage compileMessage = compileFileToClass(userCodeFile);
            if (!compileMessage.getExitCode().equals(CODE_SUCCESS)) {
                deleteCodeFile(userCodeFile);
                return getErrorMessage(OjStatusEnum.COMPILE_ERROR.getValue());
            }
            // 3. 执行代码
            List<OjExecuteMessage> executeMessageList = runClassGetOutput(userCodeFile, inputList, judgeConfig);
            // 4. 获取执行结果
            ojExecutionCodeResponse = getOutputResponse(executeMessageList);
            // 5. 删除用户代码文件
            boolean del = deleteCodeFile(userCodeFile);
            /*if (!del) {
                return getErrorMessage("删除用户代码文件失败");
            }*/
            // 6. 返回执行结果
            return ojExecutionCodeResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 1. 保存用户代码到文件
     *
     * @param code 用户代码
     * @return 文件对象
     */
    public File saveCodeToFile(String code) {
        String userDir = System.getProperty(USER_DIR_NAME);
        String globalCodePathName = userDir + File.separator + CODE_DIR_NAME;
        // 判断是否存在全局代码命令，如果不存在则创建
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        // 保存用户代码，隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + JAVA_CLASS_NAME;
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }

    /**
     * 2. 编译代码
     *
     * @param userCodeFile 用户java代码文件
     * @return 编译结果
     */
    public OjExecuteMessage compileFileToClass(File userCodeFile) {
        String compileCmd = String.format(COMPILE_COMMAND, userCodeFile.getAbsoluteFile());
        OjExecuteMessage ojExecuteMessage = null;
        try {
            Process compileProcess = Runtime.getRuntime().exec(compileCmd);
            ojExecuteMessage = JavaProcessUtil.compileJavaCode(compileProcess);
        } catch (IOException e) {
            ojExecuteMessage = new OjExecuteMessage();
            ojExecuteMessage.setExitCode(CODE_ERROR);
            return ojExecuteMessage;
        }
        return ojExecuteMessage;
    }

    /**
     * 3. 执行代码
     *
     * @param userCodeFile 用户class代码文件
     * @param inputList    输入用例
     * @return 执行结果
     */
    public List<OjExecuteMessage> runClassGetOutput(File userCodeFile, List<String> inputList, OjJudgeConfig judgeConfig) {
        // 设置超时时间
        Long timeLimit = judgeConfig.getTimeLimit();
        long MaxTime = timeLimit + 3000L;
        String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
        List<OjExecuteMessage> executeMessageList = new ArrayList<>();
        for (String input : inputList) {
            // 设置最大内存限制 -Xmx256m
            // 如果是交互式
            String runCmd = String.format(RUN_COMMAND_INTERPRETER, userCodeParentPath);
            // 如果是非交互式
            //String runCmd = String.format(RUN_COMMAND_ARGUMENTS, userCodeParentPath, input);
            try {
                Process process = Runtime.getRuntime().exec(runCmd);
                // 超时控制
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(MaxTime);
                        process.destroy();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
                // 如果是交互式
                OjExecuteMessage ojExecuteMessage = JavaProcessUtil.executeJavaCodeInteractive(process, input);
                // 如果是非交互式
                //ExecuteMessage ojExecuteMessage = JavaProcessUtil.executeJavaCodeNonInteractive(process);
                executeMessageList.add(ojExecuteMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return executeMessageList;
    }

    /**
     * 4. 获取执行结果
     *
     * @param executeMessageList 执行结果列表
     * @return 执行结果
     */
    public OjExecutionCodeResponse getOutputResponse(List<OjExecuteMessage> executeMessageList) {
        OjExecutionCodeResponse ojExecutionCodeResponse = new OjExecutionCodeResponse();
        List<String> outputList = new ArrayList<>();
        long maxTime = 0L;
        long maxMemory = 0L;
        for (OjExecuteMessage executeMessage : executeMessageList) {
            if (executeMessage.getExitCode().equals(CODE_ERROR)) {
                String errorMessage = executeMessage.getMessage();
                if (StrUtil.isNotBlank(errorMessage)) {
                    ojExecutionCodeResponse.setMessage(errorMessage);
                    ojExecutionCodeResponse.setStatus(CODE_ERROR_MESSAGE);
                    break;
                }
            }
            // 获取最大时间
            maxTime = Math.max(executeMessage.getTime(), maxTime);
            // 获取最大内存
            maxMemory = Math.max(executeMessage.getMemory(), maxMemory);
            // 获取输出信息
            outputList.add(executeMessage.getMessage());
        }
        // 正常运行完成
        if (outputList.size() == executeMessageList.size()) {
            ojExecutionCodeResponse.setStatus(CODE_SUCCESS);
        }
        OjJudgeInfo judgeInfo = new OjJudgeInfo();
        // 设置最大时间
        judgeInfo.setTime(maxTime);
        // 设置最大内存
        judgeInfo.setMemory(maxMemory);
        // 设置JudgeInfo
        ojExecutionCodeResponse.setJudgeInfo(judgeInfo);
        // 设置输出信息
        ojExecutionCodeResponse.setOutputList(outputList);
        return ojExecutionCodeResponse;
    }

    /**
     * 5. 删除用户代码文件
     *
     * @param userCodeFile 用户代码文件
     * @return 删除结果
     */
    public boolean deleteCodeFile(File userCodeFile) {
        String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
        return FileUtil.del(userCodeParentPath);
    }

    public OjExecutionCodeResponse getErrorMessage(String errMessage) {
        OjExecutionCodeResponse ojExecutionCodeResponse = new OjExecutionCodeResponse();
        ojExecutionCodeResponse.setOutputList(null);
        ojExecutionCodeResponse.setMessage(errMessage);
        ojExecutionCodeResponse.setStatus(CODE_ERROR);
        ojExecutionCodeResponse.setJudgeInfo(null);
        return ojExecutionCodeResponse;
    }
}
