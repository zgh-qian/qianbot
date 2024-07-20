package com.qian.qianbotbackend.constant;

import java.util.Arrays;
import java.util.List;

public interface ChartConstant {
    Long ONE_MB = 1024 * 1024L;

    String CHART_GEN_RUNNING = "图表正在生成中，请勿重复生成";

    String CHART_FILE_LARGE_MAX_SIZE = "文件超过 1M";

    String CHART_FILE_SUFFIX_NOT_LAW = "文件后缀非法";

    String CHART_DATA_NOT_LAW = "数据非法";

    List<String> validFileSuffixList = Arrays.asList("xlsx", "xls");
}
