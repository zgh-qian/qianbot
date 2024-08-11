package com.qian.qianbotbackend.strategy.oj.impl;

import com.qian.qianbotbackend.enums.oj.OjStatusEnum;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeCase;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeContext;
import com.qian.qianbotbackend.model.oj.judge.OjJudgeInfo;
import com.qian.qianbotbackend.strategy.oj.OjJudgeStrategy;
import com.qian.qianbotbackend.strategy.oj.OjJudgeStrategyConfig;

import java.util.List;
import java.util.Optional;

import static com.qian.qianbotbackend.constant.OjConstant.CODE_ERROR;

@OjJudgeStrategyConfig(Language = "java")
public class OjJudgeStrategyJavaImpl implements OjJudgeStrategy {
    @Override
    public OjJudgeInfo doJudge(OjJudgeContext ojJudgeContext) {
        OjJudgeInfo judgeInfoResponse = new OjJudgeInfo();
        // 如果退出码异常
        if (ojJudgeContext.getExitCode().equals(CODE_ERROR)) {
            judgeInfoResponse.setStatus(OjStatusEnum.WRONG_ANSWER.getValue());
            String message = ojJudgeContext.getMessage();
            OjStatusEnum ojStatusEnum = OjStatusEnum.getEnumByValue(message);
            if (ojStatusEnum != null) {
                judgeInfoResponse.setStatus(message);
                judgeInfoResponse.setMessage(ojStatusEnum.getText());
            } else {
                judgeInfoResponse.setMessage(message);
            }
            return judgeInfoResponse;
        }
        // 根据上下文获取数据
        OjJudgeInfo judgeInfo = ojJudgeContext.getJudgeInfo();
        List<String> inputList = ojJudgeContext.getInputList();
        List<String> outputList = ojJudgeContext.getOutputList();
        OjQuestionDetailDTO ojQuestionDetailDTO = ojJudgeContext.getOjQuestionDetailDTO();
        List<OjJudgeCase> judgeCaseList = ojJudgeContext.getOjJudgeCaseList();
        // 获取判题信息
        Long memory = Optional.ofNullable(judgeInfo.getMemory()).orElse(0L); // 内存，如果为空，则默认为0
        Long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L); // 时间，如果为空，则默认为0
        String errorInput = judgeInfo.getErrorInput();
        // 初始化判题信息
        judgeInfoResponse.setStatus(OjStatusEnum.ACCEPTED.getValue());
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMessage(errorInput);
        // 如果输出用例和输入列表长度不一致，说明判题失败
        if (outputList.size() != inputList.size()) {
            judgeInfoResponse.setStatus(OjStatusEnum.WRONG_ANSWER.getValue());
            return judgeInfoResponse;
        }
        // 遍历输出列表和输入列表，如果不一致，说明判题失败
        for (int i = 0; i < judgeCaseList.size(); i++) {
            OjJudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoResponse.setStatus(OjStatusEnum.WRONG_ANSWER.getValue());
                judgeInfoResponse.setMessage(judgeCase.getInput() + "\n" + judgeCase.getOutput());
                return judgeInfoResponse;
            }
        }
        // 判断题目限制
        Long timeLimit = ojQuestionDetailDTO.getTimeLimit();
        Long memoryLimit = ojQuestionDetailDTO.getMemoryLimit();
        // 判断时间限制
        if (time > timeLimit) {
            judgeInfoResponse.setStatus(OjStatusEnum.TIME_LIMIT_EXCEEDED.getValue());
        }
        // 判断内存限制
        if (memory > memoryLimit) {
            judgeInfoResponse.setStatus(OjStatusEnum.MEMORY_LIMIT_EXCEEDED.getValue());
        }
        // 判题通过
        return judgeInfoResponse;
    }
}
