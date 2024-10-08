# QianBot

> By：[zgh-qian](https://github.com/zgh-qian)
>
> 项目体验地址：https://zgh.icu
>
> 项目技术栈：Vue 3 + Spring Boot + Redis + ChatGLM AI + SSE + COS
[toc]

## 项目简介

### 项目介绍

当你探索网站时，你将发现一个丰富多样的**答题应用**，涵盖从MBTI性格测试到趣味性问答的各种选择。

不仅如此，**智能图表分析**功能允许你上传文件，通过AI技术进行深入分析，帮助你揭示数据背后的洞见和趋势。

无论你是寻找娱乐放松，还是需要数据驱动的决策支持，网站都能为你提供令人满意的解决方案。探索你的潜力，了解你的数据，尽在平台上。

[【速戳】快来试试答题应用！！！](https://zgh.icu/app)

[【速戳】快来试试图表分析！！！](https://zgh.icu/chart)

[欢迎访问GitHub仓库](https://github.com/zgh-qian)

### 核心业务流程图

#### 应用答题流程

![image-20240720150917133](README/image-20240720150917133.png)

#### 图表分析流程

![image-20240720151002338](README/image-20240720151002338.png)

## 页面说明

### 页面展示

首页

![image-20240720151205852](README/image-20240720151205852.png)

答题应用

![image-20240720151224671](README/image-20240720151224671.png)

图表分析

![image-20240720151245861](README/image-20240720151245861.png)

用户中心

![image-20240720154527359](README/image-20240720154527359.png)

答题历史

![image-20240720154626523](README/image-20240720154626523.png)

图表历史

![image-20240720154647435](README/image-20240720154647435.png)

我的应用

![image-20240720154702768](README/image-20240720154702768.png)

### 应用答题流程

点击感兴趣的应用

![image-20240720151655716](README/image-20240720151655716.png)

点击开始答题

![image-20240720151725556](README/image-20240720151725556.png)

最后点击查看结果，等待评分

![image-20240720151800014](README/image-20240720151800014.png)

评分完成后，跳转结果页面

![image-20240720151835605](README/image-20240720151835605.png)

### 创建应用流程

点击头像，点击我的应用

![image-20240720151928194](README/image-20240720151928194.png)

点击制作应用

![image-20240720151951275](README/image-20240720151951275.png)

输入应用信息

**应用类型**分为得分类和测评类

* 得分类就像考试做试卷，每个选项会有分数
* 测评类就像MBTI这种，每个选项对应了一种属性，比如INSJ

**评分策略**分为自定义和AI

* 自定义需要自己设置评分规则，设置评分规则是为得到最后的结果页面
* AI 就是直接让AI处理答题情况，如果设置了评分规则则不生效

![image-20240720152014806](README/image-20240720152014806.png)

设置题目和选项

题目

* 题目标题就是用户看见的题目
* 可以选择使用 AI 生成，帮你快速生成题目
  * 一键生成就是一次性返回直接生成对应数量的题目，等待时间比较长
  * 实时生成利用了 SSE ，可以实时生成一道一道题目，单次等待比较长

选项

* 选项Key就是选项前面的序号，比如 A B C D
* 选项内容就是用户看见的选项
* 选项结果就是得分或者属性（仅支持一个值，可以是字符串和数字）

![image-20240720152730491](README/image-20240720152730491.png)

![image-20240720152801239](README/image-20240720152801239.png)

![image-20240720153036688](README/image-20240720153036688.png)

设置评分

评分说明：

* 得分类应用：设置结果得分，比如只设置90，那么高于90的都是这个结果；如果设置了90和80的话，得分90结果就是90
* 测评类应用：一个规则可以设置多个属性

![image-20240720153248468](README/image-20240720153248468.png)

![image-20240720153257165](README/image-20240720153257165.png)

![image-20240720153321621](README/image-20240720153321621.png)



### 图表分析流程

点击上传文件

选择格式为xlsx或xls的文件

点击下一步

![image-20240720153815663](README/image-20240720153815663.png)

选择图表类型

![image-20240720153910608](README/image-20240720153910608.png)

填写图表信息

![image-20240720153941609](README/image-20240720153941609.png)

确认图表信息

![image-20240720153948881](README/image-20240720153948881.png)

点击生成图表

![image-20240720154023082](README/image-20240720154023082.png)

等待生成图表

![image-20240720154044193](README/image-20240720154044193.png)

生成图表成功

![image-20240720154444718](README/image-20240720154444718.png)