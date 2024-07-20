package com.qian.qianbotbackend.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.model.app.domain.Appresult;
import com.qian.qianbotbackend.model.app.dto.appresult.AppresultAddRequest;
import com.qian.qianbotbackend.model.app.dto.appresult.AppresultUpdateRequest;
import com.qian.qianbotbackend.model.app.vo.AppresultVO;
import com.qian.qianbotbackend.service.AppService;
import com.qian.qianbotbackend.service.AppresultService;
import com.qian.qianbotbackend.mapper.AppresultMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qian
 * @description 针对表【appresult(评分结果)】的数据库操作Service实现
 * @createDate 2024-07-07 15:27:31
 */
@Service
public class AppresultServiceImpl extends ServiceImpl<AppresultMapper, Appresult>
        implements AppresultService {
    @Resource
    private AppService appService;

    @Override
    public void validAppResult(Appresult appresult, boolean isAdd) {
        ThrowUtils.throwIf(appresult == null, ErrorCode.PARAMS_ERROR);
        Long id = appresult.getId();
        String resultName = appresult.getResultName();
        String resultPic = appresult.getResultPic();
        String resultDesc = appresult.getResultDesc();
        String resultProp = appresult.getResultProp();
        Integer resultScore = appresult.getResultScore();
        if (isAdd) {
            if (resultProp == null && resultScore == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "评分和属性不能同时为空");
            }
        } else {
            ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(resultName)) {
            ThrowUtils.throwIf(resultName.length() > 80, ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(resultDesc)) {
            ThrowUtils.throwIf(resultDesc.length() > 1000, ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public Long addAppResult(AppresultAddRequest appresultAddRequest) {
        ThrowUtils.throwIf(appresultAddRequest == null, ErrorCode.PARAMS_ERROR);
        // 查询app是否存在
        Long appId = appresultAddRequest.getAppId();
        App app = appService.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.PARAMS_ERROR);
        Appresult appresult = new Appresult();
        BeanUtils.copyProperties(appresultAddRequest, appresult);
        List<String> resultProp = appresultAddRequest.getResultProp();
        if (resultProp != null) {
            appresult.setResultProp(JSONUtil.toJsonStr(resultProp));
        }
        validAppResult(appresult, true);
        appresult.setUserId(BaseContext.getUserId());
        boolean save = this.save(appresult);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        appService.resetAppReviewStatus(appId);
        return appresult.getId();
    }

    @Override
    public Boolean deleteAppResult(Long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Appresult appresult = this.getById(id);
        ThrowUtils.throwIf(appresult == null, ErrorCode.PARAMS_ERROR);
        boolean remove = this.removeById(id);
        ThrowUtils.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean deleteAppResultByAppId(Long appId) {
        ThrowUtils.throwIf(appId <= 0, ErrorCode.PARAMS_ERROR);
        boolean remove = this.remove(new QueryWrapper<Appresult>().eq("appId", appId));
        ThrowUtils.throwIf(!remove, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean updateAppResult(AppresultUpdateRequest appresultUpdateRequest) {
        ThrowUtils.throwIf(appresultUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Appresult appresult = new Appresult();
        BeanUtils.copyProperties(appresultUpdateRequest, appresult);
        List<String> resultProp = appresultUpdateRequest.getResultProp();
        if (resultProp != null) {
            appresult.setResultProp(JSONUtil.toJsonStr(resultProp));
        }
        validAppResult(appresult, false);
        boolean save = this.updateById(appresult);
        appresult = this.getById(appresult);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        appService.resetAppReviewStatus(appresult.getAppId());
        return true;
    }

    @Override
    public AppresultVO getAppResultById(Long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Appresult appresult = this.getById(id);
        ThrowUtils.throwIf(appresult == null, ErrorCode.NOT_FOUND_ERROR);
        return AppresultVO.objToVO(appresult);
    }

    @Override
    public List<AppresultVO> getAppResultByAppId(Long appId) {
        return this.lambdaQuery().eq(Appresult::getAppId, appId).eq(Appresult::getUserId, BaseContext.getUserId()).list().stream().map(AppresultVO::objToVO).collect(Collectors.toList());
    }
}




