declare namespace API {
  type addChartByFileUsingPOSTParams = {
    chartData?: string;
    chartGoal?: string;
    chartName?: string;
    chartType?: string;
  };

  type App = {
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    appType?: number;
    createTime?: string;
    id?: number;
    isDelete?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    scoringStrategy?: number;
    updateTime?: string;
    userId?: number;
  };

  type AppAddRequest = {
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    appType?: number;
    scoringStrategy?: number;
  };

  type AppAIGenerateRequest = {
    appId?: number;
    optionNumber?: number;
    questionNumber?: number;
  };

  type AppAIGenerateVO = {
    optionList?: AppOptionDTO[];
    questionName?: string;
  };

  type AppanswerAddRequest = {
    appId?: number;
    id?: number;
    userAnswer?: number[];
  };

  type AppAnswerCountVO = {
    answerCount?: number;
    appDesc?: string;
    appIcon?: string;
    appId?: number;
    appName?: string;
  };

  type AppanswerQueryRequest = {
    appId?: number;
    current?: number;
    id?: number;
    pageSize?: number;
    resultDesc?: string;
    resultName?: string;
    resultScore?: number;
    resultStatus?: number;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type AppAnswerResultNameCountVO = {
    answerCount?: number;
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    id?: number;
  };

  type AppanswerVO = {
    appId?: number;
    appName?: string;
    createTime?: string;
    id?: number;
    resultDesc?: string;
    resultId?: number;
    resultName?: string;
    resultPic?: string;
    resultScore?: number;
    resultStatus?: number;
    updateTime?: string;
    userAnswer?: number[];
    userId?: number;
  };

  type AppDetailVO = {
    appVO?: AppVO;
    appresultVO?: AppresultVO;
    questionAndOptionVOList?: AppQuestionAndOptionVO[];
  };

  type AppEditRequest = {
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    appType?: number;
    id?: number;
    scoringStrategy?: number;
  };

  type Appoption = {
    createTime?: string;
    id?: number;
    isDelete?: number;
    optionKey?: string;
    optionName?: string;
    optionPic?: string;
    optionResult?: string;
    questionId?: number;
    updateTime?: string;
    userId?: number;
  };

  type AppoptionAddRequest = {
    optionKey?: string;
    optionName?: string;
    optionPic?: string;
    optionResult?: string;
    questionId?: number;
  };

  type AppOptionDTO = {
    optionKey?: string;
    optionName?: string;
    optionResult?: string;
  };

  type AppOptionUpdateDTO = {
    id?: number;
    optionKey?: string;
    optionName?: string;
    optionPic?: string;
    optionResult?: string;
  };

  type AppoptionUpdateRequest = {
    id?: number;
    optionKey?: string;
    optionName?: string;
    optionPic?: string;
    optionResult?: string;
  };

  type AppoptionVO = {
    id?: number;
    optionKey?: string;
    optionName?: string;
    optionPic?: string;
    optionResult?: string;
  };

  type AppQueryRequest = {
    appDesc?: string;
    appName?: string;
    appType?: number;
    current?: number;
    id?: number;
    pageSize?: number;
    reviewStatus?: number;
    scoringStrategy?: number;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type Appquestion = {
    appId?: number;
    createTime?: string;
    id?: number;
    isDelete?: number;
    questionName?: string;
    questionPic?: string;
    updateTime?: string;
    userId?: number;
  };

  type AppquestionAddRequest = {
    appId?: number;
    questionName?: string;
    questionPic?: string;
  };

  type AppQuestionAndOptionVO = {
    optionList?: AppoptionVO[];
    question?: AppquestionVO;
  };

  type AppQuestionOptionUpdateDTO = {
    optionList?: AppOptionUpdateDTO[];
    question?: AppQuestionUpdateDTO;
  };

  type AppQuestionOptionUpdateRequest = {
    appId?: number;
    appQuestionAndOptionList?: AppQuestionOptionUpdateDTO[];
  };

  type AppQuestionUpdateDTO = {
    id?: number;
    questionName?: string;
    questionPic?: string;
  };

  type AppquestionUpdateRequest = {
    id?: number;
    questionName?: string;
    questionPic?: string;
  };

  type AppquestionVO = {
    id?: number;
    questionName?: string;
    questionPic?: string;
  };

  type AppresultAddRequest = {
    appId?: number;
    resultDesc?: string;
    resultName?: string;
    resultPic?: string;
    resultProp?: string[];
    resultScore?: number;
  };

  type AppresultUpdateRequest = {
    id?: number;
    resultDesc?: string;
    resultName?: string;
    resultPic?: string;
    resultProp?: string[];
    resultScore?: number;
  };

  type AppresultVO = {
    appId?: number;
    createTime?: string;
    id?: number;
    isDelete?: number;
    resultDesc?: string;
    resultName?: string;
    resultPic?: string;
    resultProp?: string[];
    resultScore?: number;
    updateTime?: string;
    userId?: number;
  };

  type AppUpdateRequest = {
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    appType?: number;
    id?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    scoringStrategy?: number;
  };

  type AppVO = {
    appDesc?: string;
    appIcon?: string;
    appName?: string;
    appType?: number;
    createTime?: string;
    id?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    scoringStrategy?: number;
    updateTime?: string;
    userId?: number;
    userVO?: UserVO;
  };

  type BaseResponseAppanswerVO_ = {
    code?: number;
    data?: AppanswerVO;
    message?: string;
  };

  type BaseResponseAppDetailVO_ = {
    code?: number;
    data?: AppDetailVO;
    message?: string;
  };

  type BaseResponseAppoption_ = {
    code?: number;
    data?: Appoption;
    message?: string;
  };

  type BaseResponseAppquestion_ = {
    code?: number;
    data?: Appquestion;
    message?: string;
  };

  type BaseResponseAppresultVO_ = {
    code?: number;
    data?: AppresultVO;
    message?: string;
  };

  type BaseResponseAppVO_ = {
    code?: number;
    data?: AppVO;
    message?: string;
  };

  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseChartVO_ = {
    code?: number;
    data?: ChartVO;
    message?: string;
  };

  type BaseResponseCodeExecuteResponse_ = {
    code?: number;
    data?: CodeExecuteResponse;
    message?: string;
  };

  type BaseResponseInt_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponseListAppAIGenerateVO_ = {
    code?: number;
    data?: AppAIGenerateVO[];
    message?: string;
  };

  type BaseResponseListAppAnswerCountVO_ = {
    code?: number;
    data?: AppAnswerCountVO[];
    message?: string;
  };

  type BaseResponseListAppAnswerResultNameCountVO_ = {
    code?: number;
    data?: AppAnswerResultNameCountVO[];
    message?: string;
  };

  type BaseResponseListAppQuestionAndOptionVO_ = {
    code?: number;
    data?: AppQuestionAndOptionVO[];
    message?: string;
  };

  type BaseResponseListAppresultVO_ = {
    code?: number;
    data?: AppresultVO[];
    message?: string;
  };

  type BaseResponseListCodeSandboxLanguage_ = {
    code?: number;
    data?: CodeSandboxLanguage[];
    message?: string;
  };

  type BaseResponseLoginUserVO_ = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponseOjJudgecaseVO_ = {
    code?: number;
    data?: OjJudgecaseVO;
    message?: string;
  };

  type BaseResponseOjQuestionDetailVO_ = {
    code?: number;
    data?: OjQuestionDetailVO;
    message?: string;
  };

  type BaseResponseOjSubmitVO_ = {
    code?: number;
    data?: OjSubmitVO;
    message?: string;
  };

  type BaseResponsePageApp_ = {
    code?: number;
    data?: PageApp_;
    message?: string;
  };

  type BaseResponsePageAppanswerVO_ = {
    code?: number;
    data?: PageAppanswerVO_;
    message?: string;
  };

  type BaseResponsePageAppVO_ = {
    code?: number;
    data?: PageAppVO_;
    message?: string;
  };

  type BaseResponsePageChartVO_ = {
    code?: number;
    data?: PageChartVO_;
    message?: string;
  };

  type BaseResponsePageOjJudgecaseVO_ = {
    code?: number;
    data?: PageOjJudgecaseVO_;
    message?: string;
  };

  type BaseResponsePageOjQuestionVO_ = {
    code?: number;
    data?: PageOjQuestionVO_;
    message?: string;
  };

  type BaseResponsePageOjSubmitVO_ = {
    code?: number;
    data?: PageOjSubmitVO_;
    message?: string;
  };

  type BaseResponsePageUser_ = {
    code?: number;
    data?: PageUser_;
    message?: string;
  };

  type BaseResponsePageUserCodeSelfVO_ = {
    code?: number;
    data?: PageUserCodeSelfVO_;
    message?: string;
  };

  type BaseResponsePageUserCodeVO_ = {
    code?: number;
    data?: PageUserCodeVO_;
    message?: string;
  };

  type BaseResponsePageUserVO_ = {
    code?: number;
    data?: PageUserVO_;
    message?: string;
  };

  type BaseResponseString_ = {
    code?: number;
    data?: string;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BaseResponseUserCodeSelfVO_ = {
    code?: number;
    data?: UserCodeSelfVO;
    message?: string;
  };

  type BaseResponseUserCodeVO_ = {
    code?: number;
    data?: UserCodeVO;
    message?: string;
  };

  type BaseResponseUserVO_ = {
    code?: number;
    data?: UserVO;
    message?: string;
  };

  type ChartAddRequest = {
    chartData?: string;
    chartGoal?: string;
    chartName?: string;
    chartType?: string;
  };

  type ChartQueryRequest = {
    chartGoal?: string;
    chartName?: string;
    chartStatus?: number;
    chartType?: string;
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type ChartUpdateRequest = {
    chartData?: string;
    chartGoal?: string;
    chartName?: string;
    chartType?: string;
    id?: number;
  };

  type ChartVO = {
    chartData?: string;
    chartGoal?: string;
    chartName?: string;
    chartStatus?: number;
    chartType?: string;
    createTime?: string;
    genData?: string;
    genResult?: string;
    id?: number;
    updateTime?: string;
    userId?: number;
  };

  type CodeExecuteRequest = {
    code?: string;
    language?: string;
  };

  type CodeExecuteResponse = {
    exitCode?: number;
    message?: string;
  };

  type CodeSandboxLanguage = {
    templateCode?: string;
    text?: string;
    value?: string;
  };

  type DeleteRequest = {
    id?: number;
  };

  type doAIAppReviewUsingGETParams = {
    /** appId */
    appId?: number;
  };

  type genChartUsingGETParams = {
    /** id */
    id?: number;
  };

  type getAIGenerateQuestionSSEUsingGETParams = {
    appId?: number;
    optionNumber?: number;
    questionNumber?: number;
  };

  type getAppAnswerVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getAppDetailUsingGETParams = {
    /** appId */
    appId?: number;
  };

  type getAppOptionUsingPOSTParams = {
    /** appoptionId */
    appoptionId?: number;
  };

  type getAppQuestionAndOptionUsingGETParams = {
    /** appId */
    appId?: number;
  };

  type getAppQuestionByIdUsingGETParams = {
    /** appquestionId */
    appquestionId?: number;
  };

  type getAppResultByAppIdUsingGETParams = {
    /** appId */
    appId?: number;
  };

  type getAppResultByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getAppResultCountUsingGETParams = {
    /** appId */
    appId?: number;
  };

  type getAppVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getChartVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getOjJudgecaseByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getOjQuestionDetailUsingGETParams = {
    /** id */
    id?: number;
  };

  type getOjSubmitVOUsingGETParams = {
    /** id */
    id?: number;
  };

  type getPasswordCodeUsingGETParams = {
    /** email */
    email?: string;
  };

  type getRegisterCodeUsingGETParams = {
    /** email */
    email?: string;
  };

  type getUserByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserCodeSelfVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserCodeVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type LoginUserVO = {
    createTime?: string;
    id?: number;
    updateTime?: string;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userBirthday?: string;
    userEmail?: string;
    userGender?: number;
    userName?: string;
    userPhone?: string;
    userProfile?: string;
    userRole?: string;
  };

  type OjJudgecaseAddRequest = {
    input?: string;
    output?: string;
    questionId?: number;
  };

  type OjJudgecaseQueryRequest = {
    current?: number;
    pageSize?: number;
    questionId?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type OjJudgecaseUpdateRequest = {
    id?: number;
    input?: string;
    output?: string;
  };

  type OjJudgecaseVO = {
    createTime?: string;
    id?: number;
    input?: string;
    output?: string;
    questionId?: number;
    updateTime?: string;
    userId?: number;
  };

  type OjQuestionDetailAddRequest = {
    answer?: string[];
    content?: string;
    difficulty?: number;
    memoryLimit?: number;
    tags?: string[];
    template?: string[];
    timeLimit?: number;
    tips?: string[];
    title?: string;
  };

  type OjQuestionDetailUpdateRequest = {
    answer?: string[];
    content?: string;
    difficulty?: number;
    id?: number;
    memoryLimit?: number;
    tags?: string[];
    template?: string[];
    timeLimit?: number;
    tips?: string[];
    title?: string;
  };

  type OjQuestionDetailVO = {
    content?: string;
    createTime?: string;
    difficulty?: number;
    id?: number;
    memoryLimit?: number;
    tags?: string[];
    template?: string[];
    timeLimit?: number;
    tips?: string[];
    title?: string;
    updateTime?: string;
    userId?: number;
  };

  type OjQuestionQueryRequest = {
    current?: number;
    difficulty?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    tags?: string[];
    title?: string;
  };

  type OjQuestionVO = {
    createTime?: string;
    difficulty?: number;
    id?: number;
    tags?: string[];
    title?: string;
    updateTime?: string;
    userId?: number;
  };

  type OjSubmitAddRequest = {
    code?: string;
    language?: string;
    questionId?: number;
  };

  type OjSubmitQueryRequest = {
    current?: number;
    detail?: string;
    id?: number;
    judgeStatus?: number;
    language?: string;
    memoryUsed?: number;
    pageSize?: number;
    questionId?: number;
    sortField?: string;
    sortOrder?: string;
    status?: string;
    timeUsed?: number;
    userId?: number;
  };

  type OjSubmitVO = {
    code?: string;
    createTime?: string;
    detail?: string;
    id?: number;
    judgeStatus?: number;
    language?: string;
    memoryUsed?: number;
    questionId?: number;
    status?: string;
    timeUsed?: number;
    updateTime?: string;
    userId?: number;
  };

  type OrderItem = {
    asc?: boolean;
    column?: string;
  };

  type PageApp_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: App[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageAppanswerVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: AppanswerVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageAppVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: AppVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageChartVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: ChartVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageOjJudgecaseVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: OjJudgecaseVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageOjQuestionVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: OjQuestionVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageOjSubmitVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: OjSubmitVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUser_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: User[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserCodeSelfVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserCodeSelfVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserCodeVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserCodeVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type PageUserVO_ = {
    countId?: string;
    current?: number;
    maxLimit?: number;
    optimizeCountSql?: boolean;
    orders?: OrderItem[];
    pages?: number;
    records?: UserVO[];
    searchCount?: boolean;
    size?: number;
    total?: number;
  };

  type ReviewDTO = {
    id?: number;
    reviewMessage?: string;
    reviewStatus?: number;
  };

  type SseEmitter = {
    timeout?: number;
  };

  type uploadFileUsingPOSTParams = {
    biz?: string;
  };

  type User = {
    createTime?: string;
    id?: number;
    isDelete?: number;
    updateTime?: string;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userBirthday?: string;
    userEmail?: string;
    userGender?: number;
    userName?: string;
    userPassword?: string;
    userPhone?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserCodeAddRequest = {
    codeContent?: string;
    codeDesc?: string;
    codeLanguage?: string;
    codePwd?: string;
    codeTags?: string[];
    codeTitle?: string;
    codeType?: string;
    expireTime?: string;
    isExpire?: number;
    isPublic?: number;
  };

  type UserCodeQueryRequest = {
    codeContent?: string;
    codeDesc?: string;
    codeLanguage?: string;
    codeTags?: string[];
    codeTitle?: string;
    codeType?: string;
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type UserCodeSelfVO = {
    codeContent?: string;
    codeDesc?: string;
    codeLanguage?: string;
    codePwd?: string;
    codeTags?: string[];
    codeTitle?: string;
    codeType?: string;
    createTime?: string;
    expireTime?: string;
    id?: number;
    isExpire?: number;
    isPublic?: number;
    updateTime?: string;
    userId?: number;
  };

  type UserCodeUpdateRequest = {
    codeContent?: string;
    codeDesc?: string;
    codeLanguage?: string;
    codePwd?: string;
    codeTags?: string[];
    codeTitle?: string;
    codeType?: string;
    expireTime?: string;
    id?: number;
    isExpire?: number;
    isPublic?: number;
  };

  type UserCodeVO = {
    codeContent?: string;
    codeDesc?: string;
    codeLanguage?: string;
    codeTags?: string[];
    codeTitle?: string;
    codeType?: string;
    createTime?: string;
    expireTime?: string;
    id?: number;
    isExpire?: number;
    isPublic?: number;
    updateTime?: string;
    userId?: number;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserQueryRequest = {
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    userAccount?: string;
    userAddress?: string;
    userBirthday?: string;
    userEmail?: string;
    userGender?: number;
    userName?: string;
    userPhone?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserRegisterRequest = {
    checkPassword?: string;
    code?: string;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userBirthday?: string;
    userEmail?: string;
    userGender?: number;
    userName?: string;
    userPassword?: string;
    userPhone?: string;
    userProfile?: string;
  };

  type UserUpdateRequest = {
    checkPassword?: string;
    code?: string;
    id?: number;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userBirthday?: string;
    userEmail?: string;
    userGender?: number;
    userName?: string;
    userPassword?: string;
    userPhone?: string;
    userProfile?: string;
  };

  type UserUsageDTO = {
    remark?: string;
    usageId?: number;
    usageType?: string;
  };

  type UserVO = {
    createTime?: string;
    id?: number;
    updateTime?: string;
    userAccount?: string;
    userAddress?: string;
    userAvatar?: string;
    userBirthday?: string;
    userEmail?: string;
    userGender?: number;
    userName?: string;
    userPhone?: string;
    userProfile?: string;
    userRole?: string;
  };
}
