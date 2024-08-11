package com.qian.qianbotbackend.constant;

public interface AppConstant {
    String APP_NAME_NOT_NULL = "应用名称不能为空";
    String APP_DESC_NOT_NULL = "应用描述不能为空";
    String APP_TYPE_NOT_TRUE = "应用类型不正确";
    String APP_SCORING_NOT_TRUE = "评分策略不正确";
    String APP_QUESTION_NULL = "该应用题目为空，请添加题目后审核";
    String APP_OPTION_NULL = "该应用选项为空，请添加选项后审核";
    String APP_REVIEW_RUNNING = "应用审核中，请勿重复审核";
    String APP_REVIEW_PASS = "应用已审核通过";
    String APP_REVIEW_REJECT = "应用已审核拒绝，请修改应用";

    /**
     * 分布式锁
     */
    String APP_ANSWER_LOCK = "APP_ANSWER_LOCK_";
}
