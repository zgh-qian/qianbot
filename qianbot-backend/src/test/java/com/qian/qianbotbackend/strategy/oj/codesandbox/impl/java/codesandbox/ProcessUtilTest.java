package com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.codesandbox;

import com.qian.qianbotbackend.strategy.oj.codesandbox.impl.java.JavaProcessUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.qian.qianbotbackend.constant.OjConstant.COMPILE_COMMAND;
import static com.qian.qianbotbackend.constant.OjConstant.RUN_COMMAND_INTERPRETER;
import static org.junit.jupiter.api.Assertions.*;

class ProcessUtilTest {

    @Test
    void compileJavaCode() {
        String compileCmd = String.format("javac -encoding utf-8 %s", "E:\\CodeFiles\\QianBot\\qianbot-backend\\src\\main\\resources\\code\\Main.java");
        Process compileProcess = null;
        try {
            compileProcess = Runtime.getRuntime().exec(compileCmd);
            JavaProcessUtil.compileJavaCode(compileProcess);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runJavaCode1() {
        String runCommand = String.format("java -Dfile.encoding=utf-8 -cp %s Main", "E:\\CodeFiles\\QianBot\\qianbot-backend\\src\\main\\resources\\code");
        Process compileProcess = null;
        try {
            compileProcess = Runtime.getRuntime().exec(runCommand);
            JavaProcessUtil.compileJavaCode(compileProcess);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void runJavaCode2() {
        String runCmd = String.format(RUN_COMMAND_INTERPRETER, "E:\\CodeFiles\\QianBot\\qianbot-backend\\src\\test\\java\\com\\qian\\qianbotbackend\\strategy\\oj\\codesandbox\\impl\\java\\code");
        Process compileProcess = null;
        try {
            compileProcess = Runtime.getRuntime().exec(runCmd);
            JavaProcessUtil.executeJavaCodeInteractive(compileProcess, "1 2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}