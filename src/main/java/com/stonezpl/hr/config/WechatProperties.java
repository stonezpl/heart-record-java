package com.stonezpl.hr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 小程序接口参数
 *
 * @author stonezpl
 */
@ConfigurationProperties(prefix = "wx")
@Data
@Component
public class WechatProperties {

    private String appId;
    private String appSecret;
    private String grantType;
}
