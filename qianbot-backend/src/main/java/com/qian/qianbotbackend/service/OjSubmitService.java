package com.qian.qianbotbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qian.qianbotbackend.model.oj.domain.OjSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.oj.dto.OjSubmitAddRequest;
import com.qian.qianbotbackend.model.oj.dto.OjSubmitQueryRequest;
import com.qian.qianbotbackend.model.oj.vo.OjSubmitVO;

/**
 * @author qian
 * @description 针对表【ojsubmit(题目提交表)】的数据库操作Service
 * @createDate 2024-07-27 20:19:46
 */
public interface OjSubmitService extends IService<OjSubmit> {

    Long addOjSubmit(OjSubmitAddRequest ojSubmitAddRequest);

    Boolean deleteOjSubmit(Long id);

    Page<OjSubmit> getOjSubmitList(OjSubmitQueryRequest ojSubmitQueryRequest);

    Page<OjSubmitVO> getOjSubmitVOList(OjSubmitQueryRequest ojSubmitQueryRequest);

    OjSubmit getOjSubmit(Long id);

    OjSubmitVO getOjSubmitVO(Long id);
}
