package com.qian.qianbotbackend.model.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class AppQuestionAndOptionVO implements Serializable {

    private AppquestionVO question;

    private List<AppoptionVO> optionList;

    private static final long serialVersionUID = 1L;
}
