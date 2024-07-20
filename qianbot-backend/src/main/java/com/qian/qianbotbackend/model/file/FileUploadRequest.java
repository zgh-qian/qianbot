package com.qian.qianbotbackend.model.file;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUploadRequest implements Serializable {
    /**
     * 业务
     */
    private String biz;

    private static final long serialVersionUID = 1L;
}
