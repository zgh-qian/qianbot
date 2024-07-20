package com.qian.qianbotbackend.strategy.app;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qian.qianbotbackend.enums.app.AppAnswerStatusEnum;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.domain.Appresult;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import com.qian.qianbotbackend.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AppStrategyConfig(appType = 1, scoringStrategy = 0)
public class CustomTestAppStrategyImpl implements AppStrategy {
    @Resource
    private AppoptionService appoptionService;

    @Resource
    private AppresultService appresultService;

    @Override
    public Appanswer doScore(AppAnswerDTO appAnswerDTO) throws Exception {
        Long id = appAnswerDTO.getId();
        Long appId = appAnswerDTO.getAppId();
        List<Long> userAnswer = appAnswerDTO.getUserAnswer();
        // 统计用户每个选择对应的属性个数
        Map<String, Integer> optionCount = new HashMap<>();
        for (Long optionId : userAnswer) {
            String optionResult = appoptionService.getById(optionId).getOptionResult();
            optionCount.put(optionResult, optionCount.getOrDefault(optionResult, 0) + 1);
        }
        List<Appresult> appresultList = appresultService.list(
                Wrappers.lambdaQuery(Appresult.class).eq(Appresult::getAppId, appId)
        );
        // 遍历每种评分结果，计算哪个结果的得分更高
        int maxScore = 0;
        Appresult maxAppresult = appresultList.get(0);
        for (Appresult appresult : appresultList) {
            List<String> resultProps = JSONUtil.toList(appresult.getResultProp(), String.class);
            int score = resultProps.stream().mapToInt(prop -> optionCount.getOrDefault(prop, 0)).sum();
            if (score > maxScore) {
                maxScore = score;
                maxAppresult = appresult;
            }
        }
        // 构造返回值，填充答案对象的属性
        Appanswer appanswer = new Appanswer();
        appanswer.setId(id);
        appanswer.setResultStatus(AppAnswerStatusEnum.SUCCESS.getValue());
        appanswer.setResultId(maxAppresult.getId());
        appanswer.setResultName(maxAppresult.getResultName());
        appanswer.setResultDesc(maxAppresult.getResultDesc());
        appanswer.setResultPic(maxAppresult.getResultPic());
        return appanswer;
    }
}
