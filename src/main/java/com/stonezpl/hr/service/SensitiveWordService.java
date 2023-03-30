package com.stonezpl.hr.service;

import java.util.List;

/**
 * @author zhangpeilei
 * @Description 敏感词服务
 * @date 2023/3/30 16:29
 */
public interface SensitiveWordService {

    /**
     * 初始化本地缓存
     */
    void initLocalCache();

    /**
     * 获得文本所包含的不合法的敏感词数组
     *
     * @param text 文本
     * @param tags 标签数组
     * @return 不合法的敏感词数组
     */
    List<String> validateText(String text, List<String> tags);

    /**
     * 获得文本所包含的不合法的敏感词数组
     *
     * @param text 文本
     * @return 不合法的敏感词数组
     */
    List<String> validateText(String text);

    /**
     * 判断文本是否包含敏感词
     *
     * @param text 文本
     * @param tags 表述数组
     * @return 是否包含
     */
    boolean isTextValid(String text, List<String> tags);

    /**
     * 判断文本是否包含敏感词
     *
     * @param text 文本
     * @return 是否包含
     */
    boolean isTextValid(String text);
}
