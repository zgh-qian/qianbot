package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.model.oj.domain.OjJudgecase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseQueryRequest;
import com.qian.qianbotbackend.model.oj.dto.OjJudgecaseUpdateRequest;
import com.qian.qianbotbackend.model.oj.vo.OjJudgecaseVO;

import java.util.List;

/**
 * @author qian
 * @description 针对表【ojjudgecase(题目判题用例表)】的数据库操作Service
 * @createDate 2024-07-27 11:02:57
 */
public interface OjJudgecaseService extends IService<OjJudgecase> {
    void validOjJudgecase(OjJudgecase ojJudgecase, boolean isAdd);

    Boolean addOjJudgecase(OjJudgecaseAddRequest ojJudgecaseAddRequest);

    Boolean deleteOjJudgecase(Long id);

    Boolean deleteOjJudgecaseByQuestionId(Long questionId);

    Boolean updateOjJudgecase(OjJudgecaseUpdateRequest ojJudgecaseUpdateRequest);

    Page<OjJudgecase> getOjJudgecaseList(OjJudgecaseQueryRequest ojJudgecaseQueryRequest);

    Page<OjJudgecaseVO> getOjJudgecaseVOList(OjJudgecaseQueryRequest ojJudgecaseQueryRequest);

    OjJudgecase getOjJudgecaseById(Long id);

    OjJudgecaseVO getOjJudgecaseVOById(Long id);

    List<OjJudgecase> getOjJudgecaseByQuestionId(Long questionId);
}
