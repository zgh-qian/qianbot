package com.qian.qianbotbackend.service;

import com.qian.qianbotbackend.model.app.domain.Appquestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.app.dto.appquestion.AppquestionAddRequest;
import com.qian.qianbotbackend.model.app.dto.appquestion.AppquestionUpdateRequest;

import java.util.List;

/**
 * @author qian
 * @description 针对表【appquestion(题目)】的数据库操作Service
 * @createDate 2024-07-07 15:27:31
 */
public interface AppquestionService extends IService<Appquestion> {

    void validAppQuestion(Appquestion appquestion, boolean isAdd);

    Long addAppQuestion(AppquestionAddRequest appquestionAddRequest);

    Boolean deleteAppQuestion(Long id);

    Boolean updateAppQuestion(AppquestionUpdateRequest appquestionUpdateRequest);

    Appquestion getAppQuestionById(Long appquestionId);

    List<Appquestion> getAppQuestionList(Long appId);

}
