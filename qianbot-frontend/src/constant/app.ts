// 审核状态枚举
export const REVIEW_STATUS_ENUM = {
  // 待审核
  REVIEWING: 0,
  // 审核中
  RUNNING: 1,
  // 审核通过
  PASS: 2,
  // 审核拒绝
  REJECT: 3,
};

// 审核状态映射
export const REVIEW_STATUS_MAP = {
  0: "待审核",
  1: "审核中",
  2: "审核通过",
  3: "审核拒绝",
};

// 应用类型枚举
export const APP_TYPE_ENUM = {
  // 得分类
  SCORE: 0,
  // 测评类
  TEST: 1,
};

// 应用类型映射
export const APP_TYPE_MAP = {
  0: "得分类",
  1: "测评类",
};

// 应用评分策略枚举
export const APP_SCORING_STRATEGY_ENUM = {
  // 自定义
  CUSTOM: 0,
  // AI
  AI: 1,
};

// 应用评分策略映射
export const APP_SCORING_STRATEGY_MAP = {
  0: "自定义",
  1: "AI",
};
export const getAppColor = (num: number) => {
  switch (num) {
    case 0:
      return "orange";
    case 1:
      return "blue";
    case 2:
      return "green";
    case 3:
      return "red";
    default:
      return "red";
  }
};
