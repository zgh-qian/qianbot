package com.qian.qianbotbackend.controller.user;

import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.enums.user.UserUsageEnum;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.user.dto.userusage.UserUsageDTO;
import com.qian.qianbotbackend.service.UserUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user/usage")
public class UserUsageController {
    @Resource
    private UserUsageService userUsageService;

    /**
     * 获取默认剩余使用次数
     * @param userUsageDTO  userUsageDTO
     * @return 默认剩余使用次数
     */
    @PostMapping("/query/default")
    @AuthCheck()
    public BaseResponse<Integer> getDefaultRemainingCount(@RequestBody UserUsageDTO userUsageDTO) {
        UserUsageEnum userUsageEnum = UserUsageEnum.getEnumByText(userUsageDTO.getUsageType());
        ThrowUtils.throwIf(userUsageEnum == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userUsageEnum.getValue());
    }

    /**
     * 获取用户剩余使用次数
     *
     * @param userUsageDTO userUsageDTO
     * @return 用户剩余使用次数
     */
    @PostMapping("/query")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Integer> getUserRemainingCount(@RequestBody UserUsageDTO userUsageDTO) {
        return ResultUtils.success(userUsageService.getUserUsage(userUsageDTO).getRemainingCount());
    }
}
