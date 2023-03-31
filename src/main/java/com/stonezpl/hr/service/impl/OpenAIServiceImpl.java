package com.stonezpl.hr.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.config.OpenAIProperties;
import com.stonezpl.hr.service.OpenAIService;
import com.stonezpl.hr.service.SensitiveWordService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stonezpl
 * @Description TODO
 * @date 2023/3/31 10:59
 */
@Service
@Slf4j
public class OpenAIServiceImpl implements OpenAIService {

    private static final String OPENAI_URL = "https://api.openai.com/v1/completions";
    @Resource
    private OpenAIProperties openAIProperties;

    @Resource
    private SensitiveWordService sensitiveWordService;

    @Override
    public String generateText(String prompt) {
        if (!sensitiveWordService.isTextValid(prompt)){
            return "系统可能开小差了,换个问题再试试吧";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("model", "text-davinci-003");
        params.put("prompt", prompt);
        params.put("max_tokens", openAIProperties.getMaxTokens());
        params.put("temperature", 0);

        HttpRequest httpRequest = HttpRequest.post(OPENAI_URL).setHttpProxy(openAIProperties.getProxyHost(),
                openAIProperties.getProxyPort()).header("Authorization", "Bearer " +
                "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").body(JSONUtil.toJsonStr(params));
        try (HttpResponse response = httpRequest.execute()) {
            String result = response.body();
            JSONObject jsonObject = JSONUtil.parseObj(result);
            String text = jsonObject.getJSONArray("choices").getJSONObject(0).getStr("text");
            List<String> sensitiveWords = SpringUtil.getBean(SensitiveWordService.class).validateText(text);
            if (sensitiveWords.size() > 0) {
                for (String sensitiveWord : sensitiveWords) {
                    text = StrUtil.replace(text, sensitiveWord, "****");
                }
            }
            return text;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "呜呜呜~~~,我不知道该说什么了";
    }
}
