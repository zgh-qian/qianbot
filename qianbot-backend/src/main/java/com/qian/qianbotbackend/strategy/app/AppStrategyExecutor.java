package com.qian.qianbotbackend.strategy.app;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppStrategyExecutor implements AppStrategy {
    @Resource
    private List<AppStrategy> appStrategyList;

    @Override
    public Appanswer doScore(AppAnswerDTO appAnswerDTO) throws Exception {
        App app = appAnswerDTO.getApp();
        String errorMsg = "应用配置有误，未找到匹配的策略";
        Integer appType = app.getAppType();
        Integer scoringStrategy = app.getScoringStrategy();
        if (appType == null || scoringStrategy == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMsg);
        }
        for (AppStrategy strategy : appStrategyList) {
            if (strategy.getClass().isAnnotationPresent(AppStrategyConfig.class)) {
                AppStrategyConfig scoringStrategyConfig = strategy.getClass().getAnnotation(AppStrategyConfig.class);
                if (scoringStrategyConfig.appType() == appType && scoringStrategyConfig.scoringStrategy() == scoringStrategy) {
                    return strategy.doScore(appAnswerDTO);
                }
            }
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMsg);
    }
}
