package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.model.oj.domain.OjQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailUpdateRequest;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionQueryRequest;
import com.qian.qianbotbackend.model.oj.vo.OjQuestionDetailVO;
import com.qian.qianbotbackend.model.oj.vo.OjQuestionVO;

/**
 * @author qian
 * @description 针对表【ojquestion(题目表)】的数据库操作Service
 * @createDate 2024-07-27 11:02:57
 */
public interface OjQuestionService extends IService<OjQuestion> {
    OjQuestion validOjQuestion(OjQuestionDetailDTO ojQuestionDetailDTO, boolean isAdd);

    Long addOjQuestionDetail(OjQuestionDetailAddRequest ojQuestionDetailAddRequest);

    Boolean updateOjQuestionDetail(OjQuestionDetailUpdateRequest ojQuestionDetailUpdateRequest);

    Boolean deleteOjQuestionDetail(Long id);

    Page<OjQuestion> getOjQuestionList(OjQuestionQueryRequest ojQuestionQueryRequest);

    Page<OjQuestionVO> getOjQuestionVOList(OjQuestionQueryRequest ojQuestionQueryRequest);

    OjQuestionDetailVO getOjQuestionDetail(Long id);

    OjQuestionDetailDTO getOjQuestionDetailDTO(Long id);
}
