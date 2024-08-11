package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.async.ChartServiceAsync;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.enums.ChartStatusEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.chart.domain.Chart;
import com.qian.qianbotbackend.model.chart.dto.ChartAddRequest;
import com.qian.qianbotbackend.model.chart.dto.ChartQueryRequest;
import com.qian.qianbotbackend.model.chart.dto.ChartUpdateRequest;
import com.qian.qianbotbackend.model.chart.vo.ChartVO;
import com.qian.qianbotbackend.service.ChartService;
import com.qian.qianbotbackend.mapper.ChartMapper;
import com.qian.qianbotbackend.service.UserService;
import com.qian.qianbotbackend.utils.ExcelUtils;
import com.qian.qianbotbackend.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.stream.Collectors;

import static com.qian.qianbotbackend.constant.ChartConstant.*;

/**
 * @author qian
 * @description 针对表【chart(图表信息表)】的数据库操作Service实现
 * @createDate 2024-07-09 21:26:37
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
        implements ChartService {
    @Resource
    private UserService userService;

    @Resource
    private ChartServiceAsync chartServiceAsync;

    @Override
    public void validChart(Chart chart, boolean isAdd) {
        ThrowUtils.throwIf(chart == null, ErrorCode.PARAMS_ERROR);
        String chartName = chart.getChartName();
        String chartGoal = chart.getChartGoal();
        String chartType = chart.getChartType();
        String chartData = chart.getChartData();
        if (isAdd) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(chartName, chartGoal, chartType, chartData), ErrorCode.PARAMS_ERROR);
        } else {
            ThrowUtils.throwIf(chartName != null && StringUtils.isBlank(chartName), ErrorCode.PARAMS_ERROR);
            ThrowUtils.throwIf(chartGoal != null && StringUtils.isBlank(chartGoal), ErrorCode.PARAMS_ERROR);
            ThrowUtils.throwIf(chartType != null && StringUtils.isBlank(chartType), ErrorCode.PARAMS_ERROR);
            ThrowUtils.throwIf(chartData != null && StringUtils.isBlank(chartData), ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(chartName) && chartName.length() > 75) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图表名称过长");
        }
        if (StringUtils.isNotBlank(chartGoal) && chartGoal.length() > 300) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图表目标过长");
        }
        if (StringUtils.isNotBlank(chartType) && chartType.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图表类型过长");
        }
    }

    @Override
    public Long addChart(ChartAddRequest chartAddRequest) {
        ThrowUtils.throwIf(chartAddRequest == null, ErrorCode.PARAMS_ERROR);
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartAddRequest, chart);
        validChart(chart, true);
        chart.setChartStatus(ChartStatusEnum.CHART_STATUS_ENUM_WAITING.getValue());
        chart.setUserId(BaseContext.getUserId());
        boolean save = this.save(chart);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        Long id = chart.getId();
        genChart(id);
        return id;
    }

    @Override
    public Long addChartByFile(ChartAddRequest chartAddRequest, MultipartFile multipartFile) {
        ThrowUtils.throwIf(chartAddRequest == null, ErrorCode.PARAMS_ERROR);
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartAddRequest, chart);
        String chartName = chart.getChartName();
        String chartGoal = chart.getChartGoal();
        String chartType = chart.getChartType();
        ThrowUtils.throwIf(StringUtils.isAnyBlank(chartName, chartGoal, chartType), ErrorCode.PARAMS_ERROR);
        // 校验文件
        long size = multipartFile.getSize();
        String originalFilename = multipartFile.getOriginalFilename();
        // 校验文件大小
        ThrowUtils.throwIf(size > ONE_MB, ErrorCode.PARAMS_ERROR, CHART_FILE_LARGE_MAX_SIZE);
        // 校验文件后缀 .xlsx .xls
        String suffix = FileUtil.getSuffix(originalFilename);
        ThrowUtils.throwIf(!validFileSuffixList.contains(suffix), ErrorCode.PARAMS_ERROR, CHART_FILE_SUFFIX_NOT_LAW);
        String chartData = ExcelUtils.excelToCsv(multipartFile);
        ThrowUtils.throwIf(StringUtils.isBlank(chartData), ErrorCode.PARAMS_ERROR, CHART_DATA_NOT_LAW);
        chart.setChartData(chartData);
        chart.setChartStatus(ChartStatusEnum.CHART_STATUS_ENUM_WAITING.getValue());
        chart.setUserId(BaseContext.getUserId());
        boolean save = this.save(chart);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        Long id = chart.getId();
        genChart(id);
        return id;
    }

    @Override
    public Boolean deleteChart(Long id) {
        Chart chart = getChartById(id);
        // 仅本人或管理员可删除
        Long userId = BaseContext.getUserId();
        if (!chart.getUserId().equals(userId) && !userService.isAdmin(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = this.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean updateChart(ChartUpdateRequest chartUpdateRequest) {
        ThrowUtils.throwIf(chartUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = chartUpdateRequest.getId();
        Chart chart = getChartById(id);
        ThrowUtils.throwIf(chart == null, ErrorCode.PARAMS_ERROR);
        // 仅本人可修改
        if (!chart.getUserId().equals(BaseContext.getUserId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        Chart newChart = new Chart();
        BeanUtils.copyProperties(chartUpdateRequest, newChart);
        validChart(newChart, false);
        newChart.setGenData("");
        newChart.setGenResult("");
        newChart.setChartStatus(ChartStatusEnum.CHART_STATUS_ENUM_WAITING.getValue());
        boolean update = this.updateById(newChart);
        ThrowUtils.throwIf(!update, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Chart getChartById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        Chart chart = this.getById(id);
        ThrowUtils.throwIf(chart == null, ErrorCode.NOT_FOUND_ERROR);
        return chart;
    }

    @Override
    public ChartVO getChartVOById(Long id) {
        return ChartVO.objToVO(getChartById(id));
    }

    @Override
    public Page<Chart> getChartPage(ChartQueryRequest chartQueryRequest) {
        ThrowUtils.throwIf(chartQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = chartQueryRequest.getId();
        String chartName = chartQueryRequest.getChartName();
        String chartGoal = chartQueryRequest.getChartGoal();
        String chartType = chartQueryRequest.getChartType();
        Integer chartStatus = chartQueryRequest.getChartStatus();
        int current = chartQueryRequest.getCurrent();
        int pageSize = chartQueryRequest.getPageSize();
        String sortField = chartQueryRequest.getSortField();
        String sortOrder = chartQueryRequest.getSortOrder();
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        // 查询条件
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(chartType), "chartType", chartType);
        queryWrapper.eq(ObjectUtils.isNotEmpty(chartStatus), "chartStatus", chartStatus);
        queryWrapper.like(StringUtils.isNotBlank(chartName), "chartName", chartName);
        queryWrapper.like(StringUtils.isNotBlank(chartGoal), "chartGoal", chartGoal);
        // 补充字段
        queryWrapper.eq("userId", BaseContext.getUserId());
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<ChartVO> getChartVOPage(ChartQueryRequest chartQueryRequest) {
        Page<Chart> chartPage = getChartPage(chartQueryRequest);
        Page<ChartVO> chartVOPage = new Page<>();
        BeanUtils.copyProperties(chartPage, chartVOPage);
        chartVOPage.setRecords(chartPage.getRecords().stream().map(ChartVO::objToVO).collect(Collectors.toList()));
        return chartVOPage;
    }

    @Override
    public Boolean genChart(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        Chart chart = getChartById(id);
        ThrowUtils.throwIf(chart == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(chart.getChartStatus().equals(ChartStatusEnum.CHART_STATUS_ENUM_RUNNING.getValue()), ErrorCode.OPERATION_ERROR, CHART_GEN_RUNNING);
        chart.setChartStatus(ChartStatusEnum.CHART_STATUS_ENUM_RUNNING.getValue());
        boolean update = this.updateById(chart);
        ThrowUtils.throwIf(!update, ErrorCode.OPERATION_ERROR);
        chartServiceAsync.genChart(chart);
        return true;
    }
}




