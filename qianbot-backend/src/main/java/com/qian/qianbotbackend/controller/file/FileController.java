package com.qian.qianbotbackend.controller.file;

import cn.hutool.core.io.FileUtil;
import com.qian.qianbotbackend.annotation.AuthCheck;
import com.qian.qianbotbackend.common.BaseContext;
import com.qian.qianbotbackend.common.BaseResponse;
import com.qian.qianbotbackend.common.ErrorCode;
import com.qian.qianbotbackend.common.ResultUtils;
import com.qian.qianbotbackend.config.CosClientConfig;
import com.qian.qianbotbackend.constant.UserConstant;
import com.qian.qianbotbackend.enums.FileUploadBizEnum;
import com.qian.qianbotbackend.exception.BusinessException;
import com.qian.qianbotbackend.exception.ThrowUtils;
import com.qian.qianbotbackend.manager.CosManager;
import com.qian.qianbotbackend.model.file.FileUploadRequest;
import com.qian.qianbotbackend.model.user.dto.userusage.UserUsageDTO;
import com.qian.qianbotbackend.service.UserUsageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;

import static com.qian.qianbotbackend.enums.user.UserUsageEnum.USER_USAGE_ENUM_COS;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Resource
    private CosManager cosManager;

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private UserUsageService userUsageService;

    /**
     * 文件上传
     *
     * @param multipartFile     文件
     * @param uploadFileRequest 文件上传请求
     * @return 返回文件访问地址
     */
    @PostMapping("/upload")
    @AuthCheck(mustRole = UserConstant.ROLE_USER)
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile, FileUploadRequest uploadFileRequest) {
        // 校验是否存在上传次数
        ThrowUtils.throwIf(
                !userUsageService.subUserUsage(new UserUsageDTO(null, USER_USAGE_ENUM_COS.getText(), null)),
                ErrorCode.OPERATION_ERROR,
                "文件上传次数已用完");
        String biz = uploadFileRequest.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        validFile(multipartFile, fileUploadBizEnum);
        // 文件目录：根据业务、用户来划分
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        String filepath = String.format("/%s/%s/%s", fileUploadBizEnum.getValue(), BaseContext.getUserId(), filename);
        File file = null;
        try {
            // 上传文件
            file = File.createTempFile(filepath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filepath, file);
            // 返回可访问地址
            return ResultUtils.success(cosClientConfig.getUrl() + filepath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filepath);
                }
            }
        }
    }

    /**
     * 校验文件
     *
     * @param multipartFile     文件
     * @param fileUploadBizEnum 业务类型
     */
    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过 1M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件类型错误，只能是 jpeg/jpg/svg/png/webp");
            }
        }
    }
}
