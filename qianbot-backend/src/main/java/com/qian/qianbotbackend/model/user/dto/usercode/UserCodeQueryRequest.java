package com.qian.qianbotbackend.model.user.dto.usercode;

import com.qian.qianbotbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCodeQueryRequest extends PageRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String codeTitle;

    /**
     * 语言
     */
    private String codeLanguage;

    /**
     * 代码
     */
    private String codeContent;

    /**
     * 代码类型，比如：算法、前端、后端
     */
    private String codeType;

    /**
     * 代码标签(json数组)
     */
    private List<String> codeTags;

    /**
     * 代码描述
     */
    private String codeDesc;

    /**
     * 创建用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}