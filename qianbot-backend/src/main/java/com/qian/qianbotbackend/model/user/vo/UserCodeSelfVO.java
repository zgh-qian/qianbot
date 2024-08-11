package com.qian.qianbotbackend.model.user.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.qian.qianbotbackend.model.user.domain.UserCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserCodeSelfVO implements Serializable {
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
     * 代码密码
     */
    private String codePwd;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 是否公开：0-私密，1-公开
     */
    private Integer isPublic;

    /**
     * 是否过期：0-未过期，1-已过期
     */
    private Integer isExpire;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static UserCodeSelfVO objToVO(UserCode userCode) {
        if (userCode == null) {
            return null;
        }
        UserCodeSelfVO userCodeSelfVO = BeanUtil.copyProperties(userCode, UserCodeSelfVO.class);
        String codeTags = userCode.getCodeTags();
        if (codeTags != null) {
            userCodeSelfVO.setCodeTags(JSONUtil.toList(codeTags, String.class));
        }
        return userCodeSelfVO;
    }
}