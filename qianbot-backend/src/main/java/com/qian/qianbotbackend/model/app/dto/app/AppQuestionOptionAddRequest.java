package com.qian.qianbotbackend.model.app.dto.app;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AppQuestionOptionAddRequest implements Serializable {
    private Long appId;

    private List<AppQuestionOptionDTO> appQuestionAndOptionList;

    private static final long serialVersionUID = 1L;

    @Data
    public static class AppQuestionOptionDTO {
        /**
         * 题目图片
         */
        private String questionPic;

        /**
         * 题目
         */
        private String questionName;

        /**
         * 选项列表
         */
        private List<AppQuestionOptionDTO.AppOptionDTO> optionList;

        @Data
        public static class AppOptionDTO {
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
