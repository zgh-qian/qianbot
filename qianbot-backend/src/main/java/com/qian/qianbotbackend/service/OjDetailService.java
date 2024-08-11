package com.qian.qianbotbackend.service;

import com.qian.qianbotbackend.model.oj.domain.OjDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.oj.dto.OjQuestionDetailDTO;

/**
 * @author qian
 * @description 针对表【ojdetail(题目详情表)】的数据库操作Service
 * @createDate 2024-07-27 11:02:57
 */
public interface OjDetailService extends IService<OjDetail> {

    OjDetail validOjDetail(OjQuestionDetailDTO ojQuestionDetailDTO, boolean isAdd);

    void updateOjDetailByQuestionId(OjDetail ojDetail);

    void deleteOjDetailByQuestionId(Long questionId);

    OjDetail getQjDetailByQuestionId(Long questionId);
}
