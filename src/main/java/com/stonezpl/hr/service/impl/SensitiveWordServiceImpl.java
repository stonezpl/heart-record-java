package com.stonezpl.hr.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.stonezpl.hr.service.SensitiveWordService;
import com.stonezpl.hr.util.SimpleTrie;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author zhangpeilei
 * @Description TODO
 * @date 2023/3/30 16:32
 */
@Service
@Slf4j
public class SensitiveWordServiceImpl implements SensitiveWordService {

    /**
     * 默认的敏感词的字典树，包含所有敏感词
     */
    @Getter
    private volatile SimpleTrie sensitiveWordTrie = new SimpleTrie(Collections.emptySet());


    @Override
    @PostConstruct
    public void initLocalCache() {
        // 1：查询数据
         List<String> words = FileUtil.readLines(SpringUtil.getProperty("sensitive.path"), CharsetUtil.UTF_8);
        // 2：构建字典树
        this.sensitiveWordTrie = new SimpleTrie(words);
    }

    @Override
    public List<String> validateText(String text, List<String> tags) {
        return null;
    }

    @Override
    public List<String> validateText(String text) {
        return sensitiveWordTrie.validate(text);
    }

    @Override
    public boolean isTextValid(String text, List<String> tags) {
        return false;
    }

    @Override
    public boolean isTextValid(String text) {
        return false;
    }
}
