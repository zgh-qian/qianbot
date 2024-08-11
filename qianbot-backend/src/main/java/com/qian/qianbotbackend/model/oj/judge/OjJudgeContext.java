package com.qian.qianbotbackend.model.oj.judge;

import com.qian.qianbotbackend.model.oj.domain.OjSubmit;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import lombok.Data;

import java.util.List;

/**
 * OjJudgeContext
 */
@Data
public class OjJudgeContext {

    private Integer exitCode;

    private String message;

    private OjJudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private OjQuestionDetailDTO ojQuestionDetailDTO;

    private List<OjJudgeCase> ojJudgeCaseList;

    private OjSubmit ojSubmit;
}
