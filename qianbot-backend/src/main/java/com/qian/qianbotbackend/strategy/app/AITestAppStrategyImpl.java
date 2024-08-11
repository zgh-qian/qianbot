package com.qian.qianbotbackend.strategy.app;

import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.constant.AppConstant;
import com.qian.qianbotbackend.enums.app.AppAnswerStatusEnum;
import com.qian.qianbotbackend.enums.app.AppTypeEnum;
import com.qian.qianbotbackend.manager.AIManager;
import com.qian.qianbotbackend.manager.CacheManager;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.domain.Appoption;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import com.qian.qianbotbackend.service.AppoptionService;
import com.qian.qianbotbackend.service.AppquestionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.qian.qianbotbackend.constant.AIConstant.AI_TEST_SCORING_SYSTEM_MESSAGE;

@AppStrategyConfig(appType = 1, scoringStrategy = 1)
public class AITestAppStrategyImpl implements AppStrategy {
    @Resource
    private AppquestionService appquestionService;

    @Resource
    private AppoptionService appoptionService;

    @Resource
    private AIManager aiManager;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public Appanswer doScore(AppAnswerDTO appAnswerDTO) throws Exception {
        Long id = appAnswerDTO.getId();
        Long appId = appAnswerDTO.getAppId();
        List<Long> userAnswer = appAnswerDTO.getUserAnswer();
        // 构建缓存key
        String cacheKey = cacheManager.buildAnswerCacheKey(appId, appoptionService.getOptionKeyList(userAnswer));
        // 获取缓存
        String json = cacheManager.getCache(cacheKey);
        if (json == null) {
            // 获取锁
            RLock lock = redissonClient.getLock(AppConstant.APP_ANSWER_LOCK + cacheKey);
            try {
                boolean res = lock.tryLock(5, 30, TimeUnit.SECONDS);
                if (res) {
                    // 封装用户信息
                    String userMessage = getAiTestScoringUserMessage(appAnswerDTO);
                    // AI 生成
                    String result = aiManager.doSyncStableRequest(AI_TEST_SCORING_SYSTEM_MESSAGE, userMessage);
                    int start = result.indexOf("{");
                    int end = result.lastIndexOf("}");
                    json = result.substring(start, end + 1);
                    // 设置缓存
                    cacheManager.putCache(cacheKey, json, false);
                } else {
                    // 这个时候其他线程缓存值，只需要获取即可
                    json = cacheManager.getCache(cacheKey);
                }
            } finally {
                if (lock != null && lock.isLocked()) {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        }
        // 构建返回值
        Appanswer appanswer = JSONUtil.toBean(json, Appanswer.class);
        appanswer.setId(id);
        appanswer.setResultStatus(AppAnswerStatusEnum.SUCCESS.getValue());
        return appanswer;
    }

    /**
     * 构建用户请求信息
     *
     * @param appAnswerDTO 用户回答dto
     * @return 用户请求信息
     */
    private String getAiTestScoringUserMessage(AppAnswerDTO appAnswerDTO) {
        StringBuilder userMessage = new StringBuilder();
        List<Long> userAnswer = appAnswerDTO.getUserAnswer();
        App app = appAnswerDTO.getApp();
        userMessage.append(app.getAppName()).append("\n");
        userMessage.append(app.getAppDesc()).append("\n");
        List<QuestionContent> questionContentList = new ArrayList<>();
        for (Long optionId : userAnswer) {
            Appoption appoption = appoptionService.getById(optionId);
            questionContentList.add(new QuestionContent(
                    appquestionService.getById(appoption.getQuestionId()).getQuestionName(),
                    appoption.getOptionName()));
        }
        userMessage.append(JSONUtil.toJsonStr(questionContentList));
        return userMessage.toString();
    }

    @Data
    @AllArgsConstructor
    private static class QuestionContent {
        private String question;
        private String answer;
    }
}
