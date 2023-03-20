package com.stonezpl.hr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 发送邮件配置的一些参数
 *
 * @author stonezpl
 */
@ConfigurationProperties(prefix = "mail")
@Data
@Component
public class MailProperties {

    private String from;
    private String host;
    private int port;
    private boolean sslEnable;
    private boolean auth;
    private String password;
}
