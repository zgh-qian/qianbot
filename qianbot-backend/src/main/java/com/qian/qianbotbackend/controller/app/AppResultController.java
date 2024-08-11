package com.qian.qianbotbackend.controller.app;

import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.app.dto.appresult.AppresultAddRequest;
import com.qian.qianbotbackend.model.app.dto.appresult.AppresultUpdateRequest;
import com.qian.qianbotbackend.model.app.vo.AppresultVO;
import com.qian.qianbotbackend.service.AppresultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app/result")
public class AppResultController {
    @Resource
    private AppresultService appresultService;

    // region 增删改查

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addAppResult(@RequestBody AppresultAddRequest appresultAddRequest) {
        return ResultUtils.success(appresultService.addAppResult(appresultAddRequest));
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteAppResult(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(appresultService.deleteAppResult(deleteRequest.getId()));
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> updateAppResult(@RequestBody AppresultUpdateRequest appresultUpdateRequest) {
        return ResultUtils.success(appresultService.updateAppResult(appresultUpdateRequest));
    }

    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<AppresultVO> getAppResultById(Long id) {
        return ResultUtils.success(appresultService.getAppResultById(id));
    }

    @GetMapping("/get/list")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<List<AppresultVO>> getAppResultByAppId(Long appId) {
        return ResultUtils.success(appresultService.getAppResultByAppId(appId));
    }
    // endregion
}
