package com.qian.qianbotbackend.strategy.oj;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.model.oj.domain.OjSubmit;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeCase;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeContext;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OjJudgeStrategyExecutor implements OjJudgeStrategy {
    @Resource
    private List<OjJudgeStrategy> ojJudgeStrategyList;

    @Override
    public OjJudgeInfo doJudge(OjJudgeContext ojJudgeContext) {
        String errorMsg = "编程语言错误";
        for (OjJudgeStrategy strategy : ojJudgeStrategyList) {
            if (strategy.getClass().isAnnotationPresent(OjJudgeStrategyConfig.class)) {
                OjJudgeStrategyConfig ojJudgeStrategyConfig = strategy.getClass().getAnnotation(OjJudgeStrategyConfig.class);
                if (ojJudgeStrategyConfig.Language().equals(ojJudgeContext.getOjSubmit().getLanguage())) {
                    return strategy.doJudge(ojJudgeContext);
                }
            }
        }
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMsg);
    }
}
