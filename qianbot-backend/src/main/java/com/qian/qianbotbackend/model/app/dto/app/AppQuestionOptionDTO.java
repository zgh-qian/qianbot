package com.qian.qianbotbackend.model.app.dto.app;

import lombok.Data;

import java.util.List;

@Data
public class AppQuestionOptionDTO {
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
         * 选项
         */
        private String optionName;

        /**
         * 选项结果
         */
        private String optionResult;
    }
}
