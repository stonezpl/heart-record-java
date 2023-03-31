package com.stonezpl.hr.service;

import java.util.List;

/**
 * @author stonezpl
 * @Description 操作openai的接口
 * @date 2023/3/31 10:57
 */
public interface OpenAIService {

    /**
     * 生成文本
     *
     * @param text 文本
     * @return 生成的文本
     */
    String generateText(String text);
}
