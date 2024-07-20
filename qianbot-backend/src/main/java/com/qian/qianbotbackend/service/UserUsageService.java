package com.qian.qianbotbackend.service;

import com.qian.qianbotbackend.model.user.domain.UserUsage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.user.dto.userusage.UserUsageDTO;

/**
 * @author qian
 * @description 针对表【userusage(用户使用表)】的数据库操作Service
 * @createDate 2024-07-18 16:56:02
 */
public interface UserUsageService extends IService<UserUsage> {
    Boolean subUserUsage(UserUsageDTO userUsageDTO);

    UserUsage getUserUsage(UserUsageDTO userUsageDTO);
}
