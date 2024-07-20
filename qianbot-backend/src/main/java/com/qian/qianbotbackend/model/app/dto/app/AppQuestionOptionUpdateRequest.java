package com.qian.qianbotbackend.model.app.dto.app;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AppQuestionOptionUpdateRequest implements Serializable {
    /**
     * 应用ID
     */
    private Long appId;

    private List<AppQuestionOptionUpdateDTO> appQuestionAndOptionList;

    private static final long serialVersionUID = 1L;

    @Data
    public static class AppQuestionOptionUpdateDTO {
        /**
         * 问题
         */
        private AppQuestionUpdateDTO question;

        /**
         * 选项列表
         */
        private List<AppOptionUpdateDTO> optionList;

        @Data
        public static class AppQuestionUpdateDTO {
            /**
             * 题目ID
             */
            private Long id;

            /**
             * 题目图片
             */
            private String questionPic;

            /**
             * 题目
             */
            private String questionName;
        }

        @Data
        public static class AppOptionUpdateDTO {
            /**
             * 选项ID
             */
            private Long id;

            /**
             * 选项图片
             */
            private String optionPic;

            /**
             * 选项键
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
}