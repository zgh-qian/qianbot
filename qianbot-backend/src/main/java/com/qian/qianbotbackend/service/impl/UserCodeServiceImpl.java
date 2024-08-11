package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.user.domain.UserCode;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeAddRequest;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeQueryRequest;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeUpdateRequest;
import com.qian.qianbotbackend.model.user.vo.UserCodeSelfVO;
import com.qian.qianbotbackend.model.user.vo.UserCodeVO;
import com.qian.qianbotbackend.service.UserCodeService;
import com.qian.qianbotbackend.mapper.UserCodeMapper;
import com.qian.qianbotbackend.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @description 针对表【usercode(用户代码表)】的数据库操作Service实现
 * @createDate 2024-08-02 11:08:50
 */
@Service
public class UserCodeServiceImpl extends ServiceImpl<UserCodeMapper, UserCode>
        implements UserCodeService {

    @Override
    public void valid(UserCode userCode, boolean isAdd) {
        Long id = userCode.getId();
        String codeTitle = userCode.getCodeTitle();
        String codeLanguage = userCode.getCodeLanguage();
        String codeContent = userCode.getCodeContent();
        String codeType = userCode.getCodeType();
        String codeTags = userCode.getCodeTags();
        String codeDesc = userCode.getCodeDesc();
        String codePwd = userCode.getCodePwd();
        Date expireTime = userCode.getExpireTime();
        Integer isPublic = userCode.getIsPublic();
        Integer isExpire = userCode.getIsExpire();
        if (isAdd) {
            if (StringUtils.isAnyBlank(codeTitle, codeLanguage, codeContent)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        } else {
            if (id == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (codeTitle != null && codeTitle.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (codeLanguage != null && codeLanguage.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "语言过长");
        }
        if (codeContent != null && codeContent.length() > 10000) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (codeType != null && codeType.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "类型过长");
        }
        if (codeTags != null && codeTags.length() > 128) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标签过长");
        }
        if (codeDesc != null && codeDesc.length() > 5000) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }
        if (codePwd != null && codePwd.length() != 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度应该为4位");
        }
        if (expireTime != null && expireTime.before(new Date())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "过期时间不能早于当前时间");
        }
        // isPublic设置了1，则必须不设置密码
        if (isPublic != null && isPublic == 1 && codePwd != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "公开的代码不应该设置密码");
        }
        // isExpire设置了1，则必须设置过期时间
        if (isExpire != null && isExpire == 1 && expireTime == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "设置了代码过期，必须设置过期时间");
        }
    }

    @Override
    public Long addUserCode(UserCodeAddRequest userCodeAddRequest) {
        ThrowUtils.throwIf(userCodeAddRequest == null, ErrorCode.PARAMS_ERROR);
        UserCode userCode = BeanUtil.copyProperties(userCodeAddRequest, UserCode.class);
        List<String> codeTags = userCodeAddRequest.getCodeTags();
        if (codeTags != null) {
            userCode.setCodeTags(JSONUtil.toJsonStr(codeTags));
        }
        valid(userCode, true);
        userCode.setUserId(BaseContext.getUserId());
        ThrowUtils.throwIf(!this.save(userCode), ErrorCode.SYSTEM_ERROR);
        return userCode.getId();
    }

    @Override
    public Boolean deleteUserCode(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        getUserCodeById(id);
        ThrowUtils.throwIf(!this.removeById(id), ErrorCode.SYSTEM_ERROR);
        return null;
    }

    @Override
    public Boolean updateUserCode(UserCodeUpdateRequest userCodeUpdateRequest) {
        ThrowUtils.throwIf(userCodeUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        getUserCodeById(userCodeUpdateRequest.getId());
        UserCode userCode = BeanUtil.copyProperties(userCodeUpdateRequest, UserCode.class);
        List<String> codeTags = userCodeUpdateRequest.getCodeTags();
        if (codeTags != null) {
            userCode.setCodeTags(JSONUtil.toJsonStr(codeTags));
        }
        valid(userCode, false);
        ThrowUtils.throwIf(!this.updateById(userCode), ErrorCode.SYSTEM_ERROR);
        return true;
    }

    @Override
    public UserCode getUserCodeById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        UserCode userCode = this.getById(id);
        ThrowUtils.throwIf(userCode == null, ErrorCode.NOT_FOUND_ERROR);
        return userCode;
    }

    @Override
    public UserCodeVO getUserCodeVOById(Long id) {
        return UserCodeVO.objToVO(getUserCodeById(id));
    }

    @Override
    public UserCodeSelfVO getUserCodeSelfVOById(Long id) {
        return UserCodeSelfVO.objToVO(getUserCodeById(id));
    }

    @Override
    public Page<UserCode> getUserCodeList(UserCodeQueryRequest userCodeQueryRequest) {
        Long id = userCodeQueryRequest.getId();
        String codeTitle = userCodeQueryRequest.getCodeTitle();
        String codeLanguage = userCodeQueryRequest.getCodeLanguage();
        String codeContent = userCodeQueryRequest.getCodeContent();
        String codeType = userCodeQueryRequest.getCodeType();
        List<String> codeTags = userCodeQueryRequest.getCodeTags();
        String codeDesc = userCodeQueryRequest.getCodeDesc();
        Long userId = userCodeQueryRequest.getUserId();
        int current = userCodeQueryRequest.getCurrent();
        int pageSize = userCodeQueryRequest.getPageSize();
        String sortField = userCodeQueryRequest.getSortField();
        String sortOrder = userCodeQueryRequest.getSortOrder();
        QueryWrapper<UserCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null && id > 0, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(codeTitle), "codeTitle", codeTitle);
        queryWrapper.eq(StringUtils.isNotBlank(codeLanguage), "codeLanguage", codeLanguage);
        queryWrapper.like(StringUtils.isNotBlank(codeContent), "codeContent", codeContent);
        queryWrapper.eq(StringUtils.isNotBlank(codeType), "codeType", codeType);
        if (codeTags != null && !codeTags.isEmpty()) {
            codeTags.forEach(codeTag -> {
                queryWrapper.like("codeTags", codeTag);
            });
        }
        queryWrapper.like(StringUtils.isNotBlank(codeDesc), "codeDesc", codeDesc);
        queryWrapper.eq(userId != null, "userId", userId);
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        queryWrapper.orderByDesc("updateTime");
        return this.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<UserCodeVO> getUserCodeVOList(UserCodeQueryRequest userCodeQueryRequest) {
        Page<UserCode> userCodeList = getUserCodeList(userCodeQueryRequest);
        Page<UserCodeVO> userCodeVOList = BeanUtil.copyProperties(userCodeList, Page.class);
        userCodeVOList.setRecords(userCodeList.getRecords().stream().map(UserCodeVO::objToVO).collect(Collectors.toList()));
        return userCodeVOList;
    }

    @Override
    public Page<UserCodeSelfVO> getUserCodeSelfVOList(UserCodeQueryRequest userCodeQueryRequest) {
        userCodeQueryRequest.setUserId(BaseContext.getUserId());
        Page<UserCode> userCodeList = getUserCodeList(userCodeQueryRequest);
        Page<UserCodeSelfVO> userCodeSelfVOList = BeanUtil.copyProperties(userCodeList, Page.class);
        userCodeSelfVOList.setRecords(userCodeList.getRecords().stream().map(UserCodeSelfVO::objToVO).collect(Collectors.toList()));
        return userCodeSelfVOList;
    }
}




