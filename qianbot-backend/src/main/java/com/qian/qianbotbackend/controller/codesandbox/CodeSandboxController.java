package com.qian.qianbotbackend.controller.codesandbox;

import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.config.CodeSandboxConfig;
import com.qian.qianbotbackend.constant.CodeSandboxConstant;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.enums.codesandbox.CodeSandboxLanguageEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import com.qian.qianbotbackend.model.user.domain.User;
import com.qian.qianbotbackend.strategy.codesandbox.CodeSandbox;
import com.qian.qianbotbackend.strategy.codesandbox.factory.CodeSandboxStaticFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.qian.qianbotbackend.constant.UserConstant.USER_LOGIN_STATE;


@Slf4j
@RestController
@RequestMapping("/codesandbox")
public class CodeSandboxController {
    @Resource
    private CodeSandboxConfig codeSandboxConfig;

    /**
     * 获取语言列表
     *
     * @return 语言列表
     */
    @GetMapping("/language")
    public BaseResponse<List<CodeSandboxLanguageEnum.CodeSandboxLanguage>> getCodeSandboxLanguage() {
        return ResultUtils.success(CodeSandboxLanguageEnum.getTextAndValues());
    }

    /**
     * 执行代码
     *
     * @param codeExecuteRequest 代码执行请求
     * @return 代码执行结果
     */
    @PostMapping("/execute")
    public BaseResponse<CodeExecuteResponse> execute(@RequestBody CodeExecuteRequest codeExecuteRequest) {
        CodeSandbox codeSandbox = CodeSandboxStaticFactory.newInstanceProxy(codeSandboxConfig.getType());
        return ResultUtils.success(codeSandbox.execute(codeExecuteRequest));
    }

    private static final String[] os_cmd;

    private static final String message = "该操作需要管理员权限";

    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            os_cmd = CodeSandboxConstant.WINDOW_CMD;
        } else {
            os_cmd = CodeSandboxConstant.LINUX_CMD;
        }
    }

    /**
     * 执行命令
     *
     * @param command 命令
     * @return SseEmitter
     */
    @GetMapping("/command")
    public SseEmitter command(@RequestParam String command, HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (userObj != null && !((User) userObj).getUserRole().equals(UserConstant.ROLE_ADMIN)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, message);
        } else if (userObj == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, message);
        }
        SseEmitter sseEmitter = new SseEmitter(0L);
        try {
            String[] cmd = Arrays.copyOf(os_cmd, os_cmd.length + 1);
            cmd[os_cmd.length] = command;
            sseEmitter.send(StringUtils.join(cmd, " "));
            // 创建一个 ProcessBuilder 对象
            ProcessBuilder builder = new ProcessBuilder();
            builder.redirectErrorStream(true);
            // 设置要执行的命令
            builder.command(cmd);
            // 启动进程
            Process process = builder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sseEmitter.send(line);
            }
            // 等待进程执行完成
            process.waitFor();
            sseEmitter.complete();
        } catch (IOException | InterruptedException e) {
            // 发送错误信息
            sseEmitter.completeWithError(e);
        }
        return sseEmitter;
    }

    /**
     * 执行命令
     *
     * @param language  编程语言
     * @param command   命令
     * @param isInstall 是否安装
     * @return SseEmitter
     */
    @GetMapping("/command/language")
    public SseEmitter commandLanguage(@RequestParam String language, @RequestParam String command, @RequestParam Boolean isInstall, HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (userObj != null && !((User) userObj).getUserRole().equals(UserConstant.ROLE_ADMIN)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, message);
        } else if (userObj == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, message);
        }
        SseEmitter sseEmitter = new SseEmitter(0L);
        try {
            CodeSandboxLanguageEnum codeSandboxLanguageEnum = CodeSandboxLanguageEnum.getEnumByValue(language);
            ThrowUtils.throwIf(codeSandboxLanguageEnum == null, ErrorCode.PARAMS_ERROR, "不支持该编程语言");
            String[] cmd;
            String preCmd;
            if (isInstall) {
                preCmd = codeSandboxLanguageEnum.getInstallCmd();
            } else {
                preCmd = codeSandboxLanguageEnum.getUninstallCmd();
            }
            if (preCmd == null) {
                sseEmitter.send("该编程语言无需安装或卸载包");
                return sseEmitter;
            }
            cmd = Arrays.copyOf(os_cmd, os_cmd.length + 1);
            cmd[os_cmd.length] = String.format(preCmd, command);
            sseEmitter.send(StringUtils.join(cmd, " "));
            // 创建一个 ProcessBuilder 对象
            ProcessBuilder builder = new ProcessBuilder();
            builder.redirectErrorStream(true);
            // 设置要执行的命令
            builder.command(cmd);
            // 启动进程
            Process process = builder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sseEmitter.send(line);
            }
            // 等待进程执行完成
            process.waitFor();
            sseEmitter.complete();
        } catch (IOException | InterruptedException e) {
            // 发送错误信息
            sseEmitter.completeWithError(e);
        }
        return sseEmitter;
    }
}
