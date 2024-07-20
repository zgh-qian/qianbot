package com.qian.qianbotbackend.strategy.app;

import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppAnswerDTO;

public interface AppStrategy{
    Appanswer doScore(AppAnswerDTO appAnswerDTO) throws Exception;
}
