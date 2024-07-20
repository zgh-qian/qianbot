package com.qian.qianbotbackend.strategy.app;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.app.AppScoringStrategyEnum;
import com.qian.qianbotbackend.enums.app.AppTypeEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Deprecated
public class AppStrategyContext {
    @Resource
    private CustomScoreAppStrategyImpl customScoreAppStrategy;
    @Resource
    private CustomTestAppStrategyImpl customTestAppStrategy;

    @Resource
    private AITestAppStrategyImpl aiTestAppStrategy;

    public Appanswer doScore(AppAnswerDTO appAnswerDTO) throws Exception {
        String errorMsg = "应用配置有误，未找到匹配的策略";
        App app = appAnswerDTO.getApp();
        AppTypeEnum appTypeEnum = AppTypeEnum.getEnumByValue(app.getAppType());
        AppScoringStrategyEnum appScoringStrategyEnum = AppScoringStrategyEnum.getEnumByValue(app.getScoringStrategy());
        if (appTypeEnum == null || appScoringStrategyEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMsg);
        }
        switch (appTypeEnum) {
            case APP_TYPE_ENUM_SCORE:
                switch (appScoringStrategyEnum) {
                    case SCORING_STRATEGY_ENUM_CUSTOM:
                        return customScoreAppStrategy.doScore(appAnswerDTO);
                    case SCORING_STRATEGY_ENUM_AI:
                        break;
                }
                break;
            case APP_TYPE_ENUM_TEST:
                switch (appScoringStrategyEnum) {
                    case SCORING_STRATEGY_ENUM_CUSTOM:
                        return customTestAppStrategy.doScore(appAnswerDTO);
                    case SCORING_STRATEGY_ENUM_AI:
                        return aiTestAppStrategy.doScore(appAnswerDTO);
                }
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMsg);
    }
}
