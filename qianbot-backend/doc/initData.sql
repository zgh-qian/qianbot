
-- user
INSERT INTO qianbot.user (id, userAccount, userPassword, userName, userAvatar, userProfile, userRole, userEmail, userPhone, userGender, userBirthday, userAddress, createTime, updateTime, isDelete) VALUES (1809845829464084482, 'qian', '28030b7bd3e3ad69ac0e3ef2d4b9b697', '谦', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3519559594,1991081822&fm=253&gp=0.jpg', '你好', 'admin', '3247666236@qq.com', '198', null, null, null, '2024-07-07 15:04:05', '2024-07-19 16:48:47', 0);
-- app
INSERT INTO qianbot.app (id, appName, appDesc, appIcon, appType, scoringStrategy, reviewStatus, reviewMessage, reviewerId, reviewTime, userId, createTime, updateTime, isDelete) VALUES (1811574629893758978, '根据题目可以测试你的性格特征 ', 'MBTI性格测试', 'https://qian-1318342057.cos.ap-guangzhou.myqcloud.com/app_icon/1809845829464084482/fuXz9ZEy-567.jpg', 1, 0, 2, '', 1809845829464084482, '2024-07-19 15:33:54', 1809845829464084482, '2024-07-12 09:33:43', '2024-07-18 10:32:24', 0);
INSERT INTO qianbot.app (id, appName, appDesc, appIcon, appType, scoringStrategy, reviewStatus, reviewMessage, reviewerId, reviewTime, userId, createTime, updateTime, isDelete) VALUES (1813403591519285249, 'MBTI十六人格测试', 'MBTI将人分为十六种可能的人格类型，旨在帮助人们更好地了解自己和他人的性格特征、价值观以及行为习惯，并能够辅助进行职业规划、人际交往、自我成长等方面的探索。它使人们能够欣赏和尊重差异，以实现更好的沟通、关系、团队合作和更美好的生活。', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.bifDI_HDUh1lxYcreG3UOwHaEu?rs=1&pid=ImgDetMain', 1, 1, 2, '', 1809845829464084482, '2024-07-19 15:34:00', 1809845829464084482, '2024-07-17 10:41:22', '2024-07-17 10:41:22', 0);
INSERT INTO qianbot.app (id, appName, appDesc, appIcon, appType, scoringStrategy, reviewStatus, reviewMessage, reviewerId, reviewTime, userId, createTime, updateTime, isDelete) VALUES (1813560887096492034, '全国ikun统一考试', '测试你是真爱粉还是黑粉
满分（100分）
0-20-初级ikun
30-50-中级ikun
60-80-高级ikun
90-100ikun团长', 'https://ts1.cn.mm.bing.net/th/id/R-C.e4fe1a1677f72143432de42d44e85f9f?rik=QNhDoEtIS2Ve3A&riu=http%3a%2f%2finews.gtimg.com%2fnewsapp_match%2f0%2f15103659616%2f0&ehk=gvcKqCFPB5ADv7JtilqqKL%2b1U87peA41C0HCNdeflKc%3d&risl=&pid=ImgRaw&r=0', 0, 0, 2, '', 1809845829464084482, '2024-07-19 16:13:11', 1809845829464084482, '2024-07-17 21:06:24', '2024-07-17 21:21:39', 0);
INSERT INTO qianbot.app (id, appName, appDesc, appIcon, appType, scoringStrategy, reviewStatus, reviewMessage, reviewerId, reviewTime, userId, createTime, updateTime, isDelete) VALUES (1813569058405990402, '谦粉测试', '测试你是不是真的谦粉', 'https://ts1.cn.mm.bing.net/th/id/R-C.298d7346b582994fc5dab1a68f6e51fd?rik=rbPpF6qwnBBLAw&riu=http%3a%2f%2fn.sinaimg.cn%2fsinakd20114%2f762%2fw1000h562%2f20230417%2f2bbd-2c9a23c429f889bbcbce2d36ddd0280d.jpg&ehk=0%2bCFuY6kvZFA7LNSHKKT%2fD9eorgfI%2bsSlfgP4HHFCDo%3d&risl=&pid=ImgRaw&r=0', 0, 0, 2, '', 1809845829464084482, '2024-07-18 10:20:03', 1809845829464084482, '2024-07-17 21:38:52', '2024-07-17 21:38:52', 0);
-- appQuestion
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856769, '', '你是否倾向于与人交往时思考自己的内心感受？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856772, '', '在解决问题时，你更依赖哪种方式？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856775, '', '在做决策时，你更倾向于？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771331, '', '你是否喜欢规划和安排事情？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771334, '', '在社交场合中，你是否通常是主动发起交流的一方？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771337, '', '你是否倾向于关注细节和具体事实？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771340, '', '在面对新的挑战时，你通常会如何反应？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771343, '', '你是否喜欢在日常生活或工作中尝试不同的方法和途径？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000911880194, '', '在团队合作中，你通常扮演什么角色？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812389000911880197, '', '你是否容易受到外界事物和人群的影响？', 1811574629893758978, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082565107714, '', '你是否倾向于与人交往时思考问题？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082565107717, '', '在解决问题时，你更依赖直觉还是实感？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216579, '', '在决策时，你更倾向于情感还是逻辑？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216582, '', '你是否喜欢规划和组织活动？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216585, '', '在社交场合中，你是否通常是先行动后思考？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216588, '', '你是否倾向于关注细节而不是整体概念？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131139, '', '你是否更喜欢独自工作而不是团队合作？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131142, '', '在处理信息时，你是更倾向于判断还是知觉？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131145, '', '你是否通常在做决定时依赖内在的思考？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131148, '', '在面对压力时，你是更倾向于保持冷静还是表达情绪？', 1811574629893758978, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216297988097, '', '在社交场合中，你通常是', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216344125443, '', '当处理问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216344125446, '', '在日常生活中，你更享受', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234308, '', '在面对新的挑战时，你通常觉得', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234311, '', '在做决定时，你更看重', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234314, '', '在团队合作中，你倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148868, '', '在表达观点时，你更偏向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148871, '', '在处理信息时，你更偏好', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148874, '', '在压力下，你通常', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148877, '', '在休闲时间，你更喜欢', 1813403591519285249, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890369, '', '在社交场合中，你通常是', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890372, '', '当处理问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890375, '', '在日常生活中，你更享受', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999234, '', '在面对新的挑战时，你通常觉得', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999237, '', '在做决定时，你更看重', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999240, '', '在团队合作中，你倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999243, '', '在表达个人观点时，你通常', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913794, '', '在解决问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913797, '', '在与人互动时，你更注重', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913800, '', '在评价事物时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605319684098, '', '在社交场合中，你通常是', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598658, '', '当处理问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598661, '', '在日常生活中，你更享受', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598664, '', '在面对新的挑战时，你通常觉得', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598667, '', '在团队合作中，你更看重', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598670, '', '做决定时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598673, '', '在解决问题时，你更愿意', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598676, '', '你通常如何处理信息', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598679, '', '在与人交往时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406605449707522, '', '对于未来，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736291020802, '', '在社交场合中，你通常是', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935363, '', '当处理问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935366, '', '在日常生活中，你更享受', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935369, '', '在面对新的挑战时，你通常觉得', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935372, '', '在做决定时，你更看重', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935375, '', '在团队合作中，你倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935378, '', '在表达个人观点时，你通常', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935381, '', '在解决问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935384, '', '在与人互动时，你更注重', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813406736421044226, '', '在评价事物时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483598983786497, '', '在社交场合中，你通常是', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483598983786500, '', '当处理问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701060, '', '在日常生活中，你更享受', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701063, '', '在面对新的挑战时，你通常觉得', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701066, '', '在做决定时，你更看重', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701069, '', '在团队合作中，你倾向于', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701072, '', '在表达观点时，你更偏向于', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809923, '', '在处理信息时，你更偏好', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809926, '', '在压力下，你通常', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809929, '', '在休闲时间，你更喜欢', 1813403591519285249, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813562155126865922, '', '树枝的意思是', 1813560887096492034, 1809845829464084482, '2024-07-17 21:11:26', '2024-07-17 21:11:26', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813562932767604738, '', '荔枝的意思是', 1813560887096492034, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813562932767604742, '', '本次考试的科目是', 1813560887096492034, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519300, '', 'rap的意思是什么', 1813560887096492034, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813562932893433858, '', '蔡徐坤的粉丝叫什么', 1813560887096492034, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813564639895470081, '', '春风又绿江南岸，______时长______半', 1813560887096492034, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384644, '', '借问酒家何处有，牧童遥指_____', 1813560887096492034, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384648, '', 'ikun口中kun的意思是', 1813560887096492034, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813564640021299202, '', '以下哪位中国人曾任NBA中国形象大使？', 1813560887096492034, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687816, '', '网红歌曲层出不穷的当今，唯有“真金”不怕“火炼”.时隔多年仍令kun津津乐道的歌曲《wait wait wait》全曲循环最多的单词是什么', 1813560887096492034, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546530037761, '', '薛之谦的第一首歌是什么', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952323, '', '薛之谦的第一场演唱会是什么时候？', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952328, '', '薛之谦是在哪个国家留学的?', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061189, '', '薛之谦的生日？', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061194, '', '薛之谦的代表作《演员》收录在哪张专辑中？', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170052, '', '薛之谦出道前曾参加过哪个选秀节目？', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170057, '', '薛之谦的经纪公司是？', 1813569058405990402, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012365545473, '', '在社交场合中，你通常是', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012365545476, '', '当处理问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654340, '', '在日常生活中，你更享受', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654343, '', '在面对新的挑战时，你通常觉得', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654346, '', '在团队合作中，你更看重', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654349, '', '在做决定时，你更依赖于', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568900, '', '在与人互动时，你倾向于', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568903, '', '对于日常安排，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568906, '', '在解决问题时，你更倾向于', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568909, '', '在表达意见时，你通常', 1813403591519285249, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035266, '', '你是否倾向于与人交往时分享个人的想法和感受？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035269, '', '在解决问题时，你更依赖直觉还是具体事实？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035272, '', '你是否倾向于在决策时更多地考虑情感因素？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035275, '', '你是否喜欢生活有序，计划好每一个细节？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035278, '', '在社交场合中，你是否通常是那个先发起对话的人？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949825, '', '你更倾向于关注大局还是细节？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949828, '', '你是否倾向于用逻辑和理性来解决问题？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949831, '', '你是否愿意灵活应对变化，而不是遵循既定计划？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949834, '', '在面对新的经历和挑战时，你是开放还是谨慎？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appquestion (id, questionPic, questionName, appId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949837, '', '在团队合作中，你通常是那个提供结论的人还是那个提出问题的人？', 1811574629893758978, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);

-- appOption
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856770, 'I', 'A', '是，我通常会反思自己的感受和想法', 'I', 1812389000781856769, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856771, 'E', 'B', '不是，我更关注外界的活动和变化', 'E', 1812389000781856769, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856773, 'N', 'A', '依赖直觉和想象，寻找新的可能性', 'N', 1812389000781856772, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856774, 'S', 'B', '依赖现实和具体信息，注重实际经验', 'S', 1812389000781856772, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000781856776, 'F', 'A', '基于情感和价值观进行决策', 'F', 1812389000781856775, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771330, 'T', 'B', '基于逻辑和客观分析进行决策', 'T', 1812389000781856775, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771332, 'J', 'A', '是，我喜欢有组织和计划性的事情', 'J', 1812389000844771331, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771333, 'P', 'B', '不是，我更愿意随机应变，享受灵活性', 'P', 1812389000844771331, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771335, 'E', 'A', '是，我通常主动与人交流', 'E', 1812389000844771334, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771336, 'I', 'B', '不是，我更倾向于等待别人来找我交流', 'I', 1812389000844771334, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771338, 'S', 'A', '是，我注重细节和事实', 'S', 1812389000844771337, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771339, 'N', 'B', '不是，我更关注概念和整体图景', 'N', 1812389000844771337, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771341, 'T', 'A', '保持谨慎，评估风险和后果', 'T', 1812389000844771340, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771342, 'F', 'B', '充满好奇，愿意尝试新的方法', 'F', 1812389000844771340, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771344, 'P', 'A', '是，我喜欢探索新的可能性', 'P', 1812389000844771343, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000844771345, 'J', 'B', '不是，我更习惯于使用已知和有效的方法', 'J', 1812389000844771343, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000911880195, 'N', 'A', '我通常是提出创意和想法的人', 'N', 1812389000911880194, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000911880196, 'S', 'B', '我通常是执行和落实计划的人', 'S', 1812389000911880194, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000911880198, 'E', 'A', '是，我容易受外界影响', 'E', 1812389000911880197, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812389000911880199, 'I', 'B', '不是，我更独立，不太受外界影响', 'I', 1812389000911880197, 1809845829464084482, '2024-07-14 15:29:45', '2024-07-14 15:29:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082565107715, 'T', 'A', '是，我通常先思考再行动', 'T', 1812823082565107714, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082565107716, 'F', 'B', '不是，我通常直接根据感觉行事', 'F', 1812823082565107714, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216577, 'N', 'A', '直觉，我更看重可能性与潜在联系', 'N', 1812823082565107717, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216578, 'S', 'B', '实感，我更注重事实和具体信息', 'S', 1812823082565107717, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216580, 'F', 'A', '情感，我更关心决策对他人的影响', 'F', 1812823082632216579, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216581, 'T', 'B', '逻辑，我更注重决策的合理性和效率', 'T', 1812823082632216579, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216583, 'J', 'A', '是，我喜欢规划和安排事物的顺序', 'J', 1812823082632216582, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216584, 'P', 'B', '不是，我更愿意随机应变，享受灵活性', 'P', 1812823082632216582, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216586, 'E', 'A', '是，我倾向于先行动后反思', 'E', 1812823082632216585, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216587, 'I', 'B', '不是，我更倾向于先观察和思考', 'I', 1812823082632216585, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082632216589, 'S', 'A', '是，我更关注细节和具体实施', 'S', 1812823082632216588, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131138, 'N', 'B', '不是，我更看重整体和宏观概念', 'N', 1812823082632216588, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131140, 'I', 'A', '是，我更享受独立工作', 'I', 1812823082695131139, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131141, 'E', 'B', '不是，我更倾向于在团队中工作', 'E', 1812823082695131139, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131143, 'J', 'A', '判断，我更喜欢有结构和明确的决策', 'J', 1812823082695131142, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131144, 'P', 'B', '知觉，我更愿意保持开放性和适应性', 'P', 1812823082695131142, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131146, 'I', 'A', '是，我依赖内在的思考和价值观', 'I', 1812823082695131145, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131147, 'E', 'B', '不是，我更倾向于考虑外在的事实和情况', 'E', 1812823082695131145, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082695131149, 'T', 'A', '冷静，我通常保持冷静并处理问题', 'T', 1812823082695131148, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1812823082762240001, 'F', 'B', '情绪，我可能会表达情绪以释放压力', 'F', 1812823082695131148, 1809845829464084482, '2024-07-15 20:14:38', '2024-07-15 20:14:38', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216344125441, 'E', 'A', '首先与陌生人交谈的人', 'E', 1813406216297988097, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216344125442, 'I', 'B', '等待别人来与你交谈的人', 'I', 1813406216297988097, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216344125444, 'T', 'A', '依据逻辑和客观分析', 'T', 1813406216344125443, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216344125445, 'F', 'B', '考虑情感和主观因素', 'F', 1813406216344125443, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234306, 'J', 'A', '规划和结构化的事物', 'J', 1813406216344125446, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234307, 'P', 'B', '灵活和自由的事物', 'P', 1813406216344125446, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234309, 'S', 'A', '兴奋和激动', 'S', 1813406216411234308, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234310, 'N', 'B', '紧张和担忧', 'N', 1813406216411234308, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234312, 'S', 'A', '事实和细节', 'S', 1813406216411234311, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216411234313, 'N', 'B', '概念和想象力', 'N', 1813406216411234311, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148866, 'J', 'A', '提出并执行计划', 'J', 1813406216411234314, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148867, 'P', 'B', '贡献创意和灵活性', 'P', 1813406216411234314, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148869, 'T', 'A', '直接和坦率', 'T', 1813406216474148868, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148870, 'F', 'B', '温和和敏感', 'F', 1813406216474148868, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148872, 'S', 'A', '具体和实际的数据', 'S', 1813406216474148871, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148873, 'N', 'B', '抽象和理论化的概念', 'N', 1813406216474148871, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148875, 'T', 'A', '保持冷静和理性', 'T', 1813406216474148874, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216474148876, 'F', 'B', '表现出情感和敏感性', 'F', 1813406216474148874, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216541257730, 'J', 'A', '有组织和计划的活动', 'J', 1813406216474148877, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406216541257731, 'P', 'B', '无计划和即兴的活动', 'P', 1813406216474148877, 1809845829464084482, '2024-07-17 10:51:48', '2024-07-17 10:51:48', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890370, 'E', 'A', '首先与陌生人交谈的人', 'E', 1813406407528890369, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890371, 'I', 'B', '等待别人来与你交谈的人', 'I', 1813406407528890369, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890373, 'T', 'A', '依据逻辑和客观分析', 'T', 1813406407528890372, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890374, 'F', 'B', '考虑情感和主观因素', 'F', 1813406407528890372, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890376, 'J', 'A', '规划和结构化的事物', 'J', 1813406407528890375, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407528890377, 'P', 'B', '灵活和自由的事物', 'P', 1813406407528890375, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999235, 'S', 'A', '兴奋和激动', 'S', 1813406407595999234, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999236, 'N', 'B', '紧张和担忧', 'N', 1813406407595999234, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999238, 'S', 'A', '事实和细节', 'S', 1813406407595999237, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999239, 'N', 'B', '概念和想象力', 'N', 1813406407595999237, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999241, 'J', 'A', '提出并执行计划', 'J', 1813406407595999240, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999242, 'P', 'B', '贡献创意和灵活性', 'P', 1813406407595999240, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999244, 'T', 'A', '直接和坦率', 'T', 1813406407595999243, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407595999245, 'F', 'B', '温和和考虑他人感受', 'F', 1813406407595999243, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913795, 'P', 'A', '思考所有可能的选择', 'P', 1813406407658913794, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913796, 'J', 'B', '遵循既定的程序', 'J', 1813406407658913794, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913798, 'I', 'A', '深度和意义', 'I', 1813406407658913797, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913799, 'E', 'B', '广泛和多样性', 'E', 1813406407658913797, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913801, 'T', 'A', '客观和公正', 'T', 1813406407658913800, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406407658913802, 'F', 'B', '主观和同情', 'F', 1813406407658913800, 1809845829464084482, '2024-07-17 10:52:33', '2024-07-17 10:52:33', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605319684099, 'E', 'A', '首先与陌生人交谈的人', 'E', 1813406605319684098, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605319684100, 'I', 'B', '等待别人来与你交谈的人', 'I', 1813406605319684098, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598659, 'T', 'A', '依据逻辑和客观分析', 'T', 1813406605382598658, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598660, 'F', 'B', '考虑情感和主观因素', 'F', 1813406605382598658, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598662, 'J', 'A', '规划和结构', 'J', 1813406605382598661, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598663, 'P', 'B', '自由和灵活性', 'P', 1813406605382598661, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598665, 'S', 'A', '兴奋和激动', 'S', 1813406605382598664, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598666, 'N', 'B', '紧张和担忧', 'N', 1813406605382598664, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598668, 'T', 'A', '事实和结果', 'T', 1813406605382598667, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598669, 'F', 'B', '人际关系和和谐', 'F', 1813406605382598667, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598671, 'P', 'A', '快速并依据直觉', 'P', 1813406605382598670, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598672, 'J', 'B', '仔细思考并评估选项', 'J', 1813406605382598670, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598674, 'N', 'A', '尝试多种可能性', 'N', 1813406605382598673, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598675, 'S', 'B', '坚持已知的有效方法', 'S', 1813406605382598673, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598677, 'S', 'A', '通过感觉和具体细节', 'S', 1813406605382598676, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598678, 'N', 'B', '通过直觉和整体感知', 'N', 1813406605382598676, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598680, 'T', 'A', '直接并坦诚', 'T', 1813406605382598679, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605382598681, 'F', 'B', '温和并避免冲突', 'F', 1813406605382598679, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605449707523, 'J', 'A', '制定明确的计划', 'J', 1813406605449707522, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406605449707524, 'P', 'B', '保持开放和灵活的态度', 'P', 1813406605449707522, 1809845829464084482, '2024-07-17 10:53:20', '2024-07-17 10:53:20', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736291020803, 'E', 'A', '首先与陌生人交谈的人', 'E', 1813406736291020802, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935362, 'I', 'B', '等待别人来与你交谈的人', 'I', 1813406736291020802, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935364, 'T', 'A', '依据逻辑和客观分析', 'T', 1813406736353935363, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935365, 'F', 'B', '考虑情感和主观因素', 'F', 1813406736353935363, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935367, 'J', 'A', '规划和结构化的事物', 'J', 1813406736353935366, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935368, 'P', 'B', '灵活和自由的事物', 'P', 1813406736353935366, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935370, 'S', 'A', '兴奋和激动', 'S', 1813406736353935369, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935371, 'N', 'B', '紧张和担忧', 'N', 1813406736353935369, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935373, 'S', 'A', '事实和细节', 'S', 1813406736353935372, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935374, 'N', 'B', '概念和想象力', 'N', 1813406736353935372, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935376, 'J', 'A', '提出并执行计划', 'J', 1813406736353935375, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935377, 'P', 'B', '贡献创意和灵活性', 'P', 1813406736353935375, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935379, 'T', 'A', '直接和坦率', 'T', 1813406736353935378, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935380, 'F', 'B', '温和和考虑他人感受', 'F', 1813406736353935378, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935382, 'P', 'A', '思考所有可能的选择', 'P', 1813406736353935381, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935383, 'J', 'B', '遵循既定的程序', 'J', 1813406736353935381, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935385, 'I', 'A', '深度和意义', 'I', 1813406736353935384, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736353935386, 'E', 'B', '广泛和多样性', 'E', 1813406736353935384, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736421044227, 'T', 'A', '客观和公正', 'T', 1813406736421044226, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813406736421044228, 'F', 'B', '主观和同情', 'F', 1813406736421044226, 1809845829464084482, '2024-07-17 10:53:52', '2024-07-17 10:53:52', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483598983786498, 'E', 'A', '首先与陌生人交谈的人', 'E', 1813483598983786497, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483598983786499, 'I', 'B', '等待别人来与你交谈的人', 'I', 1813483598983786497, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701058, 'T', 'A', '依据逻辑和客观分析', 'T', 1813483598983786500, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701059, 'F', 'B', '考虑情感和主观因素', 'F', 1813483598983786500, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701061, 'J', 'A', '规划和结构化的事物', 'J', 1813483599046701060, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701062, 'P', 'B', '灵活和自由的事物', 'P', 1813483599046701060, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701064, 'S', 'A', '兴奋和激动', 'S', 1813483599046701063, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701065, 'N', 'B', '紧张和担忧', 'N', 1813483599046701063, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701067, 'S', 'A', '事实和细节', 'S', 1813483599046701066, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701068, 'N', 'B', '概念和想象力', 'N', 1813483599046701066, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701070, 'J', 'A', '提出并执行计划', 'J', 1813483599046701069, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599046701071, 'P', 'B', '贡献创意和灵活性', 'P', 1813483599046701069, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809921, 'T', 'A', '直接和坦率', 'T', 1813483599046701072, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809922, 'F', 'B', '温和和敏感', 'F', 1813483599046701072, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809924, 'S', 'A', '具体和实际的数据', 'S', 1813483599113809923, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809925, 'N', 'B', '抽象和理论化的概念', 'N', 1813483599113809923, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809927, 'T', 'A', '保持冷静和理性', 'T', 1813483599113809926, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809928, 'F', 'B', '变得情绪化和敏感', 'F', 1813483599113809926, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809930, 'J', 'A', '有组织和计划的活动', 'J', 1813483599113809929, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813483599113809931, 'P', 'B', '无计划和即兴的活动', 'P', 1813483599113809929, 1809845829464084482, '2024-07-17 15:59:17', '2024-07-17 15:59:17', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562155193974785, '0', 'A', '数值', '0', 1813562155126865922, 1809845829464084482, '2024-07-17 21:11:26', '2024-07-17 21:11:26', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562155193974786, '0', 'B', '树脂', '0', 1813562155126865922, 1809845829464084482, '2024-07-17 21:11:26', '2024-07-17 21:11:26', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562155193974787, '10', 'C', '素质', '10', 1813562155126865922, 1809845829464084482, '2024-07-17 21:11:26', '2024-07-17 21:11:26', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932767604739, '0', 'A', '立志', '0', 1813562932767604738, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932767604740, '10', 'B', '理智', '10', 1813562932767604738, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932767604741, '0', 'C', '离职', '0', 1813562932767604738, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519297, '0', 'A', '语文', '0', 1813562932767604742, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519298, '0', 'B', '科学', '0', 1813562932767604742, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519299, '10', 'C', '梗', '10', 1813562932767604742, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519301, '0', 'A', '跳舞', '0', 1813562932830519300, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519302, '0', 'B', '打篮球', '0', 1813562932830519300, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932830519303, '10', 'C', '说唱', '10', 1813562932830519300, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932893433859, '10', 'A', 'ikun', '10', 1813562932893433858, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932893433860, '10', 'B', '小黑子', '10', 1813562932893433858, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813562932893433861, '0', 'C', '黑粉', '0', 1813562932893433858, 1809845829464084482, '2024-07-17 21:14:32', '2024-07-17 21:14:32', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639895470082, '0', 'A', '操练，一年', '0', 1813564639895470081, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384642, '10', 'B', '练习，两年', '10', 1813564639895470081, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384643, '0', 'C', '钝角', '0', 1813564639895470081, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384645, '10', 'A', '蔡徐村', '10', 1813564639958384644, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384646, '0', 'B', '杏花村', '0', 1813564639958384644, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384647, '0', 'C', '华西村', '0', 1813564639958384644, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384649, '0', 'A', '丁真', '0', 1813564639958384648, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384650, '10', 'B', '鸡哥', '10', 1813564639958384648, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564639958384651, '0', 'C', '钝角', '0', 1813564639958384648, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687809, '0', 'A', '丁真', '0', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687810, '0', 'B', '姚明', '0', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687811, '0', 'C', '孙悦', '0', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687812, '0', 'D', '易建联', '0', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687813, '10', 'E', '蔡徐坤', '10', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687814, '0', 'F', '周琦', '0', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640029687815, '0', 'G', '钝角', '0', 1813564640021299202, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640084213762, '0', 'A', 'Ding zhen', '0', 1813564640029687816, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640084213763, '10', 'B', 'wait', '10', 1813564640029687816, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640084213764, '0', 'C', 'btuse angle', '0', 1813564640029687816, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813564640084213765, '0', 'D', 'kun kun', '10', 1813564640029687816, 1809845829464084482, '2024-07-17 21:21:19', '2024-07-17 21:21:19', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546530037762, null, 'A', '《认真的雪》', '10', 1813571546530037761, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546530037763, null, 'B', '《演员》', '0', 1813571546530037761, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952321, null, 'C', '《我好像在哪里见过你》', '0', 1813571546530037761, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952322, null, 'D', '《丑八怪》', '0', 1813571546530037761, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952324, null, 'A', '2002', '0', 1813571546592952323, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952325, null, 'B', '2004', '0', 1813571546592952323, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952326, null, 'C', '2006', '0', 1813571546592952323, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546592952327, null, 'D', '2008', '10', 1813571546592952323, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061185, null, 'A', '德国', '0', 1813571546592952328, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061186, null, 'B', '瑞士', '10', 1813571546592952328, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061187, null, 'C', '法国', '0', 1813571546592952328, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061188, null, 'D', '中国', '0', 1813571546592952328, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061190, null, 'A', '7月17日', '10', 1813571546660061189, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061191, null, 'B', '7月16日', '0', 1813571546660061189, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061192, null, 'C', '7月15日', '0', 1813571546660061189, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061193, null, 'D', '7月14日', '0', 1813571546660061189, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546660061195, null, 'A', '《初学者》', '0', 1813571546660061194, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170049, null, 'B', '《认真的雪》', '0', 1813571546660061194, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170050, null, 'C', '《渡》', '0', 1813571546660061194, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170051, null, 'D', '《意外》', '10', 1813571546660061194, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170053, null, 'A', '《快乐男声》', '0', 1813571546727170052, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170054, null, 'B', '《中国好声音》', '0', 1813571546727170052, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170055, null, 'C', '《歌手》', '0', 1813571546727170052, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170056, null, 'D', '《超级女声》', '10', 1813571546727170052, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170058, null, 'A', ' EE-Media', '10', 1813571546727170057, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170059, null, 'B', '大树音乐', '0', 1813571546727170057, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170060, null, 'C', '星光大道', '0', 1813571546727170057, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813571546727170061, null, 'D', '唱片制造公司', '0', 1813571546727170057, 1809845829464084482, '2024-07-17 21:48:45', '2024-07-17 21:48:45', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012365545474, null, 'A', '首先与陌生人交谈的人', 'E', 1813928012365545473, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012365545475, null, 'B', '等待别人来与你交谈的人', 'I', 1813928012365545473, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654338, null, 'A', '依据逻辑和客观分析', 'T', 1813928012365545476, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654339, null, 'B', '考虑情感和人的因素', 'F', 1813928012365545476, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654341, null, 'A', '规划和结构', 'J', 1813928012432654340, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654342, null, 'B', '自由和灵活性', 'P', 1813928012432654340, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654344, null, 'A', '兴奋和激动', 'S', 1813928012432654343, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654345, null, 'B', '紧张和担忧', 'N', 1813928012432654343, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654347, null, 'A', '清晰的目标和结果', 'J', 1813928012432654346, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012432654348, null, 'B', '开放的过程和创新', 'P', 1813928012432654346, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568898, null, 'A', '事实和数据', 'T', 1813928012432654349, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568899, null, 'B', '个人经验和直觉', 'F', 1813928012432654349, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568901, null, 'A', '直接和坦诚', 'E', 1813928012495568900, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568902, null, 'B', '间接和谨慎', 'I', 1813928012495568900, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568904, null, 'A', '事先规划和组织', 'J', 1813928012495568903, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568905, null, 'B', '随遇而安和灵活变动', 'P', 1813928012495568903, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568907, null, 'A', '逐步和系统化', 'S', 1813928012495568906, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568908, null, 'B', '跳跃和直觉化', 'N', 1813928012495568906, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568910, null, 'A', '直接和明确', 'E', 1813928012495568909, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1813928012495568911, null, 'B', '含蓄和考虑他人感受', 'I', 1813928012495568909, 1809845829464084482, '2024-07-18 21:25:13', '2024-07-18 21:25:13', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035267, null, 'A', '通常是', 'E', 1814127062801035266, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035268, null, 'B', '通常不是', 'I', 1814127062801035266, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035270, null, 'A', '直觉', 'N', 1814127062801035269, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035271, null, 'B', '具体事实', 'S', 1814127062801035269, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035273, null, 'A', '是', 'F', 1814127062801035272, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035274, null, 'B', '否', 'T', 1814127062801035272, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035276, null, 'A', '喜欢', 'J', 1814127062801035275, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035277, null, 'B', '不喜欢', 'P', 1814127062801035275, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035279, null, 'A', '通常是', 'E', 1814127062801035278, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062801035280, null, 'B', '通常不是', 'I', 1814127062801035278, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949826, null, 'A', '大局', 'N', 1814127062863949825, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949827, null, 'B', '细节', 'S', 1814127062863949825, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949829, null, 'A', '是', 'T', 1814127062863949828, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949830, null, 'B', '否', 'F', 1814127062863949828, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949832, null, 'A', '愿意', 'P', 1814127062863949831, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949833, null, 'B', '不愿意', 'J', 1814127062863949831, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949835, null, 'A', '开放', 'N', 1814127062863949834, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949836, null, 'B', '谨慎', 'S', 1814127062863949834, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949838, null, 'A', '提供结论', 'J', 1814127062863949837, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);
INSERT INTO qianbot.appoption (id, optionPic, optionKey, optionName, optionResult, questionId, userId, createTime, updateTime, isDelete) VALUES (1814127062863949839, null, 'B', '提出问题', 'P', 1814127062863949837, 1809845829464084482, '2024-07-19 10:36:11', '2024-07-19 10:36:11', 0);

-- appAnswer
INSERT INTO qianbot.appanswer (id, appId, userAnswer, resultStatus, resultId, resultName, resultDesc, resultScore, resultPic, userId, createTime, updateTime, isDelete) VALUES (1814127164619010048, 1813560887096492034, '[1813562155193974786,1813562932767604740,1813562932830519299,1813562932830519303,1813562932893433861,1813564639958384642,1813564639958384646,1813564639958384650,1813564640029687812,1813564640084213764]', 2, 1813565375823855618, '还得练', '继续唱跳rap', 30, null, 1809845829464084482, '2024-07-19 10:36:44', '2024-07-19 10:36:44', 0);
INSERT INTO qianbot.appanswer (id, appId, userAnswer, resultStatus, resultId, resultName, resultDesc, resultScore, resultPic, userId, createTime, updateTime, isDelete) VALUES (1814204423421865984, 1813569058405990402, '[1813571546530037763,1813571546592952326,1813571546660061187,1813571546660061192,1813571546727170050,1813571546727170055,1813571546727170060]', 3, null, null, null, null, null, 1809845829464084482, '2024-07-19 15:43:40', '2024-07-19 15:43:40', 0);
INSERT INTO qianbot.appanswer (id, appId, userAnswer, resultStatus, resultId, resultName, resultDesc, resultScore, resultPic, userId, createTime, updateTime, isDelete) VALUES (1814204595333804032, 1813560887096492034, '[1813562155193974786,1813562932767604740,1813562932830519299,1813562932830519303,1813562932893433861,1813564639958384642,1813564639958384645,1813564639958384650,1813564640029687813,1813564640084213763]', 2, 1813565313626521601, '高级ikun', '还不错', 60, null, 1809845829464084482, '2024-07-19 15:44:30', '2024-07-19 15:44:30', 0);
INSERT INTO qianbot.appanswer (id, appId, userAnswer, resultStatus, resultId, resultName, resultDesc, resultScore, resultPic, userId, createTime, updateTime, isDelete) VALUES (1814211890633551872, 1813560887096492034, '[1813562155193974786,1813562932767604740,1813562932830519298,1813562932830519302,1813562932893433860,1813564639958384642,1813564639958384645,1813564639958384650,1813564640029687813,1813564640084213763]', 2, 1813565313626521601, '高级ikun', '还不错', 60, 'https://qian-1318342057.cos.ap-guangzhou.myqcloud.com/app_result/1809845829464084482/MkavkDr7-20220710223208_8347f.thumb.1000_0.jpg', 1809845829464084482, '2024-07-19 16:13:26', '2024-07-19 16:13:26', 0);
INSERT INTO qianbot.appanswer (id, appId, userAnswer, resultStatus, resultId, resultName, resultDesc, resultScore, resultPic, userId, createTime, updateTime, isDelete) VALUES (1814213290776113152, 1813569058405990402, '[1813571546530037763,1813571546592952326,1813571546660061187,1813571546660061192,1813571546727170050,1813571546727170055,1813571546727170060]', 3, null, null, null, null, null, 1809845829464084482, '2024-07-19 16:18:55', '2024-07-19 16:18:55', 0);
-- chart
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1810846205755625474, 'dfafas', 'dfafas', '柱状图', 'dfafas', null, null, 0, 1809845829464084482, '2024-07-10 09:19:14', '2024-07-10 09:19:14', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1810858550213033985, '销售额对比', '对比不同产品在过去一年的销售额，并分析哪些产品的销售额增长最快。', '柱状图', 'product,April2022,May2022,June2022,July2022,August2022,September2022,October2022,November2022,December2022,January2023,February2023,March2023
ProductA,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,2100
ProductB,800,900,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900
ProductC,1200,1100,1000,900,800,700,600,500,400,300,200,100', '{"legend":{"data":["ProductA","ProductB","ProductC"]},"xAxis":{"type":"category","data":["April2022","May2022","June2022","July2022","August2022","September2022","October2022","November2022","December2022","January2023","February2023","March2023"]},"yAxis":{"type":"value"},"series":[{"name":"ProductA","type":"bar","data":[1000,1100,1200,1300,1400,1500,1600,1700,1800,1900,2000,2100]},{"name":"ProductB","type":"bar","data":[800,900,1000,1100,1200,1300,1400,1500,1600,1700,1800,1900]},{"name":"ProductC","type":"bar","data":[1200,1100,1000,900,800,700,600,500,400,300,200,100]}]}', '根据数据分析，我们可以得出以下结论：

- 产品A的销售额呈现稳定增长，从2022年4月的1000增长到2023年3月的2100，增长率为110%。
- 产品B的销售额也持续增长，从2022年4月的800增长到2023年3月的1900，增长率为98.75%。
- 产品C的销售额则呈现下降趋势，从2022年4月的1200下降到2023年3月的100，下降率为91.67%。

综合来看，产品A的销售额增长最快，而产品C的销售额持续下降，可能需要进一步的市场策略调整。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-10 10:08:17', '2024-07-10 10:09:08', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1812825302366945281, '分析', '分析', '折线图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"数据分析折线图"},"tooltip":{"trigger":"axis"},"legend":{"data":["数据系列"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":"category","boundaryGap":false,"data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"name":"数据系列","type":"line","stack":"总量","data":[2,3,5]}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据以折线图的形式展示，横坐标（x轴）代表数据点，纵坐标（y轴）代表相应的数值。
- 从图表中可以看出，当横坐标为1时，纵坐标的值为2；当横坐标为2时，纵坐标的值为3；当横坐标为4时，纵坐标的值为5。
- 数据整体呈现上升趋势，表明随着横坐标的增加，纵坐标的值也在增加。
- 由于数据点较少，无法进行更复杂的统计分析，如趋势预测或异常检测。

请注意，这些结论仅基于提供的数据点，并且没有考虑到可能存在的其他数据或背景信息。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-15 20:23:27', '2024-07-15 20:23:27', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813105317927288833, 'as', 'saf', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，我们可以看到x轴上有三个数据点（1, 2, 4），对应的y轴数据为（2, 3, 5）。数据分析结论如下：

1. 数据整体呈现增长趋势，从点(1, 2)到点(4, 5)，y值随着x值的增加而增加。
2. 在x=2时，有一个数据跳跃，从y=2直接增加到y=3，这可能表明了某个特定事件或者变化对数据产生了影响。
3. 由于数据点过少，无法进行更深入的趋势分析或者预测分析。
4. 如果数据点之间有缺失，可能需要进一步的数据收集以获得更连续的趋势线。
5. 折线图清晰地展示了x与y之间的关系，但需要更多的上下文信息来解释数据背后的具体含义。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 14:56:08', '2024-07-16 14:56:08', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813110833290248193, 'asdf', 'asdsa', '折线图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"数据分析折线图"},"tooltip":{"trigger":"axis"},"legend":{"data":["数据系列"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":"category","boundaryGap":false,"data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"name":"数据系列","type":"line","data":[2,3,5]}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据展示了一条折线图，横坐标为1、2、4，纵坐标为2、3、5。
- 从折线图可以看出，随着横坐标的增加，纵坐标的值也在增加，呈现出一种正相关的关系。
- 数据点之间的连线表明了数据随横坐标变化的趋势，从点(1, 2)到点(2, 3)再到点(4, 5)，呈现出一种逐渐上升的趋势。
- 由于数据点较少，仅三个，这限制了我们对整体数据趋势的深入理解，但就目前数据来看，可以初步判断该数据系列是随着横坐标的增加而线性增长的。

请注意，由于实际应用中数据分析通常需要更多的上下文信息和数据点来得出更准确的结论，这里的结论仅基于提供的有限数据。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:18:03', '2024-07-16 15:18:03', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813111933464576001, 'a', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据集包含三个数据点，分别对应于 x 轴的值 1, 2, 和 4，以及 y 轴的值 2, 3, 和 5。
- 通过折线图可以观察到，随着 x 值的增加，y 值也在增加，表现出一种正相关的关系。
- 在数据点 (1, 2) 和 (2, 3) 之间，y 值增加了 1；然而，从数据点 (2, 3) 到 (4, 5)，y 值同样增加了 2，但跨度更大，这可能表明了数据在某些区间内增长速率不同。
- 由于数据点较少，我们无法确定这种趋势是否持续或具有统计意义，需要更多的数据点来进行更准确的分析。

请注意，这些结论仅基于提供的数据点，并且没有考虑到可能影响数据的其他因素。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:22:25', '2024-07-16 15:22:25', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813112161043316737, 'afa', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，生成的折线图显示数据点呈不规则上升趋势。具体分析如下：

- 数据起点为 (1, 2)，表示第一个横坐标（x）值为1时，对应的纵坐标（y）值为2。
- 第二个数据点为 (2, 3)，表明当横坐标值为2时，纵坐标值为3，比第一个点上升了1个单位。
- 第三个数据点为 (4, 5)，这里横坐标跳过了3直接到4，对应的纵坐标值为5，继续保持上升的趋势。

从这些数据点可以观察到，虽然横坐标的间隔不均匀，但整体上纵坐标的值在增加，表明所分析的数据呈现出正向发展的趋势。然而，由于数据点过少且横坐标间隔不规律，这个分析结果需要更多的数据来验证其准确性。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:23:19', '2024-07-16 15:23:19', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813112909277790210, 'sa', 'a', '饼图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":"item"},"legend":{"orient":"vertical","left":"left"},"series":[{"name":"数据","type":"pie","radius":"50%","data":[{"value":1,"name":"x"},{"value":2,"name":"y"}],"emphasis":{"itemStyle":{"shadowBlur":10,"shadowOffsetX":0,"shadowColor":"rgba(0,0,0,0.5)"}}}]}', '根据提供的原始数据，我们可以看到有两对数据点：(1,2) 和 (2,3)。这些数据点在饼图中被表示为两个不同的部分，分别对应于标签 "x" 和 "y"。

数据分析结论：
- 标签 "x" 的数值为1，占整个饼图的一部分。
- 标签 "y" 的数值为2，是数据中最大的值，因此在饼图中占据的面积也是最大的部分。

需要注意的是，通常饼图用于展示各部分占总量的百分比，但在此例中，由于没有总量或百分比信息，饼图仅仅展示了两个数值的相对大小。此外，原始数据中的第二对数据点 (4,5) 似乎未被使用，因为它们没有对应的标签信息，所以没有在图表中体现。如果这是一个错误，那么需要提供正确的标签和数据对应关系以生成准确的饼图。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:26:18', '2024-07-16 15:26:18', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813113292142247937, 'a', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，生成的折线图显示了一个简单的线性关系。数据点从(1, 2)开始，到(4, 5)结束，中间有一个点(2, 3)。从这些数据中可以观察到以下结论：

1. 数据点的分布呈现出随x轴增加而y轴值也增加的趋势。
2. 从点(1, 2)到点(2, 3)，每增加1个单位的x，y值增加1个单位。
3. 然而，从点(2, 3)到点(4, 5)，每增加2个单位的x，y值增加2个单位，保持了同样的斜率。
4. 由于数据点过少，无法确定是否存在某种周期性或非线性关系，需要更多的数据点来进行更深入的分析。

请注意，这些结论仅基于当前提供的数据，并且没有考虑到可能影响数据的外部因素。如果数据集更大或更复杂，分析可能会揭示更多的细节和模式。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:27:49', '2024-07-16 15:27:49', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813116302109016066, 'xy变化', 'xy的规律', '折线图', 'x,y
1,2
2,3
4,5
', '{"animation":false,"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"tooltip":{"trigger":"axis"},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"name":"y","type":"line","data":[2,3,5]}]}', '根据提供的数据，我们可以观察到x和y之间存在一种线性关系，随着x的增加，y也在增加。具体来看：

- 当x=1时，y=2；
- 当x=2时，y=3；
- 当x=4时，y=5。

这表明y的值是x值加1的结果。由于数据点较少，我们无法确定这是否是一种恒定的线性关系还是仅仅是一个巧合。如果数据点更多，我们可以进行更准确的趋势分析。目前的数据分析结论是，在现有数据范围内，x和y之间存在一种简单的线性关系，y的值比x的值大1。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:39:47', '2024-07-16 15:39:47', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813118714957889537, 'sa', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据集包含三个数据点，分别对应于x轴的值1, 2, 和4，以及y轴的值2, 3, 和5。
- 从折线图的趋势来看，随着x轴值的增加，y轴值也在增加，表现出一种正向的线性关系。
- 在x=1时，y=2；在x=2时，y=3；在x=4时，y=5，这表明y值相对于x值的增长率为1:1。
- 由于数据点过少，无法进行更深入的趋势分析或预测，但可以初步判断该数据集呈现的是一种简单的直线增长趋势。如果数据点更多，我们可以进行更复杂的数据分析，例如拟合曲线、预测未来值等。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:49:22', '2024-07-16 15:49:22', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813119845482201089, 'a', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，生成的折线图显示数据点呈不规则上升趋势。具体分析如下：

- 数据起点为 (1, 2)，表示第一个横坐标（x）值为1时，对应的纵坐标（y）值为2。
- 第二个数据点为 (2, 3)，表明当横坐标值为2时，纵坐标值为3，比第一个点上升了1个单位。
- 第三个数据点为 (4, 5)，这里横坐标跳过了3直接到4，对应的纵坐标值为5，继续保持上升的趋势。

从这些数据点可以观察到，虽然横坐标的间隔不一致，但整体上纵坐标的值在增加，表明所分析的数据呈现出正向关系。然而，由于数据点过少且横坐标间隔不规律，这个分析结果需要更多的数据来验证其准确性和趋势的连续性。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 15:53:51', '2024-07-16 15:53:51', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813124781355925506, 'asd', 'fas', '折线图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"数据分析折线图"},"tooltip":{"trigger":"axis"},"legend":{"data":["数据系列"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":"category","boundaryGap":false,"data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"name":"数据系列","type":"line","data":[2,3,5]}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据集包含两个变量，\'x\' 和 \'y\'，分别代表横轴和纵轴的值。
- 通过折线图我们可以观察到，随着 \'x\' 值的增加，\'y\' 值也在增加，呈现出一种正相关的关系。
- 在 \'x\' = 1 时，\'y\' 的值为 2。
- 在 \'x\' = 2 时，\'y\' 的值为 3。
- 在 \'x\' = 4 时，\'y\' 的值为 5。
- 由于数据点较少，我们无法确定这种趋势是否持续或者是否存在其他潜在的模式。

请注意，由于数据量非常有限，以上结论可能不具备普遍性，需要更多的数据来验证。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 16:13:28', '2024-07-16 16:13:28', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813124917888909313, 'a', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，生成的折线图显示了一个简单的线性关系。数据点从(1, 2)开始，到(4, 5)结束，中间有一个点(2, 3)。从这些数据中可以观察到以下结论：

1. 数据点的分布呈现出随x轴增加而y轴值也增加的趋势。
2. 从点(1, 2)到点(2, 3)，每增加1个单位的x，y值增加1个单位。
3. 然而，从点(2, 3)到点(4, 5)，每增加2个单位的x，y值增加2个单位，保持了同样的增长斜率。
4. 由于数据点过少，无法确定是否存在某种周期性或非线性关系，需要更多的数据点来进行更深入的分析。

请注意，这些结论仅基于当前提供的数据，并且没有考虑到可能影响数据的外部因素。如果数据集更大或更复杂，分析可能会揭示更多的细节和模式。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 16:14:01', '2024-07-16 16:14:01', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813125368357158913, 'a', 'a', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据集包含三个数据点，分别对应于x轴的值1, 2, 和4，以及y轴的值2, 3, 和5。
- 从折线图可以看出，随着x轴值的增加，y轴值也在增加，表现出一种正相关关系。
- 在x=1时，y=2；在x=2时，y=3；在x=4时，y=5，这表明y值相对于x值的增长速率不是恒定的。
- 由于数据点过少，无法进行更深入的趋势分析或预测，但可以初步判断数据集呈现线性增长趋势。
- 如果有更多的数据点，我们可以进行线性回归分析，以获得更准确的增长模型。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 16:15:48', '2024-07-16 16:15:48', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813131535972139009, '1', '1', '折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，生成的折线图显示以下信息：

- 在x轴上，数据点为1, 2, 和4。
- 在y轴上，对应的值为2, 3, 和5。

数据分析结论：
- 数据点之间的趋势是上升的，从点(1, 2)到点(2, 3)再到点(4, 5)，每个点的y值都比前一个点大。
- 从点(2, 3)到点(4, 5)的增长斜率比从点(1, 2)到点(2, 3)的增长斜率要大，表明数据在后一阶段增长得更快。
- 由于数据点较少，且x轴的数据不连续，这个分析可能不全面，需要更多的数据点来得出更准确的结论。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-16 16:40:19', '2024-07-16 16:40:19', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813466617647788034, 't', 'h', '折线图', 'x,y
1,2
2,3
4,5
', '{"animation":false,"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"data":[2,3,5],"type":"line"}]}', '根据提供的原始数据，我们可以看到x轴和y轴各有三个数据点。数据分析结论如下：

1. 数据整体呈现增长趋势，从点(1, 2)开始，到点(4, 5)结束。
2. 在x轴的第二个点(2)处，y轴的值从2增长到3，表明在该区间内有所增长。
3. 从点(2, 3)到点(4, 5)，数据增长速率似乎有所加快，因为同样的x轴距离，y轴的数值增加了2，而在第一段中只增加了1。
4. 由于数据点过少，且x轴的数据并不连续，无法进行更深入的趋势分析或预测。

请注意，以上结论基于非常有限的数据点，且未考虑任何实际背景或数据的潜在含义。在实际情况中，更多上下文信息和数据将是必要的。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-17 14:51:48', '2024-07-17 14:51:48', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813476711026454530, 's', 's', '基础折线图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"基础折线图"},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"data":[2,3,5],"type":\'line\'}]}', '根据提供的原始数据，生成的折线图显示了一个简单的线性关系。数据点包括(1, 2)，(2, 3)和(4, 5)。从这些数据点可以看出，随着x轴数值的增加，y轴的数值也在增加，呈现出正相关的趋势。具体来说，当x从1增加到2时，y从2增加到3；当x从2增加到4时，y从3增加到5。这个图表可以用来展示两个变量之间的基础关系，但结论的深度受限于数据的数量和范围。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-17 15:31:55', '2024-07-17 15:31:55', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813476940773650434, 'a', 's', '堆叠柱状图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"堆叠柱状图"},"tooltip":{"trigger":"axis","axisPointer":{"type":"shadow"}},"legend":{"data":["系列1","系列2"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"name":"系列1","type":"bar","stack":"总量","data":[2,3,null]},{"name":"系列2","type":"bar","stack":"总量","data":[null,null,5]}]}', '根据提供的原始数据，我们可以看到：

- 在x轴的"1"位置，系列1有2个单位的值。
- 在x轴的"2"位置，系列1有3个单位的值，系列2没有值。
- 在x轴的"4"位置，系列1没有值，系列2有5个单位的值。

由于数据点不完整，我们无法直接比较系列1和系列2在所有位置的总量。不过，我们可以得出以下结论：

- 在"1"和"2"位置，系列1有值，而系列2没有值。
- 在"4"位置，系列2有值，而系列1没有值。
- 数据可能缺少某些时间点或者类别的数据，这需要在实际应用中进一步的数据收集来完善。

请注意，由于原始数据只提供了三个数据点，并且不是每个系列在每个点都有值，因此生成的堆叠柱状图可能不会完全反映两个系列之间的直接对比。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-17 15:32:50', '2024-07-17 15:32:50', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813479882968854530, 'sfd', 'sfdfs', '平滑曲线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\',"smooth":true}]}', '根据提供的原始数据，我们可以看到x轴的数据点为1, 2, 4，对应的y轴数据为2, 3, 5。数据分析结论如下：

1. 数据整体呈现增长趋势，从点(1, 2)到点(4, 5)，y值随着x值的增加而增加。
2. 由于图表类型选择了平滑曲线图，我们可以观察到数据点之间通过平滑曲线连接，这有助于我们理解数据变化的连续性。
3. 在当前数据集中，点(2, 3)和点(4, 5)之间的斜率似乎比点(1, 2)和点(2, 3)之间的斜率要大，这可能表明随着x值的增加，y值的增长速度在加快。
4. 由于数据点较少，我们无法进行更深入的趋势分析或预测，需要更多的数据点来得出更准确的结论。

请注意，以上结论基于当前提供的数据，如果数据量增加或数据点更加密集，分析结论可能会更加准确和详细。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-17 15:44:31', '2024-07-17 15:44:31', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813480831028359170, 's', 's', '阶梯线图', 'x,y
1,2
2,3
4,5
', '{"animation":false,"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"data":[2,3,5],"type":\'line\',"step":\'start\'}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据呈现出阶梯状的增长趋势，其中x轴代表某个变量（例如时间、事件等），y轴代表另一个变量（例如数量、频率等）。
- 在x=1时，y的值为2。
- 在x=2时，y的值增加到3，表明有一个单位的增长。
- 在x=4时，y的值进一步增加到5，再次表明有一个单位的增长。
- 由于x轴的数据点不是连续的，这表明在x=1到x=2，以及x=2到x=4之间可能存在缺失的数据或者数据采集的间隔。
- 使用阶梯线图可以清晰地展示这种非连续增长的数据模式，有助于观察数据随时间或事件的变化趋势。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-17 15:48:17', '2024-07-17 15:48:17', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813484538751152130, 's', 's', '堆叠折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"legend":{"data":["系列1"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":\'category\',"boundaryGap":false,"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"name":\'系列1\',"type":\'line\',"stack":\'总量\',"data":[2,3,5]}]}', '根据提供的原始数据，我们可以看到x轴上有三个数据点（1, 2, 4），对应的y轴数据为（2, 3, 5）。数据分析结论如下：

1. 数据整体呈现增长趋势，从点(1, 2)到点(4, 5)，数值逐渐增大。
2. 在x轴=2的位置，y轴的值从2增长到3，增长率为50%。
3. 在x轴=4的位置，y轴的值从3增长到5，增长率为66.67%。
4. 由于数据点过少，无法进行更深入的趋势分析或预测。

需要注意的是，这些结论仅基于当前提供的数据点，如果数据量增加，分析结论可能会更加准确和详细。, name=null, tool_calls=null, tool_call_id=null), delta=null)', 2, 1809845829464084482, '2024-07-17 16:03:01', '2024-07-17 16:03:01', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813488192333897730, 'k', 'k', '阶梯瀑布图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\',"axisPointer":{"type":\'shadow\'}},"legend":{"data":[\'X\',\'Y\']},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"name":\'X\',"type":\'bar\',"stack":\'总量\',"data":[1,2,null]},{"name":\'Y\',"type":\'bar\',"stack":\'总量\',"data":[null,3,5]}]}', '根据提供的原始数据，我们可以看到X和Y在各自的点上有所增加。在点1，X的值为1，Y没有值；在点2，X的值增加到2，Y的值为3；在点4，X没有值，而Y的值增加到5。这个阶梯瀑布图显示了X和Y在不同点的变化情况。

数据分析结论：
1. 在点1，X开始于1，而Y尚未开始。
2. 在点2，X增长到2，Y从0增长到3。
3. 在点4，X没有变化，Y从上一个点增长到5。
4. 从整体趋势来看，Y的增长速度在观察的三个点中超过了X。
5. 由于数据点较少，我们无法确定X和Y之间是否存在某种关联或者周期性变化，需要更多的数据来进行深入分析。', 2, 1809845829464084482, '2024-07-17 16:17:32', '2024-07-17 16:17:32', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813567525249466370, 'sf', 'sfdf', '平滑曲线图', 'x,y
1,2
2,3
4,5
', '{"animation":true,"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"data":["2","3","5"],"type":"line","smooth":true}]}', '根据提供的原始数据，生成的平滑曲线图显示了一个简单的线性关系。数据点从(1, 2)开始，到(4, 5)结束，中间有一个点(2, 3)。从图表中可以观察到以下数据分析结论：

1. 数据呈现出一种递增的趋势，即随着x轴数值的增加，y轴的数值也在增加。
2. 由于使用了平滑曲线图，我们可以推测数据之间可能存在某种连续性关系，而非离散的跳跃。
3. 由于数据点较少（只有三个），很难做出更复杂的统计推断，如趋势线的方程或者未来值的预测。
4. 在当前数据集中，没有异常值或者离群点，所有数据点都相对接近趋势线。

需要注意的是，这些结论仅基于当前提供的数据点，如果数据量增加或者有其他相关数据，结论可能会发生变化。', 2, 1809845829464084482, '2024-07-17 21:32:47', '2024-07-17 21:32:47', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813769286719479810, 'ss', 'ss', '堆叠折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"legend":{"data":["系列1"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":\'category\',"boundaryGap":false,"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"name":\'系列1\',"type":\'line\',"stack":\'总量\',"data":[2,3,5]}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据集包含两个变量，\'x\' 和 \'y\'，分别代表横轴和纵轴的值。
- 横轴（x）的值分别为：1, 2, 4。
- 纵轴（y）的值分别为：2, 3, 5。
- 通过堆叠折线图，我们可以观察到随着横轴（x）值的增加，纵轴（y）的值也在增加。
- 数据点之间的连接线显示了变量 \'y\' 随 \'x\' 变化的趋势。
- 由于数据点较少，很难确定是否存在某种明确的模式或关系，需要更多的数据点来进行更准确的分析。', 2, 1809845829464084482, '2024-07-18 10:54:30', '2024-07-18 10:54:30', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813925771638300673, 'ss', 'ss', '基础折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":"axis"},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"data":[2,3,5],"type":"line"}]}', '根据提供的原始数据，生成的折线图显示以下趋势：

- 在x=1时，y的值为2。
- 在x=2时，y的值增加到3，表明有一个单位的增长。
- 在x=4时，y的值进一步增加到5，再次表明了增加的趋势。

由于数据点较少，且x轴的值不是连续的，这限制了我们可以得出的分析结论。不过，从现有数据来看，可以明确的是，随着x值的增加，y值也在增加，显示出一种正相关的关系。如果这个趋势持续下去，我们可以预测在未来的x值增加时，y值也会相应地增加。

需要注意的是，由于数据点的跳跃性，这个分析可能不包含所有相关信息，可能需要更多的数据点来验证这一趋势的准确性。', 2, 1809845829464084482, '2024-07-18 21:16:19', '2024-07-18 21:16:19', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813926043123015681, 'ss', 'ss', '基础折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":"axis"},"xAxis":{"type":"category","data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"data":[2,3,5],"type":"line"}]}', '根据提供的原始数据，生成的折线图显示以下趋势：

- 在x=1时，y的值为2。
- 在x=2时，y的值增加到3，表明有一个单位的增长。
- 在x=4时，y的值进一步增加到5，再次表明了增加的趋势。

由于数据点较少，且x轴的值不是连续的，这限制了进一步的统计分析。不过，从已有的数据来看，可以观察到y值随着x值的增加而增加，显示出一种正相关的关系。如果这个趋势在更多的数据点上得到证实，可以认为这是一个简单的线性关系。需要注意的是，由于数据点太少，这个结论并不具有统计意义，需要更多的数据来验证这一趋势。', 2, 1809845829464084482, '2024-07-18 21:17:24', '2024-07-18 21:17:24', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1813927307613421569, 's', 's', '基础折线图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"基础折线图"},"tooltip":{"trigger":"axis"},"legend":{"data":["数据系列"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":"category","boundaryGap":false,"data":["1","2","4"]},"yAxis":{"type":"value"},"series":[{"name":"数据系列","type":"line","stack":"总量","data":[2,3,5]}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

- 数据集包含两个变量：x 和 y。
- x 的取值分别为：1, 2, 4。
- y 的取值分别为：2, 3, 5。
- 通过观察折线图，我们可以发现随着 x 的增加，y 的值也在增加，但不是线性关系。
- 在 x = 2 时，y 的增长速度为 1（从 2 增加到 3），而在 x = 4 时，y 的增长速度为 2（从 3 增加到 5）。
- 由于数据点较少，我们无法确定这种趋势是否持续或具有统计意义，需要更多的数据点来进行更准确的分析。

请注意，以上结论基于非常有限的数据点，因此可能不具有代表性或广泛性。在实际应用中，我们通常需要更多的数据来进行深入分析。', 2, 1809845829464084482, '2024-07-18 21:22:25', '2024-07-18 21:22:25', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1814132401713807362, 's', 's', '区域面积图', 'x,y
1,2
2,3
4,5
', '{"title":{"text":"区域面积图"},"tooltip":{"trigger":\'axis\',"axisPointer":{"type":\'cross\'}},"xAxis":{"type":\'category\',"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"data":[2,3,5],"type":\'line\',"areaStyle":{}}]}', '根据提供的原始数据，生成的区域面积图显示数据点 (1, 2)，(2, 3)，和(4, 5)。从图表中可以观察到以下数据分析结论：

1. 数据在 x 轴上是离散的，并且有一定的间隔，特别是在从 x=2 到 x=4 的跳跃。
2. y 值随着 x 的增加而增加，但增长不是线性的，这可能表明数据之间存在某种非线性的关系或模式。
3. 面积图通过填充线条下的区域，强调了数据点的连续性，这在视觉上帮助理解数据的变化趋势。
4. 由于数据点较少，很难得出更深层次的结论，但可以初步推测 x=4 时，y 值的增长速度比 x=1 到 x=2 之间的增长速度要快。

需要注意的是，这些结论仅基于当前提供的数据点，如果有更多的数据或上下文信息，可以做出更准确的分析。此外，由于数据点的数量较少，任何趋势或模式的判断都应该谨慎对待。', 2, 1809845829464084482, '2024-07-19 10:57:24', '2024-07-19 10:57:24', 0);
INSERT INTO qianbot.chart (id, chartName, chartGoal, chartType, chartData, genData, genResult, chartStatus, userId, createTime, updateTime, isDelete) VALUES (1814204165307346945, 's', 's', '堆叠折线图', 'x,y
1,2
2,3
4,5
', '{"tooltip":{"trigger":\'axis\'},"legend":{"data":["系列1"]},"grid":{"left":"3%","right":"4%","bottom":"3%","containLabel":true},"toolbox":{"feature":{"saveAsImage":{}}},"xAxis":{"type":\'category\',"boundaryGap":false,"data":[\'1\',\'2\',\'4\']},"yAxis":{"type":\'value\'},"series":[{"name":\'系列1\',"type":\'line\',"stack":\'总量\',"data":[2,3,5]}]}', '根据提供的原始数据，我们可以得出以下数据分析结论：

1. 数据集包含两个变量：x 和 y。
2. 在堆叠折线图中，由于数据点较少（只有三个），难以进行深入的趋势分析，但可以看出随着 x 的增加，y 的值也在增加。
3. 在 x = 1 时，y 的值为 2。
4. 在 x = 2 时，y 的值增加到 3，增长了 50%。
5. 在 x = 4 时，y 的值进一步增加到 5，增长了 66.67%，但从 x = 2 到 x = 4 的增长速度低于从 x = 1 到 x = 2 的增长速度。
6. 由于数据点太少，我们无法确定这种趋势是否持续或具有统计意义。

需要注意的是，这样的分析结论非常有限，因为数据集非常小，且没有提供足够的信息来支持更复杂的分析。在实际应用中，更多的数据点将提供更准确的趋势分析。', 2, 1809845829464084482, '2024-07-19 15:42:33', '2024-07-19 15:42:33', 0);
