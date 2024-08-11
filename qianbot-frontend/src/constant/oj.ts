// 应用评分策略映射
export const OJ_DIFFICULTY_MAP = {
  0: "简单",
  1: "中等",
  2: "困难",
};
export const getOjDifficultyColor = (num: number) => {
  switch (num) {
    case 0:
      return "green";
    case 1:
      return "orange";
    case 2:
      return "red";
    default:
      return "green";
  }
};
export const getOjTagColor = (tag: string) => {
  switch (tag) {
    case "栈":
      return "green";
    case "队列":
      return "blue";
    case "链表":
      return "purple";
    case "树":
      return "brown";
    case "图":
      return "orange";
    case "哈希表":
      return "red";
    case "堆":
      return "cyan";
    case "双指针":
      return "teal";
    case "二分查找":
      return "indigo";
    case "动态规划":
      return "pink";
    case "贪心算法":
      return "lime";
    case "深度优先搜索":
      return "violet";
    case "广度优先搜索":
      return "yellow";
    case "并查集":
      return "amber";
    case "拓扑排序":
      return "lightblue";
    case "最短路径":
      return "deeporange";
    case "最小生成树":
      return "lightgreen";
    case "字符串匹配":
      return "cyan";
    case "位运算":
      return "bluegrey";
    case "几何算法":
      return "teal";
    case "线段树":
      return "indigo";
    case "树状数组":
      return "purple";
    case "并行算法":
      return "green";
    case "回溯算法":
      return "pink";
    case "滑动窗口":
      return "orange";
    case "扫描线算法":
      return "lime";
    case "快速幂":
      return "yellow";
    case "博弈论":
      return "deeporange";
    case "拆分算法":
      return "brown";
    case "分治算法":
      return "red";
    case "图论":
      return "blue";
    case "搜索算法":
      return "violet";
    case "剪枝算法":
      return "lightgreen";
    case "网络流":
      return "bluegrey";
    case "离散数学":
      return "lime";
    case "字符串":
      return "blue";
    case "数据结构":
      return "brown";
    default:
      return "gray";
  }
};
export const getOjStatusColor = (tag: string) => {
  switch (tag) {
    case "AC":
      return "green";
    case "WA":
      return "red";
    case "TLE":
      return "orange";
    case "MLE":
      return "purple";
    case "RE":
      return "brown";
    case "CE":
      return "gray";
    default:
      return "gray";
  }
};

export const getOjCodeLanguageColor = (tag: string) => {
  tag = tag.toLowerCase();
  switch (tag) {
    case "c":
      return "blue";
    case "cpp":
      return "red";
    case "java":
      return "orange";
    case "python":
      return "green";
    case "javascript":
      return "yellow";
    case "go":
      return "teal";
  }
};
