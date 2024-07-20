package com.qian.qianbotbackend.controller.chart;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.chart.dto.ChartAddRequest;
import com.qian.qianbotbackend.model.chart.dto.ChartQueryRequest;
import com.qian.qianbotbackend.model.chart.dto.ChartUpdateRequest;
import com.qian.qianbotbackend.model.chart.vo.ChartVO;
import com.qian.qianbotbackend.model.user.dto.userusage.UserUsageDTO;
import com.qian.qianbotbackend.service.ChartService;
import com.qian.qianbotbackend.service.UserUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.qian.qianbotbackend.enums.user.UserUsageEnum.USER_USAGE_ENUM_CHART;

@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {
    @Resource
    private ChartService chartService;

    @Resource
    private UserUsageService userUsageService;

    // region 增删改查

    /**
     * 添加图表
     *
     * @param chartAddRequest 添加图表请求
     * @return 图表id
     */
    @PostMapping("/add/data")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addChart(@RequestBody ChartAddRequest chartAddRequest) {
        return ResultUtils.success(chartService.addChart(chartAddRequest));
    }

    /**
     * 添加图表
     *
     * @param multipartFile   文件
     * @param chartAddRequest 添加图表请求
     * @return 图表id
     */
    @PostMapping("/add/file")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addChartByFile(@RequestPart("file") MultipartFile multipartFile, ChartAddRequest chartAddRequest) {
        // 校验是否存在上传次数
        ThrowUtils.throwIf(
                !userUsageService.subUserUsage(new UserUsageDTO(null, USER_USAGE_ENUM_CHART.getText(), null)),
                ErrorCode.OPERATION_ERROR,
                "AI 分析图表次数已用完");
        return ResultUtils.success(chartService.addChartByFile(chartAddRequest, multipartFile));
    }

    /**
     * 删除图表
     *
     * @param deleteRequest 删除请求
     * @return 是否删除成功
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteChart(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(chartService.deleteChart(deleteRequest.getId()));
    }

    /**
     * 更新图表
     *
     * @param chartUpdateRequest 更新图表请求
     * @return 是否更新成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> updateChart(@RequestBody ChartUpdateRequest chartUpdateRequest) {
        return ResultUtils.success(chartService.updateChart(chartUpdateRequest));
    }

    /**
     * 获取本人图表
     *
     * @param id 图表id
     * @return 图表列表
     */
    @GetMapping("/get/vo")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<ChartVO> getChartVOById(Long id) {
        return ResultUtils.success(chartService.getChartVOById(id));
    }

    /**
     * 获取本人图表
     *
     * @param chartQueryRequest 查询图表请求
     * @return 图表列表
     */
    @PostMapping("/get/vo/page")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Page<ChartVO>> getChartVOPage(@RequestBody ChartQueryRequest chartQueryRequest) {
        return ResultUtils.success(chartService.getChartVOPage(chartQueryRequest));
    }
    // endregion

    /**
     * 生成图表
     *
     * @param id 图表id
     * @return 是否生成成功
     */
    @GetMapping("/ai/gen")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> genChart(Long id) {
        return ResultUtils.success(chartService.genChart(id));
    }
}
