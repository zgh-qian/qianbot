package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.model.user.domain.UserCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeAddRequest;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeQueryRequest;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeUpdateRequest;
import com.qian.qianbotbackend.model.user.vo.UserCodeSelfVO;
import com.qian.qianbotbackend.model.user.vo.UserCodeVO;

/**
 * @author qian
 * @description 针对表【usercode(用户代码表)】的数据库操作Service
 * @createDate 2024-08-02 11:08:50
 */
public interface UserCodeService extends IService<UserCode> {
    void valid(UserCode userCode, boolean isAdd);

    Long addUserCode(UserCodeAddRequest userCodeAddRequest);

    Boolean deleteUserCode(Long id);

    Boolean updateUserCode(UserCodeUpdateRequest userCodeUpdateRequest);

    UserCode getUserCodeById(Long id);

    UserCodeVO getUserCodeVOById(Long id);

    UserCodeSelfVO getUserCodeSelfVOById(Long id);

    Page<UserCode> getUserCodeList(UserCodeQueryRequest userCodeQueryRequest);

    Page<UserCodeVO> getUserCodeVOList(UserCodeQueryRequest userCodeQueryRequest);

    Page<UserCodeSelfVO> getUserCodeSelfVOList(UserCodeQueryRequest userCodeQueryRequest);
}
