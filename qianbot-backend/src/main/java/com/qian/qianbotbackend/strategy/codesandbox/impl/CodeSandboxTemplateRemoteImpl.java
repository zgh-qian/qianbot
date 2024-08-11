package com.qian.qianbotbackend.strategy.codesandbox.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteRequest;
import com.qian.qianbotbackend.model.codesandbox.CodeExecuteResponse;
import com.qian.qianbotbackend.strategy.codesandbox.CodeSandboxTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class CodeSandboxTemplateRemoteImpl extends CodeSandboxTemplate {
    private static final String url = "https://toolin.cn/api/runcode";

    /**
     * {
     * "lang": "lua",
     * "code": "print \"图灵工具真的棒！\\n\";"
     * }
     */
    @Data
    @AllArgsConstructor
    static class Request {
        String lang;
        String code;
    }

    /**
     * {
     * "filenames": {
     * "image": "codingcn/lua:5.1",
     * "file": "test.lua",
     * "cmd": "lua ./test.lua",
     * "timeout": 5,
     * "memory": "100MB",
     * "cpuset": "0-3"
     * },
     * "code": 0,
     * "data": {
     * "output": [
     * "图灵工具真的棒！\n\n"
     * ]
     * }
     * }
     */
    @Data
    static class Response {
        Filenames filenames;
        Integer code;
        Data data;

        @lombok.Data
        static class Filenames {
            String image;
            String file;
            String cmd;
            String timeout;
            String memory;
            String cpuset;
        }

        @lombok.Data
        static class Data {
            List<String> output;
        }
    }

    @Override
    public CodeExecuteResponse execute(CodeExecuteRequest codeExecuteRequest) {
        String json = JSONUtil.toJsonStr(new Request(codeExecuteRequest.getLanguage(), codeExecuteRequest.getCode()));
        String result = HttpRequest.post(url)
                .body(json)
                .execute()
                .body();
        Response response = JSONUtil.toBean(result, Response.class);
        CodeExecuteResponse codeExecuteResponse = new CodeExecuteResponse();
        codeExecuteResponse.setExitCode(response.getCode());
        codeExecuteResponse.setMessage(response.getData().getOutput().get(0));
        codeExecuteResponse.setTimeUsed(response.getFilenames().getTimeout());
        codeExecuteResponse.setMemoryUsed(response.getFilenames().getMemory());
        return codeExecuteResponse;
    }
}
