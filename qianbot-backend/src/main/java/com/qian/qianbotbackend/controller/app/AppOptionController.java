package com.qian.qianbotbackend.controller.app;

import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.app.domain.Appoption;
import com.qian.qianbotbackend.model.app.dto.appoption.AppoptionAddRequest;
import com.qian.qianbotbackend.model.app.dto.appoption.AppoptionUpdateRequest;
import com.qian.qianbotbackend.service.AppoptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/option")
@Slf4j
public class AppOptionController {
    @Resource
    private AppoptionService appoptionService;

    // region 增删改查
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addAppOption(@RequestBody AppoptionAddRequest appoptionAddRequest) {
        return ResultUtils.success(appoptionService.addAppOption(appoptionAddRequest));
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteAppOption(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(appoptionService.deleteAppOption(deleteRequest.getId()));
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> updateAppOption(@RequestBody AppoptionUpdateRequest appoptionUpdateRequest) {
        return ResultUtils.success(appoptionService.updateAppOption(appoptionUpdateRequest));
    }

    @PostMapping("/get")
    public BaseResponse<Appoption> getAppOption(Long appoptionId) {
        return ResultUtils.success(appoptionService.getAppOption(appoptionId));
    }
    // endregion
}
