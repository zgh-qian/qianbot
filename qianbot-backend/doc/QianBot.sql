-- 创建库
create database if not exists qianbot;
-- 切换库
use qianbot;
-- 用户表
drop table if exists user;
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null unique comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：admin/vip/user/ban',
    userEmail    varchar(256)                           null unique comment '邮箱，唯一',
    userPhone    varchar(128)                           null unique comment '手机号，唯一，必须是11位数字',
    userGender   tinyint      default 0                 null comment '性别：0-未知，1-男，2-女',
    userBirthday date                                   null comment '生日，存储年月日',
    userAddress  varchar(512)                           null comment '地址',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_user_id (id)
) comment '用户' collate = utf8mb4_unicode_ci;
-- 用户使用表
drop table if exists userusage;
create table if not exists userusage
(
    id             bigint auto_increment comment 'id' primary key,
    usageId        bigint                             null comment '使用id',
    usageType      varchar(128)                       not null comment '使用类型',
    usedCount      int      default 0                 not null comment '使用次数',
    remainingCount int      default 0                 not null comment '剩余次数',
    remark         varchar(1024)                      null comment '备注',
    userId         bigint                             not null comment '用户id',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete       tinyint  default 0                 not null comment '是否删除',
    index idx_user_usage_id (userId)
) comment '用户使用表' collate = utf8mb4_unicode_ci;
-- 应用表
drop table if exists app;
create table if not exists app
(
    id              bigint auto_increment comment 'id' primary key,
    appName         varchar(128)                       not null comment '应用名',
    appDesc         text                               null comment '应用描述',
    appIcon         varchar(1024)                      null comment '应用图标',
    appType         tinyint  default 0                 not null comment '应用类型（0-得分类，1-测评类）',
    scoringStrategy tinyint  default 0                 not null comment '评分策略（0-自定义，1-AI）',
    reviewStatus    tinyint  default 0                 not null comment '审核状态：0-待审核,1-审核中,2-通过,3-拒绝',
    reviewMessage   varchar(512)                       null comment '审核信息',
    reviewerId      bigint                             null comment '审核人id',
    reviewTime      datetime                           null comment '审核时间',
    userId          bigint                             not null comment '创建用户id',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint  default 0                 not null comment '是否删除',
    index idx_appName (appName)
) comment '应用' collate = utf8mb4_unicode_ci;
-- 题目表
drop table if exists appquestion;
create table if not exists appquestion
(
    id           bigint auto_increment comment 'id' primary key,
    questionPic  varchar(1024)                      null comment '题目图片',
    questionName varchar(256)                       not null comment '题目',
    appId        bigint                             not null comment '应用id',
    userId       bigint                             not null comment '创建用户id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    index idx_appId (appId),
    foreign key (appId) references app (id)
) comment '题目' collate = utf8mb4_unicode_ci;
-- 选项表
drop table if exists appoption;
create table if not exists appoption
(
    id           bigint auto_increment comment 'id' primary key,
    optionPic    varchar(1024)                      null comment '选项图片',
    optionKey    varchar(128)                       not null comment '选项键',
    optionName   varchar(256)                       not null comment '选项名称',
    optionResult varchar(256)                       not null comment '选项结果',
    questionId   bigint                             not null comment '问题id',
    userId       bigint                             not null comment '创建用户id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    index idx_questionId (questionId),
    foreign key (questionId) references appquestion (id)
) comment '选项' collate = utf8mb4_unicode_ci;
-- 评分结果表
drop table if exists appresult;
create table if not exists appresult
(
    id          bigint auto_increment comment 'id' primary key,
    resultName  varchar(512)                       not null comment '结果名称',
    resultPic   varchar(1024)                      null comment '结果图片',
    resultDesc  text                               null comment '结果描述',
    resultProp  varchar(512)                       null comment '结果属性集合(JSON数组)',
    resultScore int                                null comment '结果得分',
    appId       bigint                             not null comment '应用id',
    userId      bigint                             not null comment '创建用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_appId (appId),
    foreign key (appId) references app (id)
) comment '评分结果' collate = utf8mb4_unicode_ci;
-- 用户答题记录表
drop table if exists appanswer;
create table if not exists appanswer
(
    id           bigint auto_increment primary key,
    appId        bigint                             not null comment '应用id',
    userAnswer   text                               null comment '用户答案(JSON 数组)',
    resultStatus tinyint  default 0                 not null comment '结果状态，0-待判断，1-判断中，2-已完成，3-已失败',
    resultId     bigint                             null comment '评分结果id',
    resultName   varchar(256)                       null comment '结果名称',
    resultDesc   text                               null comment '结果描述',
    resultScore  int                                null comment '得分',
    resultPic    varchar(1024)                      null comment '结果图片',
    userId       bigint                             not null comment '用户id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    index idx_appId (appId),
    index idx_userId (userId)
) comment '用户答题记录' collate = utf8mb4_unicode_ci;

-- 图表
drop table if exists chart;
create table if not exists chart
(
    id          bigint auto_increment comment 'id' primary key,
    chartName   varchar(256)                       not null comment '图表名称',
    chartGoal   varchar(1024)                      not null comment '分析目标',
    chartType   varchar(128)                       null comment '图表类型',
    chartData   text                               null comment '图表数据',
    genData     text                               null comment '生成的图表数据',
    genResult   text                               null comment '生成的分析结论',
    chartStatus tinyint  default 0                 not null comment '状态，0-待生成，1-生成中，2-已生成，3-已失败',
    userId      bigint                             not null comment '创建用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '图表信息表' collate = utf8mb4_unicode_ci;

-- 题目表
drop table if exists ojquestion;
create table if not exists ojquestion
(
    id         bigint auto_increment primary key comment 'id',
    title      varchar(512)                       not null comment '题目标题',
    tags       varchar(512)                       not null comment '标签(json数组)',
    difficulty tinyint                            not null comment '难度，0-简单，1-中等，2-困难',
    userId     bigint                             not null comment '创建用户id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_id (id)
) comment '题目表' collate = utf8mb4_unicode_ci;
-- 题目详情表
drop table if exists ojdetail;
create table if not exists ojdetail
(
    id          bigint auto_increment primary key comment 'id',
    timeLimit   bigint                             not null comment '时间限制(ms)',
    memoryLimit bigint                             not null comment '内存限制(kb)',
    content     text                               not null comment '内容',
    template    text                               null comment '模板代码(json)',
    answer      text                               null comment '题目答案(json)',
    tips        text                               null comment '提示(json数组)',
    questionId  bigint                             not null comment '题目id',
    userId      bigint                             not null comment '创建用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '题目详情表' collate = utf8mb4_unicode_ci;
-- 题目判题用例表
drop table if exists ojjudgecase;
create table if not exists ojjudgecase
(
    id         bigint auto_increment primary key comment 'id',
    input      text                               not null comment '输入用例',
    output     text                               not null comment '输出用例',
    questionId bigint                             not null comment '题目id',
    userId     bigint                             not null comment '创建用户id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '题目判题用例表' collate = utf8mb4_unicode_ci;
-- 题目提交表
drop table if exists ojsubmit;
create table if not exists ojsubmit
(
    id          bigint auto_increment primary key comment 'id',
    language    varchar(128)                       not null comment '编程语言',
    code        text                               not null comment '代码',
    status      varchar(128)                       null comment '运行结果，比如：AC',
    detail      text                               null comment '运行结果信息',
    timeUsed    bigint                             null comment '时间消耗(ms)',
    memoryUsed  bigint                             null comment '内存消耗(kb)',
    judgeStatus tinyint  default 0                 not null comment '判题状态：0-待判题，1-判题中，2-判题成功，3-判题失败',
    questionId  bigint                             not null comment '题目id',
    userId      bigint                             not null comment '创建用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '题目提交表' collate = utf8mb4_unicode_ci;
-- 题目统计表
drop table if exists ojstats;
create table if not exists ojstats
(
    id           bigint auto_increment primary key comment 'id',
    AC           int      default 0                 not null comment '通过数',
    WA           int      default 0                 not null comment '输出错误数',
    CE           int      default 0                 not null comment '编译错误数',
    RE           int      default 0                 not null comment '运行错误数',
    PE           int      default 0                 not null comment '格式错误数',
    TLE          int      default 0                 not null comment '时间超限数',
    MLE          int      default 0                 not null comment '内存超限数',
    thumbCount   int      default 0                 not null comment '点赞数',
    favourCount  int      default 0                 not null comment '收藏数',
    commentCount int      default 0                 not null comment '评论数',
    userId       bigint                             not null comment '创建用户id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除'
) comment '题目统计表' collate = utf8mb4_unicode_ci;
-- 用户代码表
drop table if exists usercode;
create table if not exists usercode
(
    id           bigint auto_increment primary key comment 'id',
    codeTitle    varchar(128)                       not null comment '标题',
    codeLanguage varchar(128)                       not null comment '语言',
    codeContent  text                               not null comment '代码',
    codeType     varchar(128)                       null comment '代码类型，比如：算法、前端、后端',
    codeTags     varchar(512)                       null comment '代码标签(json数组)',
    codeDesc     text                               null comment '代码描述',
    codePwd      varchar(128)                       null comment '代码密码',
    expireTime   datetime                           null comment '过期时间',
    isPublic     tinyint  default 0                 not null comment '是否公开：0-私密，1-公开',
    isExpire     tinyint  default 0                 not null comment '是否过期：0-未过期，1-已过期',
    userId       bigint                             not null comment '创建用户id',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除'
) comment '用户代码表' collate = utf8mb4_unicode_ci;