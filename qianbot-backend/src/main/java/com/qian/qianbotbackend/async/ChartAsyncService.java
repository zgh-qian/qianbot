package com.qian.qianbotbackend.async;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.ChartStatusEnum;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.manager.AIManager;
import com.qian.qianbotbackend.model.chart.domain.Chart;
import com.qian.qianbotbackend.service.ChartService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.qian.qianbotbackend.constant.AIConstant.AI_CHART_GENERATE_SYSTEM_MESSAGE;

@Service
public class ChartAsyncService {
    @Resource
    @Lazy
    private ChartService chartService;

    @Resource
    private AIManager aiManager;

    @Async
    public void genChart(Chart chart) {
        // 封装 Prompt
        String userMessage = getChartGenUserMessage(chart);
        String result = aiManager.doSyncStableRequest(AI_CHART_GENERATE_SYSTEM_MESSAGE, userMessage);
        int startIndex = result.indexOf("【【【【【");
        int endIndex = result.lastIndexOf("【【【【【");
        Integer chartStatus = ChartStatusEnum.CHART_STATUS_ENUM_SUCCESS.getValue();
        if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
            chartStatus = ChartStatusEnum.CHART_STATUS_ENUM_FAILURE.getValue();
        } else {
            String genData = result.substring(startIndex + 5, endIndex).trim().replaceAll("\n", "").replaceAll("\\s", "");
            String genResult = result.substring(endIndex + 5).trim();
            chart.setGenData(genData);
            int punctuation  = genResult.lastIndexOf("。");
            chart.setGenResult(genResult.substring(0, punctuation + 1));
        }
        chart.setChartStatus(chartStatus);
        boolean update = chartService.updateById(chart);
        ThrowUtils.throwIf(!update, ErrorCode.OPERATION_ERROR);
    }

    private String getChartGenUserMessage(Chart chart) {
        String chartName = chart.getChartName();
        String chartGoal = chart.getChartGoal();
        String chartType = chart.getChartType();
        String chartData = chart.getChartData();
        return chartName + "\n" +
                chartGoal + "\n" +
                chartType + "\n" +
                chartData + "\n";
    }
}
