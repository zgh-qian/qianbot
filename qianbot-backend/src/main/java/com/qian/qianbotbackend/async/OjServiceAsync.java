package com.qian.qianbotbackend.async;

import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.enums.oj.OjJudgeStatusEnum;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeRequest;
import com.qian.qianbotbackend.model.codesandbox.OjExecutionCodeResponse;
import com.qian.qianbotbackend.model.oj.domain.OjJudgecase;
import com.qian.qianbotbackend.model.oj.domain.OjSubmit;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeCase;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeConfig;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeContext;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeInfo;
import com.qian.qianbotbackend.service.OjJudgecaseService;
import com.qian.qianbotbackend.service.OjQuestionService;
import com.qian.qianbotbackend.service.impl.OjSubmitServiceImpl;
import com.qian.qianbotbackend.strategy.oj.OjJudgeStrategyExecutor;
import com.qian.qianbotbackend.strategy.oj.codesandbox.CodeSandbox;
import com.qian.qianbotbackend.strategy.oj.codesandbox.factory.CodeSandboxSingletonFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OjServiceAsync {
    @Lazy
    @Resource
    private OjQuestionService ojQuestionService;

    @Lazy
    @Resource
    private OjSubmitServiceImpl ojSubmitService;

    @Lazy
    @Resource
    private OjJudgecaseService ojJudgecaseService;

    @Resource
    private OjJudgeStrategyExecutor ojJudgeStrategyExecutor;

    @Async
    public void judge(Long id) {
        OjSubmit ojSubmit = null;
        boolean update = false;
        Integer judgeStatus = null;
        try {
            // 获取提交信息
            ojSubmit = ojSubmitService.getOjSubmit(id);
            ThrowUtils.throwIf(ojSubmit == null, ErrorCode.NOT_FOUND_ERROR, "提交记录不存在");
            Long questionId = ojSubmit.getQuestionId();
            OjQuestionDetailDTO ojQuestionDetailDTO = ojQuestionService.getOjQuestionDetailDTO(questionId);
            List<OjJudgecase> ojJudgecaseByQuestionId = ojJudgecaseService.getOjJudgecaseByQuestionId(questionId);
            // 更新判题状态为判题中
            ojSubmit.setJudgeStatus(OjJudgeStatusEnum.OJ_JUDGE_STATUS_ENUM_RUNNING.getValue());
            update = ojSubmitService.updateById(ojSubmit);
            ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
            List<OjJudgeCase> ojJudgeCaseList = ojJudgecaseByQuestionId.stream().map(ojJudgecase -> new OjJudgeCase(ojJudgecase.getInput(), ojJudgecase.getOutput())).collect(Collectors.toList());
            // 获取代码沙箱
            CodeSandbox codeSandbox = CodeSandboxSingletonFactory.getInstance().newInstanceBySingletonFactoryProxy("default");
            // 设置请求参数
            List<String> inputList = ojJudgecaseByQuestionId.stream().map(OjJudgecase::getInput).collect(Collectors.toList());
            OjJudgeConfig ojJudgeConfig = new OjJudgeConfig();
            ojJudgeConfig.setTimeLimit(ojQuestionDetailDTO.getTimeLimit());
            ojJudgeConfig.setMemoryLimit(ojQuestionDetailDTO.getMemoryLimit());
            OjExecutionCodeRequest ojExecutionCodeRequest = OjExecutionCodeRequest.builder()
                    .language(ojSubmit.getLanguage())
                    .code(ojSubmit.getCode())
                    .inputList(inputList)
                    .ojJudgeConfig(ojJudgeConfig)
                    .build();
            // 调用代码沙箱
            OjExecutionCodeResponse ojExecutionCodeResponse = codeSandbox.execute(ojExecutionCodeRequest);
            OjJudgeContext ojJudgeContext = new OjJudgeContext();
            // 判题
            ojJudgeContext.setExitCode(ojExecutionCodeResponse.getStatus());
            ojJudgeContext.setMessage(ojExecutionCodeResponse.getMessage());
            ojJudgeContext.setJudgeInfo(ojExecutionCodeResponse.getJudgeInfo());
            ojJudgeContext.setInputList(inputList);
            ojJudgeContext.setOutputList(ojExecutionCodeResponse.getOutputList());
            ojJudgeContext.setOjQuestionDetailDTO(ojQuestionDetailDTO);
            ojJudgeContext.setOjJudgeCaseList(ojJudgeCaseList);
            ojJudgeContext.setOjSubmit(ojSubmit);
            OjJudgeInfo ojJudgeInfo = ojJudgeStrategyExecutor.doJudge(ojJudgeContext);
            // 整理判题结果
            String status = ojJudgeInfo.getStatus();
            String message = ojJudgeInfo.getMessage();
            Long time = ojJudgeInfo.getTime();
            Long memory = ojJudgeInfo.getMemory();
            ojSubmit.setStatus(status);
            ojSubmit.setDetail(message);
            ojSubmit.setTimeUsed(time);
            ojSubmit.setMemoryUsed(memory);
            // 更新判题结果
            judgeStatus = OjJudgeStatusEnum.OJ_JUDGE_STATUS_ENUM_SUCCESS.getValue();
        } catch (Exception e) {
            // 更新判题结果
            judgeStatus = OjJudgeStatusEnum.OJ_JUDGE_STATUS_ENUM_FAILURE.getValue();
        }
        ojSubmit.setJudgeStatus(judgeStatus);
        update = ojSubmitService.updateById(ojSubmit);
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
    }
}
