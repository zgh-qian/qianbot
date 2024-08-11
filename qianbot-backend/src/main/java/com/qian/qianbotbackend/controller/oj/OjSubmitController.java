package com.qian.qianbotbackend.controller.oj;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.oj.dto.OjSubmitAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjSubmitQueryRequest;
import com.qian.qianbotbackend.model.oj.vo.OjSubmitVO;
import com.qian.qianbotbackend.service.OjSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/oj/submit")
public class OjSubmitController {
    @Resource
    private OjSubmitService ojSubmitService;

    /**
     * 添加OJ提交记录
     *
     * @param ojSubmitAddRequest 提交请求
     * @return 提交记录id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addOjSubmit(@RequestBody OjSubmitAddRequest ojSubmitAddRequest) {
        return ResultUtils.success(ojSubmitService.addOjSubmit(ojSubmitAddRequest));
    }

    /**
     * 删除OJ提交记录
     *
     * @param deleteRequest 删除请求
     * @return 是否删除成功
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteOjSubmit(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(ojSubmitService.deleteOjSubmit(deleteRequest.getId()));
    }

    /**
     * 获取OJ提交记录列表
     *
     * @param ojSubmitQueryRequest 查询请求
     * @return OJ提交记录列表
     */
    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Page<OjSubmitVO>> getOjSubmitVOList(@RequestBody OjSubmitQueryRequest ojSubmitQueryRequest) {
        return ResultUtils.success(ojSubmitService.getOjSubmitVOList(ojSubmitQueryRequest));
    }

    /**
     * 获取OJ提交记录
     *
     * @param id 提交记录id
     * @return OJ提交记录
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<OjSubmitVO> getOjSubmitVO(Long id) {
        return ResultUtils.success(ojSubmitService.getOjSubmitVO(id));
    }
}
