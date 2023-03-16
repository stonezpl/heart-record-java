package com.stonezpl.hr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * minio参数
 *
 * @author stonezpl
 */
@ConfigurationProperties(prefix = "minio")
@Data
@Component
public class MinioProperties {

    private String bucket;
    private String endpoint;
    private String accessKey;
    private String secretKey;

    private String domain;
}
