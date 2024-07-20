package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ReviewDTO;
import com.qian.qianbotbackend.model.app.domain.App;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.app.dto.app.*;
import com.qian.qianbotbackend.model.app.vo.AppAIGenerateVO;
import com.qian.qianbotbackend.model.app.vo.AppDetailVO;
import com.qian.qianbotbackend.model.app.vo.AppQuestionAndOptionVO;
import com.qian.qianbotbackend.model.app.vo.AppVO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author qian
 * @description 针对表【app(应用)】的数据库操作Service
 * @createDate 2024-07-07 15:27:30
 */
public interface AppService extends IService<App> {


    void validApp(App app, boolean add);


    Long addApp(AppAddRequest appAddRequest);

    Boolean deleteApp(DeleteRequest deleteRequest);

    Boolean editApp(AppEditRequest appEditRequest);

    AppVO getAppVO(App app);

    AppVO getAppVOById(Long id);

    Wrapper<App> getQueryWrapper(AppQueryRequest appQueryRequest);

    Page<AppVO> listAppVOByPage(AppQueryRequest appQueryRequest);

    Page<AppVO> listMyAppVOByPage(AppQueryRequest appQueryRequest);

    java.lang.Boolean updateApp(AppUpdateRequest appUpdateRequest);

    Page<App> listAppByPage(AppQueryRequest appQueryRequest);

    AppDetailVO getAppDetail(Long appId);

    Boolean doAppReview(ReviewDTO reviewDTO);

    Boolean addAndUpdateAppQuestionAndOption(AppQuestionOptionUpdateRequest appQuestionOptionUpdateRequest);

    List<AppQuestionAndOptionVO> getAppQuestionAndOption(Long appId);

    List<AppAIGenerateVO> getAIGenerateQuestionSync(AppAIGenerateRequest appAIGenerateRequest);

    SseEmitter getAIGenerateQuestionBySSE(AppAIGenerateRequest appAIGenerateRequest);

    Boolean doAIAppReview(Long appId);

    Boolean resetAppReviewStatus(Long appId);

}
