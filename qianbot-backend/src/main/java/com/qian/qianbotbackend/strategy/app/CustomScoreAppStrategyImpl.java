package com.qian.qianbotbackend.strategy.app;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qian.qianbotbackend.enums.app.AppAnswerStatusEnum;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.domain.Appresult;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import com.qian.qianbotbackend.service.*;

import javax.annotation.Resource;
import java.util.List;

@AppStrategyConfig(appType = 0, scoringStrategy = 0)
public class CustomScoreAppStrategyImpl implements AppStrategy {
    @Resource
    private AppoptionService appoptionService;

    @Resource
    private AppresultService appresultService;

    @Override
    public Appanswer doScore(AppAnswerDTO appAnswerDTO) throws Exception {
        Long id = appAnswerDTO.getId();
        Long appId = appAnswerDTO.getAppId();
        List<Long> userAnswer = appAnswerDTO.getUserAnswer();
        // 统计用户得分情况
        int totalScore = userAnswer.stream()
                .map(optionId -> Integer.parseInt(appoptionService.getById(optionId).getOptionResult()))
                .reduce(0, Integer::sum);
        List<Appresult> appresultList = appresultService.list(
                Wrappers.lambdaQuery(Appresult.class).eq(Appresult::getAppId, appId).orderByDesc(Appresult::getResultScore)
        );
        // 遍历得分结果，找到第一个用户分数大于得分范围的结果，作为最终结果
        Appresult maxAppresult = appresultList.get(0);
        for (Appresult appresult : appresultList) {
            if (totalScore >= appresult.getResultScore()) {
                maxAppresult = appresult;
                break;
            }
        }
        // 构造返回值，填充答案对象的属性
        Appanswer appanswer = new Appanswer();
        appanswer.setId(id);
        appanswer.setResultStatus(AppAnswerStatusEnum.SUCCESS.getValue());
        appanswer.setResultId(maxAppresult.getId());
        appanswer.setResultName(maxAppresult.getResultName());
        appanswer.setResultDesc(maxAppresult.getResultDesc());
        appanswer.setResultScore(maxAppresult.getResultScore());
        appanswer.setResultPic(maxAppresult.getResultPic());
        return appanswer;
    }
}
