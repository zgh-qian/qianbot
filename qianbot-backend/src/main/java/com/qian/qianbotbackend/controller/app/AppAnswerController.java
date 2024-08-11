package com.qian.qianbotbackend.controller.app;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.mapper.AppanswerMapper;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppanswerAddRequest;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppanswerQueryRequest;
import com.qian.qianbotbackend.model.app.vo.AppAnswerCountVO;
import com.qian.qianbotbackend.model.app.vo.AppAnswerResultNameCountVO;
import com.qian.qianbotbackend.model.app.vo.AppanswerVO;
import com.qian.qianbotbackend.service.AppanswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app/answer")
public class AppAnswerController {
    @Resource
    private AppanswerService appanswerService;

    // region 增删改查

    /**
     * 生成id
     *
     * @return 生成的id
     */
    @GetMapping("/gene/id")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> generateId() {
        return ResultUtils.success(IdUtil.getSnowflakeNextId());
    }

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Long> addAppAnswer(@RequestBody AppanswerAddRequest appanswerAddRequest) {
        return ResultUtils.success(appanswerService.addAppAnswer(appanswerAddRequest));
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Boolean> deleteAppAnswer(@RequestBody DeleteRequest deleteRequest) {
        return ResultUtils.success(appanswerService.deleteAppAnswer(deleteRequest.getId()));
    }

    @GetMapping("/get/vo")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<AppanswerVO> getAppAnswerVOById(Long id) {
        return ResultUtils.success(appanswerService.getAppAnswerVOById(id));
    }

    @PostMapping("/get/page/vo")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<Page<AppanswerVO>> getAppAnswerPageVO(@RequestBody AppanswerQueryRequest appanswerQueryRequest) {
        return ResultUtils.success(appanswerService.getAppAnswerPageVO(appanswerQueryRequest));
    }

    // endregion

    // region 统计相关接口
    @GetMapping("/stats/answer/count")
    public BaseResponse<List<AppAnswerCountVO>> getAppAnswerCount() {
        return ResultUtils.success(appanswerService.getAppAnswerCount());
    }

    @GetMapping("/stats/result/count")
    public BaseResponse<List<AppAnswerResultNameCountVO>> getAppResultCount(Long appId) {
        return ResultUtils.success(appanswerService.getAppResultCount(appId));
    }
    // endregion
}
