package com.qian.qianbotbackend.manager;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.qian.qianbotbackend.constant.AIConstant.STABLE_TEMPERATURE;
import static com.qian.qianbotbackend.constant.AIConstant.UNSTABLE_TEMPERATURE;

/**
 * AI 管理
 */
@Slf4j
@Component
public class AIManager {

    // region ZhiPuAI
    @Resource
    private ClientV4 clientV4;

    /**
     * 同步稳定请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @return 调用结果
     */
    public String doSyncStableRequest(String systemMessage, String userMessage) {
        return doSyncRequest(systemMessage, userMessage, STABLE_TEMPERATURE);
    }

    /**
     * 同步不稳定请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @return 调用结果
     */
    public String doSyncUnstableRequest(String systemMessage, String userMessage) {
        return doSyncRequest(systemMessage, userMessage, UNSTABLE_TEMPERATURE);
    }

    /**
     * 同步请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @param temperature   温度
     * @return 调用结果
     */
    public String doSyncRequest(String systemMessage, String userMessage, Float temperature) {
        return doRequest(systemMessage, userMessage, Boolean.FALSE, temperature);
    }

    /**
     * 封装基础请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @param stream        流
     * @param temperature   温度
     * @return 调用结果
     */
    public String doRequest(String systemMessage, String userMessage, Boolean stream, Float temperature) {
        return doRequest(
                new ArrayList<>(Arrays.asList(
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage),
                        new ChatMessage(ChatMessageRole.USER.value(), userMessage))),
                stream,
                temperature);
    }

    /**
     * 基础请求
     *
     * @param messages    信息
     * @param stream      流
     * @param temperature 温度
     * @return 调用结果
     */
    public String doRequest(List<ChatMessage> messages, Boolean stream, Float temperature) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(stream)
                .temperature(temperature)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .build();
        try {
            ModelApiResponse modelApiResponse = clientV4.invokeModelApi(chatCompletionRequest);
            return modelApiResponse.getData().getChoices().get(0).toString();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * 稳定流请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @return 调用结果
     */
    public Flowable<ModelData> doStableStreamRequest(String systemMessage, String userMessage) {
        return doStreamRequest(systemMessage, userMessage, STABLE_TEMPERATURE);
    }

    /**
     * 不稳定流请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @return 调用结果
     */
    public Flowable<ModelData> doUnstableStreamRequest(String systemMessage, String userMessage) {
        return doStreamRequest(systemMessage, userMessage, UNSTABLE_TEMPERATURE);
    }

    /**
     * 流请求
     *
     * @param systemMessage 系统信息
     * @param userMessage   用户信息
     * @param temperature   温度
     * @return 调用结果
     */
    public Flowable<ModelData> doStreamRequest(String systemMessage, String userMessage, Float temperature) {
        return doStreamRequest(
                new ArrayList<>(Arrays.asList(
                        new ChatMessage(ChatMessageRole.SYSTEM.value(), systemMessage),
                        new ChatMessage(ChatMessageRole.USER.value(), userMessage)
                )),
                temperature);
    }

    /**
     * 基础流请求
     *
     * @param messages    信息流
     * @param temperature 温度
     * @return 调用结果
     */
    public Flowable<ModelData> doStreamRequest(List<ChatMessage> messages, Float temperature) {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(Boolean.TRUE)
                .temperature(temperature)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .build();
        try {
            ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
            return invokeModelApiResp.getFlowable();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }
    // endregion

    // region yucongming
    @Resource
    private YuCongMingClient yuCongMingClient;

    /**
     * 对话
     *
     * @param modelId 模型id
     * @param message 信息
     * @return 调用结果
     */
    public String doChat(long modelId, String message) {
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        if (response == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "AI 响应错误");
        }
        return response.getData().getContent();
    }
    // endregion
}
