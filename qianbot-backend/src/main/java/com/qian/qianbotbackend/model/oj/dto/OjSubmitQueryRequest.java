package com.qian.qianbotbackend.model.oj.dto;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class OjSubmitQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 运行结果，比如：AC
     */
    private String status;

    /**
     * 运行结果信息
     */
    private String detail;

    /**
     * 时间消耗(ms)
     */
    private Long timeUsed;

    /**
     * 内存消耗(kb)
     */
    private Long memoryUsed;

    /**
     * 判题状态：0-待判题，1-判题中，2-判题成功，3-判题失败
     */
    private Integer judgeStatus;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 创建用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}