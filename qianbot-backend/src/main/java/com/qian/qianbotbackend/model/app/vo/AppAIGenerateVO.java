package com.qian.qianbotbackend.model.app.vo;

import lombok.Data;

import java.util.List;

@Data
public class AppAIGenerateVO {
    /**
     * 题目
     */
    private String questionName;

    /**
     * 选项列表
     */
    private List<AppOptionDTO> optionList;

    @Data
    public static class AppOptionDTO {
        /**
         * key
         */
        private String optionKey;

        /**
         * 选项
         */
        private String optionName;

        /**
         * 选项结果
         */
        private String optionResult;
    }
}
