package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.model.app.domain.Appanswer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppanswerAddRequest;
import com.qian.qianbotbackend.model.app.dto.appanswer.AppanswerQueryRequest;
import com.qian.qianbotbackend.model.app.vo.AppAnswerCountVO;
import com.qian.qianbotbackend.model.app.vo.AppAnswerResultNameCountVO;
import com.qian.qianbotbackend.model.app.vo.AppanswerVO;

import java.util.List;

/**
 * @author qian
 * @description 针对表【appanswer(用户答题记录)】的数据库操作Service
 * @createDate 2024-07-07 15:27:31
 */
public interface AppanswerService extends IService<Appanswer> {

    Long addAppAnswer(AppanswerAddRequest appanswerAddRequest);

    Boolean deleteAppAnswer(Long id);

    Boolean deleteAppAnswerByAppId(Long appId);

    Appanswer getAppAnswerById(Long id);

    AppanswerVO getAppAnswerVOById(Long id);

    Page<Appanswer> getAppAnswerPage(AppanswerQueryRequest appanswerQueryRequest);

    Page<AppanswerVO> getAppAnswerPageVO(AppanswerQueryRequest appanswerQueryRequest);

    List<AppAnswerCountVO> getAppAnswerCount();

    List<AppAnswerResultNameCountVO> getAppResultCount(Long appId);
}
