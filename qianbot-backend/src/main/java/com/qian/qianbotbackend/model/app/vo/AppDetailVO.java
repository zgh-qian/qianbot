package com.qian.qianbotbackend.model.app.vo;

import lombok.Data;

import java.util.List;

@Data
public class AppDetailVO {
    private AppVO appVO;

    private AppresultVO appresultVO;

    private List<AppQuestionAndOptionVO> questionAndOptionVOList;
}
