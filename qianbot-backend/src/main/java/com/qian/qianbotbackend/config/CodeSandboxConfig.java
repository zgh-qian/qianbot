package com.qian.qianbotbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "codesandbox")
public class CodeSandboxConfig {
    private String type;
}
