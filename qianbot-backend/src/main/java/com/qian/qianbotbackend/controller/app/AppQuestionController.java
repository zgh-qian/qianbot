package com.qian.qianbotbackend.controller.app;

import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.model.app.domain.Appquestion;
import com.qian.qianbotbackend.model.app.dto.appquestion.AppquestionAddRequest;
import com.qian.qianbotbackend.model.app.dto.appquestion.AppquestionUpdateRequest;
import com.qian.qianbotbackend.service.AppquestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/question")
@Slf4j
public class AppQuestionController {
    @Resource
    private AppquestionService appquestionService;

    // region 增删改查

    /**
     * 添加题目
     *
     * @param appquestionAddRequest 添加题目请求
     * @return 题目id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addAppQuestion(@RequestBody AppquestionAddRequest appquestionAddRequest) {
        return ResultUtils.success(appquestionService.addAppQuestion(appquestionAddRequest));
    }

    /**
     * 删除题目
     *
     * @param deleteRequest 删除题目请求
     * @return 删除结果
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteAppQuestion(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(appquestionService.deleteAppQuestion(deleteRequest.getId()));
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> updateAppQuestion(@RequestBody AppquestionUpdateRequest appquestionUpdateRequest) {
        return ResultUtils.success(appquestionService.updateAppQuestion(appquestionUpdateRequest));
    }

    @GetMapping("/get")
    public BaseResponse<Appquestion> getAppQuestionById(Long appquestionId) {
        return ResultUtils.success(appquestionService.getAppQuestionById(appquestionId));
    }
    // endregion
}
