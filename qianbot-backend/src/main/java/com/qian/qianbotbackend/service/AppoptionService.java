package com.qian.qianbotbackend.service;

import com.qian.qianbotbackend.model.app.domain.Appoption;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.app.dto.appoption.AppoptionAddRequest;
import com.qian.qianbotbackend.model.app.dto.appoption.AppoptionUpdateRequest;

import java.util.List;

/**
 * @author qian
 * @description 针对表【appoption(选项)】的数据库操作Service
 * @createDate 2024-07-07 15:27:31
 */
public interface AppoptionService extends IService<Appoption> {
    void validAppOption(Appoption appoption, boolean isAdd);

    Long addAppOption(AppoptionAddRequest appoptionAddRequest);

    Boolean deleteAppOption(Long id);

    Boolean updateAppOption(AppoptionUpdateRequest appoptionUpdateRequest);

    Appoption getAppOption(Long id);

    List<Appoption> getAppOptionList(Long questionId);
}
