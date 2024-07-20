// 图形枚举
export const CHART_TYPE_ENUM = {};
export const CHART_TYPE_BAR_ENUM = {
  BAR: "柱状图",
  BAR_BASIC: "基础柱状图",
  BAR_STACKED: "堆叠柱状图",
  BAR_SORTABLE: "动态排序柱状图",
  BAR_WATERFALL: "阶梯瀑布图",
};
export const CHART_TYPE_LINE_ENUM = {
  LINE: "折线图",
  LINE_BASIC: "基础折线图",
  LINE_STACKED: "堆叠折线图",
  LINE_AREA: "区域面积图",
  LINE_SMOOTH: "平滑曲线图",
  LINE_STEP: "阶梯线图",
};
export const CHART_TYPE_PIE_ENUM = {
  PIE: "饼图",
  PIE_BASIC: "基础饼图",
  PIE_RING: "圆环图",
  PIE_ROSE: "南丁格尔图（玫瑰图）",
};
export const CHART_TYPE_SCATTER_ENUM = {
  SCATTER: "散点图",
  SCATTER_BASIC: "基础散点图",
};
export const CHART_STATUS_ENUM = {
  WAITING: 0,
  GENERATING: 1,
  SUCCESS: 2,
  FAIL: 3,
};

// 应用评分策略映射
export const CHART_STATUS_MAP = {
  0: "待生成",
  1: "生成中",
  2: "生成成功",
  3: "生成失败",
};
export const getChartColor = (num: number) => {
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
