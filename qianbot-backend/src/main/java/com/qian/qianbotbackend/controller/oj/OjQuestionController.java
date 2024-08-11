package com.qian.qianbotbackend.controller.oj;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailUpdateRequest;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionQueryRequest;
import com.qian.qianbotbackend.model.oj.vo.OjQuestionDetailVO;
import com.qian.qianbotbackend.model.oj.vo.OjQuestionVO;
import com.qian.qianbotbackend.service.OjDetailService;
import com.qian.qianbotbackend.service.OjJudgecaseService;
import com.qian.qianbotbackend.service.OjQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/oj/question")
public class OjQuestionController {
    @Resource
    private OjQuestionService ojQuestionService;

    /**
     * 添加题目
     *
     * @param ojQuestionDetailAddRequest 题目添加请求
     * @return 题目id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Long> addOjQuestionDetail(@RequestBody OjQuestionDetailAddRequest ojQuestionDetailAddRequest) {
        return ResultUtils.success(ojQuestionService.addOjQuestionDetail(ojQuestionDetailAddRequest));
    }

    /**
     * 更新题目
     *
     * @param ojQuestionDetailUpdateRequest 题目更新请求
     * @return true
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> updateOjQuestionDetail(@RequestBody OjQuestionDetailUpdateRequest ojQuestionDetailUpdateRequest) {
        return ResultUtils.success(ojQuestionService.updateOjQuestionDetail(ojQuestionDetailUpdateRequest));
    }

    /**
     * 删除题目
     *
     * @param deleteRequest 删除请求
     * @return true
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> deleteOjQuestionDetail(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(ojQuestionService.deleteOjQuestionDetail(deleteRequest.getId()));
    }

    /**
     * 获取题目列表
     *
     * @param ojQuestionQueryRequest 题目查询请求
     * @return 题目列表
     */
    @PostMapping("/list")
    public BaseResponse<Page<OjQuestionVO>> getOjQuestionVOList(@RequestBody OjQuestionQueryRequest ojQuestionQueryRequest) {
        return ResultUtils.success(ojQuestionService.getOjQuestionVOList(ojQuestionQueryRequest));
    }

    /**
     * 获取题目详情
     *
     * @param id 题目id
     * @return 题目详情
     */
    @GetMapping("/get")
    public BaseResponse<OjQuestionDetailVO> getOjQuestionDetail(Long id) {
        return ResultUtils.success(ojQuestionService.getOjQuestionDetail(id));
    }
}
