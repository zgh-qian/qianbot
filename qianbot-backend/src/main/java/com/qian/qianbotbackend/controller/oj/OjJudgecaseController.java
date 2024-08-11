package com.qian.qianbotbackend.controller.oj;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseQueryRequest;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseUpdateRequest;
import com.qian.qianbotbackend.model.oj.vo.OjJudgecaseVO;
import com.qian.qianbotbackend.service.OjJudgecaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/oj/judgecase")
public class OjJudgecaseController {
    @Resource
    private OjJudgecaseService ojJudgecaseService;

    /**
     * 添加判题用例
     *
     * @param ojJudgecaseAddRequest 添加请求
     * @return 判题用例id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> addOjJudgecase(@RequestBody OjJudgecaseAddRequest ojJudgecaseAddRequest) {
        ojJudgecaseService.addOjJudgecase(ojJudgecaseAddRequest);
        return ResultUtils.success(true);
    }

    /**
     * 删除判题用例
     *
     * @param deleteRequest 删除请求
     * @return 是否删除成功
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> deleteOjJudgecase(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(ojJudgecaseService.deleteOjJudgecase(deleteRequest.getId()));
    }

    /**
     * 更新判题用例
     *
     * @param ojJudgecaseUpdateRequest 更新请求
     * @return 是否更新成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_ADMIN)
    public BaseResponse<Boolean> updateOjJudgecase(@RequestBody OjJudgecaseUpdateRequest ojJudgecaseUpdateRequest) {
        return ResultUtils.success(ojJudgecaseService.updateOjJudgecase(ojJudgecaseUpdateRequest));
    }

    /**
     * 根据题目id获取判题用例列表
     *
     * @param ojJudgecaseQueryRequest 查询请求
     * @return 判题用例列表
     */
    @PostMapping("/list")
    public BaseResponse<Page<OjJudgecaseVO>> getOjJudgecaseList(@RequestBody OjJudgecaseQueryRequest ojJudgecaseQueryRequest) {
        return ResultUtils.success(ojJudgecaseService.getOjJudgecaseVOList(ojJudgecaseQueryRequest));
    }

    /**
     * 根据id获取判题用例
     *
     * @param id id
     * @return 判题用例
     */
    @GetMapping("/get")
    public BaseResponse<OjJudgecaseVO> getOjJudgecaseById(Long id) {
        return ResultUtils.success(ojJudgecaseService.getOjJudgecaseVOById(id));
    }
}
