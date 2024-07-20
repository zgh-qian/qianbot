package com.qian.qianbotbackend.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qian.qianbotbackend.async.AppAsyncService;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.DeleteRequest;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.common.ReviewDTO;
import com.qian.qianbotbackend.constant.CommonConstant;
import com.qian.qianbotbackend.enums.app.AppTypeEnum;
import com.qian.qianbotbackend.enums.app.AppReviewStatsEnum;
import com.qian.qianbotbackend.enums.app.AppScoringStrategyEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.manager.AIManager;
import com.qian.qianbotbackend.model.app.domain.Appoption;
import com.qian.qianbotbackend.model.app.domain.Appquestion;
import com.qian.qianbotbackend.model.app.dto.app.*;
import com.qian.qianbotbackend.model.app.vo.*;
import com.qian.qianbotbackend.model.user.domain.User;
import com.qian.qianbotbackend.model.user.vo.UserVO;
import com.qian.qianbotbackend.service.*;
import com.qian.qianbotbackend.model.app.domain.App;
import com.qian.qianbotbackend.mapper.AppMapper;
import com.qian.qianbotbackend.utils.SqlUtils;
import com.zhipu.oapi.service.v4.model.ModelData;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.qian.qianbotbackend.constant.AIConstant.AI_GENERATE_QUESTION_SYSTEM_MESSAGE;
import static com.qian.qianbotbackend.constant.AIConstant.AI_REVIEW_APP_SYSTEM_MESSAGE;
import static com.qian.qianbotbackend.constant.AppConstant.*;

/**
 * @author qian
 * @description 针对表【app(应用)】的数据库操作Service实现
 * @createDate 2024-07-07 15:27:30
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
        implements AppService {
    @Resource
    private UserService userService;

    @Resource
    private AIManager aiManager;

    @Resource
    private AppAsyncService appAsyncService;

    @Lazy
    @Resource
    private AppquestionService appquestionService;

    @Lazy
    @Resource
    private AppoptionService appoptionService;

    @Lazy
    @Resource
    private AppresultService appresultService;

    @Lazy
    @Resource
    private AppanswerService appanswerService;

    @Override
    public void validApp(App app, boolean add) {
        ThrowUtils.throwIf(app == null, ErrorCode.PARAMS_ERROR);
        Long id = app.getId();
        String appName = app.getAppName();
        String appDesc = app.getAppDesc();
        String appIcon = app.getAppIcon();
        Integer appType = app.getAppType();
        Integer scoringStrategy = app.getScoringStrategy();
        Integer reviewStatus = app.getReviewStatus();
        String reviewMessage = app.getReviewMessage();
        Long reviewerId = app.getReviewerId();
        Date reviewTime = app.getReviewTime();
        Long userId = app.getUserId();
        if (add) {
            // 补充校验规则
            ThrowUtils.throwIf(StringUtils.isBlank(appName), ErrorCode.PARAMS_ERROR, APP_NAME_NOT_NULL);
            ThrowUtils.throwIf(StringUtils.isBlank(appDesc), ErrorCode.PARAMS_ERROR, APP_DESC_NOT_NULL);
            ThrowUtils.throwIf(AppTypeEnum.getEnumByValue(appType) == null, ErrorCode.PARAMS_ERROR, APP_TYPE_NOT_TRUE);
            ThrowUtils.throwIf(AppScoringStrategyEnum.getEnumByValue(scoringStrategy) == null, ErrorCode.PARAMS_ERROR, APP_SCORING_NOT_TRUE);
        }
        if (StringUtils.isNotBlank(appName)) {
            ThrowUtils.throwIf(appName.length() > 80, ErrorCode.PARAMS_ERROR, "应用名称过长");
        }
        if (StringUtils.isNotBlank(appDesc)) {
            ThrowUtils.throwIf(appDesc.length() > 3000, ErrorCode.PARAMS_ERROR, "应用描述过长");
        }
    }

    @Override
    public Long addApp(AppAddRequest appAddRequest) {
        ThrowUtils.throwIf(appAddRequest == null, ErrorCode.PARAMS_ERROR);
        App app = new App();
        BeanUtils.copyProperties(appAddRequest, app);
        validApp(app, true);
        app.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_WAITING.getValue());
        app.setUserId(BaseContext.getUserId());
        // 写入数据库
        boolean result = this.save(app);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        // 返回新写入的数据 id
        return app.getId();
    }

    @Override
    @Transactional
    public Boolean deleteApp(DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long appId = deleteRequest.getId();
        // 判断是否存在
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        Long userId = BaseContext.getUserId();
        if (!app.getUserId().equals(userId) && !userService.isAdmin(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        List<Appquestion> appQuestionList = appquestionService.getAppQuestionList(appId);
        if (appQuestionList != null && !appQuestionList.isEmpty()) {
            for (Appquestion appquestion : appQuestionList) {
                List<Appoption> appOptionList = appoptionService.getAppOptionList(appquestion.getId());
                if (appOptionList != null && !appOptionList.isEmpty()) {
                    // 先删除选项
                    appOptionList.forEach(appoption -> {
                        ThrowUtils.throwIf(!appoptionService.removeById(appoption.getId()), ErrorCode.OPERATION_ERROR);
                    });
                }
                // 再删除问题
                ThrowUtils.throwIf(!appquestionService.removeById(appquestion.getId()), ErrorCode.OPERATION_ERROR);
            }
        }
        // 删除应用结果
        appresultService.deleteAppResultByAppId(appId);
        // 删除应用回答
        appanswerService.deleteAppAnswerByAppId(appId);
        // 最后删除应用
        ThrowUtils.throwIf(!this.removeById(appId), ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean editApp(AppEditRequest appEditRequest) {
        if (appEditRequest == null || appEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        App app = new App();
        BeanUtils.copyProperties(appEditRequest, app);
        // 数据校验
        this.validApp(app, false);
        // 判断是否存在
        long id = appEditRequest.getId();
        App oldApp = this.getById(id);
        ThrowUtils.throwIf(oldApp == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人可编辑
        if (!oldApp.getUserId().equals(BaseContext.getUserId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        app.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_WAITING.getValue());
        // 操作数据库
        boolean result = this.updateById(app);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public AppVO getAppVO(App app) {
        // 对象转封装类
        AppVO appVO = AppVO.objToVo(app);
        // 关联查询用户信息
        Long userId = app.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        appVO.setUserVO(userVO);
        return appVO;
    }

    @Override
    public AppVO getAppVOById(Long id) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        App app = this.getById(id);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        // 获取封装类
        return this.getAppVO(app);
    }

    @Override
    public Wrapper<App> getQueryWrapper(AppQueryRequest appQueryRequest) {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        if (appQueryRequest == null) {
            return queryWrapper;
        }
        // 从对象中取值
        Long id = appQueryRequest.getId();
        String appName = appQueryRequest.getAppName();
        String appDesc = appQueryRequest.getAppDesc();
        Integer appType = appQueryRequest.getAppType();
        Integer scoringStrategy = appQueryRequest.getScoringStrategy();
        Integer reviewStatus = appQueryRequest.getReviewStatus();
        Long userId = appQueryRequest.getUserId();
        String searchText = appQueryRequest.getSearchText();
        String sortField = appQueryRequest.getSortField();
        String sortOrder = appQueryRequest.getSortOrder();
        // 从多字段中搜索
        if (StringUtils.isNotBlank(searchText)) {
            // 需要拼接查询条件
            queryWrapper.and(qw -> qw.like("appName", searchText).or().like("appDesc", searchText));
        }
        // 模糊查询
        queryWrapper.like(StringUtils.isNotBlank(appName), "appName", appName);
        queryWrapper.like(StringUtils.isNotBlank(appDesc), "appDesc", appDesc);
        // 精确查询
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(appType), "appType", appType);
        queryWrapper.eq(ObjectUtils.isNotEmpty(scoringStrategy), "scoringStrategy", scoringStrategy);
        queryWrapper.eq(ObjectUtils.isNotEmpty(reviewStatus), "reviewStatus", reviewStatus);
        // 排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        queryWrapper.orderByDesc("updateTime");
        return queryWrapper;
    }

    @Override
    public Page<App> listAppByPage(AppQueryRequest appQueryRequest) {
        long current = appQueryRequest.getCurrent();
        long size = appQueryRequest.getPageSize();
        // 查询数据库
        return this.page(new Page<>(current, size), this.getQueryWrapper(appQueryRequest));
    }

    @Override
    public AppDetailVO getAppDetail(Long appId) {
        AppDetailVO appDetailVO = new AppDetailVO();
        appDetailVO.setAppVO(AppVO.objToVo(this.getById(appId)));
        appDetailVO.setAppresultVO(AppresultVO.objToVO(appresultService.getById(appId)));
        appDetailVO.setQuestionAndOptionVOList(getAppQuestionAndOption(appId));
        return appDetailVO;
    }

    @Override
    public Page<AppVO> listAppVOByPage(AppQueryRequest appQueryRequest) {
        ThrowUtils.throwIf(appQueryRequest == null, ErrorCode.PARAMS_ERROR);
        // 只能搜索审核通过的应用
        appQueryRequest.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_PASS.getValue());
        Page<App> appPage = listAppByPage(appQueryRequest);
        Page<AppVO> appVOPage = new Page<>();
        BeanUtils.copyProperties(appPage, appVOPage);
        List<App> appList = appPage.getRecords();
        if (appList.isEmpty()) {
            appVOPage.setRecords(new ArrayList<>());
            return appVOPage;
        }
        // 对象列表 => 封装对象列表
        List<AppVO> appVOList = appList.stream().map(AppVO::objToVo).collect(Collectors.toList());
        // 关联查询用户信息
        Set<Long> userIdSet = appList.stream().map(App::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));
        // 填充信息
        appVOList.forEach(appVO -> {
            Long userId = appVO.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            appVO.setUserVO(userService.getUserVO(user));
        });
        appVOPage.setRecords(appVOList);
        return appVOPage;
    }

    @Override
    public Page<AppVO> listMyAppVOByPage(AppQueryRequest appQueryRequest) {
        ThrowUtils.throwIf(appQueryRequest == null, ErrorCode.PARAMS_ERROR);
        // 只能搜索自己创建的应用
        appQueryRequest.setUserId(BaseContext.getUserId());
        Page<App> appPage = listAppByPage(appQueryRequest);
        Page<AppVO> appVOPage = new Page<>();
        BeanUtils.copyProperties(appPage, appVOPage);
        List<App> appList = appPage.getRecords();
        if (appList.isEmpty()) {
            appVOPage.setRecords(new ArrayList<>());
            return appVOPage;
        }
        // 对象列表 => 封装对象列表
        List<AppVO> appVOList = appList.stream().map(AppVO::objToVo).collect(Collectors.toList());
        appVOPage.setRecords(appVOList);
        return appVOPage;
    }

    @Override
    public Boolean updateApp(AppUpdateRequest appUpdateRequest) {
        if (appUpdateRequest == null || appUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        App app = new App();
        BeanUtils.copyProperties(appUpdateRequest, app);
        // 数据校验
        this.validApp(app, false);
        // 判断是否存在
        long id = appUpdateRequest.getId();
        App oldApp = this.getById(id);
        ThrowUtils.throwIf(oldApp == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅管理员可编辑
        if (!userService.isAdmin(BaseContext.getUserId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = this.updateById(app);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public Boolean doAppReview(ReviewDTO reviewDTO) {
        ThrowUtils.throwIf(reviewDTO == null, ErrorCode.PARAMS_ERROR);
        Long id = reviewDTO.getId();
        Integer reviewStatus = reviewDTO.getReviewStatus();
        // 校验
        AppReviewStatsEnum appReviewStatsEnum = AppReviewStatsEnum.getEnumByValue(reviewStatus);
        if (id == null || appReviewStatsEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        App app = this.getById(id);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        checkAppReviewStatus(app.getReviewStatus());
        // 判断是否存在
        App oldApp = this.getById(id);
        ThrowUtils.throwIf(oldApp == null, ErrorCode.NOT_FOUND_ERROR);
        // 更新审核状态
        app.setReviewStatus(reviewStatus);
        app.setReviewMessage(reviewDTO.getReviewMessage());
        app.setReviewerId(BaseContext.getUserId());
        app.setReviewTime(new Date());
        boolean result = this.updateById(app);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    @Transactional
    public Boolean addAndUpdateAppQuestionAndOption(AppQuestionOptionUpdateRequest appQuestionOptionUpdateRequest) {
        Long appId = appQuestionOptionUpdateRequest.getAppId();
        List<AppQuestionOptionUpdateRequest.AppQuestionOptionUpdateDTO> appQuestionAndOptionList = appQuestionOptionUpdateRequest.getAppQuestionAndOptionList();
        // 校验app是否存在
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        Long userId = BaseContext.getUserId();
        boolean save;
        // 获取原本题目id列表
        List<Long> oldQuestionIdList = appquestionService.list(
                new QueryWrapper<Appquestion>().eq("appId", appId)
        ).stream().map(Appquestion::getId).collect(Collectors.toList());
        for (AppQuestionOptionUpdateRequest.AppQuestionOptionUpdateDTO appQuestionOptionUpdateDTO : appQuestionAndOptionList) {
            AppQuestionOptionUpdateRequest.AppQuestionOptionUpdateDTO.AppQuestionUpdateDTO question = appQuestionOptionUpdateDTO.getQuestion();
            Long questionId = question.getId();
            String questionPic = question.getQuestionPic();
            String questionName = question.getQuestionName();
            List<AppQuestionOptionUpdateRequest.AppQuestionOptionUpdateDTO.AppOptionUpdateDTO> optionList = appQuestionOptionUpdateDTO.getOptionList();
            // 判断题目id是否存在，不存在则新建，存在则修改
            if (questionId == null) {
                // 校验题目
                Appquestion appquestion = new Appquestion();
                appquestion.setQuestionPic(questionPic);
                appquestion.setQuestionName(questionName);
                appquestion.setAppId(appId);
                appquestion.setUserId(userId);
                appquestionService.validAppQuestion(appquestion, true);
                // 插入问题
                save = appquestionService.save(appquestion);
                ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
                for (AppQuestionOptionUpdateRequest.AppQuestionOptionUpdateDTO.AppOptionUpdateDTO appOptionDTO : optionList) {
                    String optionPic = appOptionDTO.getOptionPic();
                    String optionKey = appOptionDTO.getOptionKey();
                    String optionName = appOptionDTO.getOptionName();
                    String optionResult = appOptionDTO.getOptionResult();
                    // 校验选项
                    Appoption appoption = new Appoption();
                    appoption.setOptionPic(optionPic);
                    appoption.setOptionKey(optionKey);
                    appoption.setOptionName(optionName);
                    appoption.setOptionResult(optionResult);
                    appoption.setQuestionId(appquestion.getId());
                    appoption.setUserId(userId);
                    appoptionService.validAppOption(appoption, true);
                    // 插入选项
                    save = appoptionService.save(appoption);
                    ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
                }
            } else {
                // 校验问题
                Appquestion appquestion = appquestionService.getById(questionId);
                ThrowUtils.throwIf(appquestion == null, ErrorCode.NOT_FOUND_ERROR);
                if (StringUtils.isNotBlank(questionPic)) {
                    appquestion.setQuestionPic(questionPic);
                }
                if (StringUtils.isNotBlank(questionName)) {
                    appquestion.setQuestionName(questionName);
                }
                appquestionService.validAppQuestion(appquestion, false);
                // 更新问题
                save = appquestionService.updateById(appquestion);
                ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
                if (optionList.isEmpty()) {
                    continue;
                }
                for (AppQuestionOptionUpdateRequest.AppQuestionOptionUpdateDTO.AppOptionUpdateDTO appOption : optionList) {
                    Long optionId = appOption.getId();
                    String optionPic = appOption.getOptionPic();
                    String optionKey = appOption.getOptionKey();
                    String optionName = appOption.getOptionName();
                    String optionResult = appOption.getOptionResult();
                    // 校验选项
                    Appoption appoption = appoptionService.getById(optionId);
                    ThrowUtils.throwIf(appoption == null, ErrorCode.NOT_FOUND_ERROR);
                    if (StringUtils.isNotBlank(optionName)) {
                        appoption.setOptionName(optionName);
                    }
                    if (StringUtils.isNotBlank(optionKey)) {
                        appoption.setOptionPic(optionKey);
                    }
                    if (StringUtils.isNotBlank(optionPic)) {
                        appoption.setOptionPic(optionPic);
                    }
                    if (StringUtils.isNotBlank(optionResult)) {
                        appoption.setOptionPic(optionResult);
                    }
                    appoptionService.validAppOption(appoption, false);
                    // 更新选项
                    save = appoptionService.updateById(appoption);
                    ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
                }
                // 移除问题id
                oldQuestionIdList.remove(questionId);
            }
        }
        // 删除还存在的问题id列表
        oldQuestionIdList.forEach(quesitonId -> {
            // 先删除选项
            appoptionService.lambdaUpdate().eq(Appoption::getQuestionId, quesitonId).remove();
        });
        // 再删除问题
        appquestionService.removeByIds(oldQuestionIdList);
        // 修改应用审核状态
        save = this.resetAppReviewStatus(appId);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return true;
    }

    @Override
    public List<AppQuestionAndOptionVO> getAppQuestionAndOption(Long appId) {
        // 校验app是否存在
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        // 问题和选项列表
        List<AppQuestionAndOptionVO> appQuestionAndOptionVOList = new ArrayList<>();
        // 查询问题列表
        List<Appquestion> appquestionList = appquestionService.lambdaQuery().eq(Appquestion::getAppId, appId).list();
        if (appquestionList.isEmpty()) {
            return appQuestionAndOptionVOList;
        }
        for (Appquestion appquestion : appquestionList) {
            // 查询选项列表
            List<Appoption> appoptionList = appoptionService.lambdaQuery().eq(Appoption::getQuestionId, appquestion.getId()).list();
            if (appoptionList.isEmpty()) {
                continue;
            }
            List<AppoptionVO> appoptionVOList = appoptionList.stream().map(AppoptionVO::objToVO).collect(Collectors.toList());
            appQuestionAndOptionVOList.add(new AppQuestionAndOptionVO(AppquestionVO.objToVO(appquestion), appoptionVOList));
        }
        return appQuestionAndOptionVOList;
    }

    @Override
    public List<AppAIGenerateVO> getAIGenerateQuestionSync(AppAIGenerateRequest appAIGenerateRequest) {
        ThrowUtils.throwIf(appAIGenerateRequest == null, ErrorCode.PARAMS_ERROR);
        Long appId = appAIGenerateRequest.getAppId();
        Integer questionNumber = appAIGenerateRequest.getQuestionNumber();
        Integer optionNumber = appAIGenerateRequest.getOptionNumber();
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        // 封装 Prompt
        String userMessage = getGenerateQuestionAndOptionUserMessage(app, questionNumber, optionNumber);
        // AI 生成
        String result = aiManager.doSyncStableRequest(AI_GENERATE_QUESTION_SYSTEM_MESSAGE, userMessage);
        int start = result.indexOf("[");
        int end = result.lastIndexOf("]");
        int mistakeChar = result.indexOf("__");
        if (mistakeChar != -1) {
            result = result.replace("__optionList", "\"optionList\"");
        }
        String jsonStr = result.substring(start, end + 1);
        List<AppAIGenerateVO> list = JSONUtil.toList(jsonStr, AppAIGenerateVO.class);
        return list;
    }

    @Override
    public SseEmitter getAIGenerateQuestionBySSE(AppAIGenerateRequest appAIGenerateRequest) {
        ThrowUtils.throwIf(appAIGenerateRequest == null, ErrorCode.PARAMS_ERROR);
        Long appId = appAIGenerateRequest.getAppId();
        Integer questionNumber = appAIGenerateRequest.getQuestionNumber();
        Integer optionNumber = appAIGenerateRequest.getOptionNumber();
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        // 封装 Prompt
        String userMessage = getGenerateQuestionAndOptionUserMessage(app, questionNumber, optionNumber);
        // 建立 SSE 连接，0 表示不设置超时时间
        SseEmitter sseEmitter = new SseEmitter(0L);
        // 左括号计数器
        AtomicInteger leftCount = new AtomicInteger(0);
        StringBuilder data = new StringBuilder();
        Flowable<ModelData> modelDataFlowable = aiManager.doStableStreamRequest(AI_GENERATE_QUESTION_SYSTEM_MESSAGE, userMessage);
        modelDataFlowable
                .observeOn(Schedulers.io())
                .map(chunk -> chunk.getChoices().get(0).getDelta().getContent())
                .map(message -> message.replaceAll("\\s", ""))
                .filter(StrUtil::isNotBlank)
                .flatMap(message -> {
                    return Flowable.fromIterable(message.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
                })
                .doOnNext(c -> {
                    data.append(c);
                    if (c == '{') {
                        leftCount.addAndGet(1);
                    }
                    if (c == '}') {
                        leftCount.addAndGet(-1);
                        if (leftCount.get() == 0) {
                            int beginIndex = data.indexOf("{");
                            int endIndex = data.lastIndexOf("}");
                            String json = data.substring(beginIndex, endIndex + 1);
                            int mistakeChar = data.indexOf("__");
                            if (mistakeChar != -1) {
                                json = json.replace("__optionList", "\"optionList\"");
                            }
                            sseEmitter.send(JSONUtil.toJsonStr(json));
                            data.setLength(0);
                        }
                    }
                })
                .doOnError((e) -> log.error("sse error", e))
                .doOnComplete(sseEmitter::complete)
                .subscribe();
        return sseEmitter;
    }

    @Override
    public Boolean doAIAppReview(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        checkAppReviewStatus(app.getReviewStatus());
        app.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_RUNNING.getValue());
        boolean update = this.updateById(app);
        ThrowUtils.throwIf(!update, ErrorCode.SYSTEM_ERROR);
        appAsyncService.doAIAppReviewAsync(app);
        return true;
    }

    @Override
    public Boolean resetAppReviewStatus(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        App app = this.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        app.setReviewStatus(AppReviewStatsEnum.REVIEW_STATS_ENUM_WAITING.getValue());
        return this.updateById(app);
    }

    private void checkAppReviewStatus(Integer reviewStatus) {
        if (reviewStatus.equals(AppReviewStatsEnum.REVIEW_STATS_ENUM_RUNNING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, APP_REVIEW_RUNNING);
        } else if (reviewStatus.equals(AppReviewStatsEnum.REVIEW_STATS_ENUM_PASS.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, APP_REVIEW_PASS);
        } else if (reviewStatus.equals(AppReviewStatsEnum.REVIEW_STATS_ENUM_REJECT.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, APP_REVIEW_REJECT);
        }
    }

    private String getGenerateQuestionAndOptionUserMessage(App app, int questionNumber, int optionNumber) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append(app.getAppName()).append("\n");
        userMessage.append(app.getAppDesc()).append("\n");
        userMessage.append(AppTypeEnum.getEnumByValue(app.getAppType()).getText()).append("\n");
        userMessage.append(questionNumber).append("\n");
        userMessage.append(optionNumber);
        return userMessage.toString();
    }
}




