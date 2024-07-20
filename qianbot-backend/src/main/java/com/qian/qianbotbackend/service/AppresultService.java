package com.qian.qianbotbackend.service;

import com.qian.qianbotbackend.model.app.domain.Appresult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qian.qianbotbackend.model.app.dto.appresult.AppresultAddRequest;
import com.qian.qianbotbackend.model.app.dto.appresult.AppresultUpdateRequest;
import com.qian.qianbotbackend.model.app.vo.AppresultVO;

import java.util.List;

/**
 * @author qian
 * @description 针对表【appresult(评分结果)】的数据库操作Service
 * @createDate 2024-07-07 15:27:31
 */
public interface AppresultService extends IService<Appresult> {
    void validAppResult(Appresult appresult, boolean isAdd);

    Long addAppResult(AppresultAddRequest appresultAddRequest);

    Boolean deleteAppResult(Long id);

    Boolean deleteAppResultByAppId(Long appId);

    Boolean updateAppResult(AppresultUpdateRequest appresultUpdateRequest);

    AppresultVO getAppResultById(Long id);

    List<AppresultVO> getAppResultByAppId(Long appId);
}
