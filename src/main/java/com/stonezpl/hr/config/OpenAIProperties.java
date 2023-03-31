package com.stonezpl.hr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * openai配置的一些参数
 *
 * @author stonezpl
 */
@ConfigurationProperties(prefix = "openai")
@Data
@Component
public class OpenAIProperties {
    private int maxTokens;
    private String proxyHost;
    private int proxyPort;
}
