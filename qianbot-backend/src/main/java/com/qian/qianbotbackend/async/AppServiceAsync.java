package com.qian.qianbotbackend.async;

import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.app.AppAnswerStatusEnum;
import com.qian.qianbotbackend.enums.app.AppReviewStatsEnum;
import com.qian.qianbotbackend.enums.app.AppTypeEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.manager.AIManager;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.domain.Appoption;
import com.qian.qianbotbackend.model.app.domain.Appquestion;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;
import com.qian.qianbotbackend.service.AppService;
import com.qian.qianbotbackend.service.AppanswerService;
import com.qian.qianbotbackend.service.AppoptionService;
import com.qian.qianbotbackend.service.AppquestionService;
import com.qian.qianbotbackend.strategy.app.AppStrategyExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.qian.qianbotbackend.constant.AIConstant.AI_REVIEW_APP_SYSTEM_MESSAGE;
import static com.qian.qianbotbackend.constant.AppConstant.APP_OPTION_NULL;
import static com.qian.qianbotbackend.constant.AppConstant.APP_QUESTION_NULL;

@Service
@Slf4j
public class AppServiceAsync {
    @Resource
    @Lazy
    private AppService appService;

    @Resource
    @Lazy
    private AppanswerService appanswerService;

    @Resource
    @Lazy
    private AppquestionService appquestionService;

    @Resource
    @Lazy
    private AppoptionService appoptionService;

    @Resource
    private AIManager aiManager;

    @Resource
    @Lazy
    private AppStrategyExecutor appStrategyExecutor;

    @Async
    public void doAIAppReviewAsync(App app) {
        // 封装 Prompt
        String userMessage = getAppReviewUserMessage(app);
        // AI 生成
        String result = aiManager.doSyncStableRequest(AI_REVIEW_APP_SYSTEM_MESSAGE, userMessage);
        int start = result.indexOf("{");
        int end = result.lastIndexOf("}");
        String jsonStr = result.substring(start, end + 1);
        App newApp = JSONUtil.toBean(jsonStr, App.class);
        // 检查审核状态
        ThrowUtils.throwIf(
                !newApp.getReviewStatus().equals(AppReviewStatsEnum.REVIEW_STATS_ENUM_PASS.getValue()) &&
                        !newApp.getReviewStatus().equals(AppReviewStatsEnum.REVIEW_STATS_ENUM_REJECT.getValue()),
                ErrorCode.SYSTEM_ERROR);
        newApp.setId(app.getId());
        boolean update = appService.updateById(newApp);
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
    }

    private String getAppReviewUserMessage(App app) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append(app.getAppName()).append("\n");
        userMessage.append(app.getAppDesc()).append("\n");
        userMessage.append(AppTypeEnum.getEnumByValue(app.getAppType()).getText()).append("\n");
        List<AppDetail> appDetailList = new ArrayList<>();
        List<Appquestion> appQuestionList = appquestionService.getAppQuestionList(app.getId());
        if (appQuestionList.isEmpty()) {
            app.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_REJECT.getValue());
            app.setReviewMessage(APP_QUESTION_NULL);
            appService.updateById(app);
            throw new BusinessException(ErrorCode.OPERATION_ERROR, APP_QUESTION_NULL);
        }
        for (Appquestion appquestion : appQuestionList) {
            String questionName = appquestion.getQuestionName();
            List<Appoption> appOptionList = appoptionService.getAppOptionList(appquestion.getId());
            if (appOptionList.isEmpty()) {
                app.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_REJECT.getValue());
                app.setReviewMessage(APP_OPTION_NULL);
                appService.updateById(app);
                throw new BusinessException(ErrorCode.OPERATION_ERROR, APP_OPTION_NULL);
            }
            AppDetail appDetail = new AppDetail();
            appDetail.setQuestionName(questionName);
            appDetail.setOptionList(appOptionList.stream().map(Appoption::getOptionName).collect(Collectors.toList()));
            appDetailList.add(appDetail);
        }
        userMessage.append(JSONUtil.toJsonStr(appDetailList));
        return userMessage.toString();
    }

    @Data
    private static class AppDetail {
        private String questionName;
        private List<String> optionList;
    }

    /**
     * 异步更新结果
     *
     * @param appAnswerDTO appAnswerDTO
     */
    @Async
    public void asyncUpdateAppAnswer(AppAnswerDTO appAnswerDTO) {
        log.info(Thread.currentThread().getName());
        Long id = appAnswerDTO.getId();
        Appanswer newAppanswer = null;
        try {
            newAppanswer = appStrategyExecutor.doScore(appAnswerDTO);
            newAppanswer.setResultStatus(AppAnswerStatusEnum.SUCCESS.getValue());
        } catch (Exception e) {
            newAppanswer = new Appanswer();
            newAppanswer.setId(id);
            newAppanswer.setResultStatus(AppAnswerStatusEnum.FAILURE.getValue());
        } finally {
            appanswerService.updateById(newAppanswer);
        }
    }
}
