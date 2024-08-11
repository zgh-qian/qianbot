package com.qian.qianbotbackend.strategy.oj;


import com.qian.qianbotbackend.model.oj.judge.OjJudgeContext;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeInfo;

/**
 * 判题策略接口
 */
public interface OjJudgeStrategy {

    /**
     * 执行判题
     *
     * @param ojJudgeContext 判题上下文
     * @return 判题结果
     */
    OjJudgeInfo doJudge(OjJudgeContext ojJudgeContext);
}
