package com.qian.qianbotbackend.ai;

import com.qian.qianbotbackend.manager.AIManager;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class YuCongMingAITest {
    @Resource
    private YuCongMingClient yuCongMingClient;

    @Resource
    private AIManager aiManager;

    @Test
    void test1() {
        // 构造请求
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(1651468516836098050L);
        devChatRequest.setMessage("鱼皮");
        // 获取响应
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        System.out.println(response.getData());
    }

    @Test
    void test2() {
        long biModelId = 1659171950288818178L;
        String msg="分析需求：\n" +
                "对比不同产品在过去一年的销售额，并分析哪些产品的销售额增长最快。\n" +
                "\n" +
                "原始数据：\n" +
                "product,April2022,May2022,June2022,July2022,August2022,September2022,October2022,November2022,December2022,January2023,February2023,March2023\n" +
                "ProductA,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,2100\n" +
                "ProductB,800,900,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900\n" +
                "ProductC,1200,1100,1000,900,800,700,600,500,400,300,200,100";
        String chat = aiManager.doChat(biModelId, msg);
        System.out.println(chat);
    }
}
