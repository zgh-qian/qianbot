package com.qian.qianbotbackend.strategy.codesandbox.impl;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static com.qian.qianbotbackend.constant.CodeSandboxConstant.EXIT_CODE_FAILURE;

public class JS {
    public static void main(String[] args) {
        String code = "console.log('Hello, World!');";
        CodeExecuteResponse codeExecuteResponse = new CodeExecuteResponse();
        // 创建脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        // 获取 JavaScript 引擎
        ScriptEngine engine = manager.getEngineByName("js");
        if (engine == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "JavaScript engine not found");
        }
        try {
            String script = "var capturedOutput = '';\n" +
                    "function captureLog(message) { capturedOutput += message + '\\n'; }\n" +
                    "function executeScript() { console.log = captureLog; " +
                    code + "}\n" +
                    "executeScript();\n" +
                    "capturedOutput;";
            // 设置 console.log 函数，将输出重定向到 System.out
            engine.eval("var console = { log: function(msg) { java.lang.System.out.println(msg); } };");
            // 执行 JavaScript 代码
            Object result = engine.eval(script);
        } catch (ScriptException e) {
            codeExecuteResponse.setExitCode(EXIT_CODE_FAILURE);
            codeExecuteResponse.setMessage(e.getMessage());
            e.printStackTrace();
        }
    }
}
