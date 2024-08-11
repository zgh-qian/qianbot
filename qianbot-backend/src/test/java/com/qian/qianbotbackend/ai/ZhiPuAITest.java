package com.qian.qianbotbackend.ai;

import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ZhiPuAITest {
    @Test
    void testRaw() {
        // 初始化客户端
        ClientV4 client = new ClientV4.Builder("29bbe8fd8cb0706d7782279f2e1e9ca2.fJLN3AJUfoY6rzsl").build();
        // 构建请求
        List<ChatMessage> messages = new ArrayList<>();
        String content = "作为一名营销专家，请为智谱开放平台创作一个吸引人的slogan";
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), content);
        messages.add(chatMessage);
        // 请求id，会自动创建
        // String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(Boolean.FALSE)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .build();
        ModelApiResponse invokeModelApiResp = client.invokeModelApi(chatCompletionRequest);
        System.out.println("model output:" + invokeModelApiResp.toString());
        System.out.println("model output:" + invokeModelApiResp.getData().getChoices().get(0));
    }
}
