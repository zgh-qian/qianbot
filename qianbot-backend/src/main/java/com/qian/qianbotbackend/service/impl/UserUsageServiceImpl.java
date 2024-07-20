package com.qian.qianbotbackend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.user.UserUsageEnum;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.user.domain.UserUsage;
import com.qian.qianbotbackend.model.user.dto.userusage.UserUsageDTO;
import com.qian.qianbotbackend.service.UserService;
import com.qian.qianbotbackend.service.UserUsageService;
import com.qian.qianbotbackend.mapper.UserUsageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author qian
 * @description 针对表【userusage(用户使用表)】的数据库操作Service实现
 * @createDate 2024-07-18 16:56:02
 */
@Service
public class UserUsageServiceImpl extends ServiceImpl<UserUsageMapper, UserUsage>
        implements UserUsageService {
    @Resource
    private UserService userService;

    @Override
    public Boolean subUserUsage(UserUsageDTO userUsageDTO) {
        // 管理员可以不限制次数
        if (userService.isAdmin(BaseContext.getUserId())) {
            return true;
        }
        ThrowUtils.throwIf(userUsageDTO == null, ErrorCode.NOT_FOUND_ERROR);
        UserUsage userUsage = getUserUsage(userUsageDTO);
        // 判断剩余次数是否大于0
        Integer remainingCount = userUsage.getRemainingCount();
        if (remainingCount > 0) {
            userUsage.setUsedCount(userUsage.getUsedCount() + 1);
            userUsage.setRemainingCount(remainingCount - 1);
            boolean update = this.updateById(userUsage);
            ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
            return true;
        }
        return false;
    }

    @Override
    public UserUsage getUserUsage(UserUsageDTO userUsageDTO) {
        ThrowUtils.throwIf(userUsageDTO == null, ErrorCode.NOT_FOUND_ERROR);
        Long usageId = userUsageDTO.getUsageId();
        String usageType = userUsageDTO.getUsageType();
        UserUsage userUsage = this.getOne(Wrappers.lambdaQuery(UserUsage.class)
                .eq(UserUsage::getUserId, BaseContext.getUserId())
                .eq(usageId != null, UserUsage::getUsageId, userUsageDTO.getUsageId())
                .eq(usageType != null, UserUsage::getUsageType, userUsageDTO.getUsageType())
        );
        if (userUsage == null) {
            // 如果不存在，则新增
            userUsage = new UserUsage();
            BeanUtils.copyProperties(userUsageDTO, userUsage);
            UserUsageEnum userUsageEnum = UserUsageEnum.getEnumByText(userUsage.getUsageType());
            ThrowUtils.throwIf(userUsageEnum == null, ErrorCode.PARAMS_ERROR);
            userUsage.setUserId(BaseContext.getUserId());
            // 设置默认值
            userUsage.setUsedCount(0);
            userUsage.setRemainingCount(userUsageEnum.getValue());
            boolean save = this.save(userUsage);
            ThrowUtils.throwIf(!save, ErrorCode.SYSTEM_ERROR);
        } else {
            // 判断更新时间是否不是同一天，不是则重置
            LocalDateTime curDateTime = LocalDateTime.now();
            LocalDateTime preDateTime = LocalDateTime.ofInstant(userUsage.getUpdateTime().toInstant(), ZoneId.systemDefault());
            if (preDateTime.toLocalDate().isBefore(curDateTime.toLocalDate())) {
                UserUsageEnum userUsageEnum = UserUsageEnum.getEnumByText(userUsage.getUsageType());
                ThrowUtils.throwIf(userUsageEnum == null, ErrorCode.PARAMS_ERROR);
                userUsage.setRemainingCount(userUsageEnum.getValue());
                boolean update = this.updateById(userUsage);
                ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
            }
        }
        return userUsage;
    }
}




