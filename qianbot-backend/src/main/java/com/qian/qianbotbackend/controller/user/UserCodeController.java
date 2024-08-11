package com.qian.qianbotbackend.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeAddRequest;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeQueryRequest;
import com.qian.qianbotbackend.model.user.dto.usercode.UserCodeUpdateRequest;
import com.qian.qianbotbackend.model.user.vo.UserCodeSelfVO;
import com.qian.qianbotbackend.model.user.vo.UserCodeVO;
import com.qian.qianbotbackend.service.UserCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user/code")
public class UserCodeController {
    @Resource
    private UserCodeService userCodeService;

    /**
     * 用户代码添加
     *
     * @param userCodeAddRequest 请求
     * @return 添加代码id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addUserCode(@RequestBody UserCodeAddRequest userCodeAddRequest) {
        return ResultUtils.success(userCodeService.addUserCode(userCodeAddRequest));
    }

    /**
     * 用户代码删除
     *
     * @param deleteRequest 请求
     * @return 是否成功
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteUserCode(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(userCodeService.deleteUserCode(deleteRequest.getId()));
    }

    /**
     * 用户代码更新
     *
     * @param userCodeUpdateRequest 请求
     * @return 是否成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> updateUserCode(@RequestBody UserCodeUpdateRequest userCodeUpdateRequest) {
        return ResultUtils.success(userCodeService.updateUserCode(userCodeUpdateRequest));
    }

    /**
     * 获取用户代码
     *
     * @param id id
     * @return UserCodeVO
     */
    @GetMapping("/get")
    public BaseResponse<UserCodeVO> getUserCodeVOById(Long id) {
        return ResultUtils.success(userCodeService.getUserCodeVOById(id));
    }

    /**
     * 获取用户代码列表
     *
     * @param userCodeQueryRequest 请求
     * @return UserCodeVO
     */
    @PostMapping("/get/list")
    public BaseResponse<Page<UserCodeVO>> getUserCodeVOList(@RequestBody UserCodeQueryRequest userCodeQueryRequest) {
        return ResultUtils.success(userCodeService.getUserCodeVOList(userCodeQueryRequest));
    }

    /**
     * 获取本人代码
     *
     * @param id id
     * @return UserCodeSelfVO
     */
    @GetMapping("/get/self")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<UserCodeSelfVO> getUserCodeSelfVOById(Long id) {
        return ResultUtils.success(userCodeService.getUserCodeSelfVOById(id));
    }

    /**
     * 获取本人代码列表
     *
     * @param userCodeQueryRequest 请求
     * @return UserCodeSelfVO
     */
    @PostMapping("/get/self/list")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Page<UserCodeSelfVO>> getUserCodeSelfVOList(@RequestBody UserCodeQueryRequest userCodeQueryRequest) {
        return ResultUtils.success(userCodeService.getUserCodeSelfVOList(userCodeQueryRequest));
    }
}
