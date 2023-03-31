package com.stonezpl.hr.config;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.stonezpl.hr.constants.CommonConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * openai配置的一些参数
 *
 * @author stonezpl
 */
@ConfigurationProperties(prefix = "openai")
@Component
public class OpenAIProperties {

    private String apiKey;
    private int maxTokens;
    private String proxyHost;
    private int proxyPort;

    public String getApiKey() {
        byte[] key =
                SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), CommonConstants.SECURE_KEY.getBytes()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.decryptStr(this.apiKey, CharsetUtil.CHARSET_UTF_8);
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }
}
