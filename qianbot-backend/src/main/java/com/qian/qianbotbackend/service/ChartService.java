package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.model.chart.domain.Chart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.chart.dto.ChartAddRequest;
import com.qian.qianbotbackend.model.chart.dto.ChartQueryRequest;
import com.qian.qianbotbackend.model.chart.dto.ChartUpdateRequest;
import com.qian.qianbotbackend.model.chart.vo.ChartVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qian
 * @description 针对表【chart(图表信息表)】的数据库操作Service
 * @createDate 2024-07-09 21:26:37
 */
public interface ChartService extends IService<Chart> {
    void validChart(Chart chart, boolean isAdd);

    Long addChart(ChartAddRequest chartAddRequest);

    Long addChartByFile(ChartAddRequest chartAddRequest, MultipartFile multipartFile);

    Boolean deleteChart(Long id);

    Boolean updateChart(ChartUpdateRequest chartUpdateRequest);

    Chart getChartById(Long id);

    ChartVO getChartVOById(Long id);

    Page<Chart> getChartPage(ChartQueryRequest chartQueryRequest);

    Page<ChartVO> getChartVOPage(ChartQueryRequest chartQueryRequest);

    Boolean genChart(Long id);

}
