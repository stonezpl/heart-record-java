package com.stonezpl.hr.util;

import cn.hutool.core.collection.CollUtil;

import java.util.*;

/**
 * @author stonezpl
 * @Description 基于前缀树，生成敏感词树
 * @date 2023/3/30 16:41
 */
public class SimpleTrie {

    /**
     * 一个敏感词结束后对应的 key
     */
    private static final Character CHARACTER_END = '\0';

    /**
     * 使用敏感词，构建的前缀树
     */
    private final Map<Character, Object> children;

    /**
     * 基于字符串，构建前缀树
     *
     * @param strs 字符串数组
     */
    public SimpleTrie(Collection<String> strs) {
        children = new HashMap<>();
        // 构建树
        CollUtil.sort(strs, String::compareTo); // 排序，优先使用较短的前缀
        for (String str : strs) {
            Map<Character, Object> child = children;
            // 遍历每个字符
            for (Character c : str.toCharArray()) {
                // 如果已经到达结束，就没必要在添加更长的敏感词。
                // 例如说，有两个敏感词是：吃饭啊、吃饭。输入一句话是 “我要吃饭啊”，则只要匹配到 “吃饭” 这个敏感词即可。
                if (child.containsKey(CHARACTER_END)) {
                    break;
                }
                if (!child.containsKey(c)) {
                    child.put(c, new HashMap<>());
                }
                child = (Map<Character, Object>) child.get(c);
            }
            // 结束
            child.put(CHARACTER_END, null);
        }
    }

    /**
     * 验证文本是否合法，即不包含敏感词
     *
     * @param text 文本
     * @return 是否 ok
     */
    public boolean isValid(String text) {
        // 遍历 text，使用每一个 [i, n) 段的字符串，使用 children 前缀树匹配，是否包含敏感词
        for (int i = 0; i < text.length() - 1; i++) {
            Map<Character, Object> child = (Map<Character, Object>) children.get(text.charAt(i));
            if (child == null) {
                continue;
            }
            boolean ok = recursion(text, i + 1, child);
            if (!ok) {
                return false;
            }
        }
        return true;
    }

    private boolean recursion(String text, int i, Map<Character, Object> child) {
        if (i >= text.length()) {
            return true;
        }
        Character c = text.charAt(i);
        if (child.containsKey(CHARACTER_END)) {
            return false;
        }
        if (child.containsKey(c)) {
            return recursion(text, i + 1, (Map<Character, Object>) child.get(c));
        }
        return true;
    }

    /**
     * 获得文本所包含的不合法的敏感词
     *
     * 注意，才当即最短匹配原则。例如说：当敏感词存在 “煞笔”，“煞笔二货 ”时，只会返回 “煞笔”。
     *
     * @param text 文本
     * @return 匹配的敏感词
     */
    public List<String> validate(String text) {
        Set<String> results = new HashSet<>();
        for (int i = 0; i < text.length() - 1; i++) {
            Character c = text.charAt(i);
            Map<Character, Object> child = (Map<Character, Object>) children.get(c);
            if (child == null) {
                continue;
            }
            StringBuilder result = new StringBuilder().append(c);
            boolean ok = recursionWithResult(text, i + 1, child, result);
            if (!ok) {
                results.add(result.toString());
            }
        }
        return new ArrayList<>(results);
    }

    private boolean recursionWithResult(String text, int i, Map<Character, Object> child, StringBuilder result) {
        if (i >= text.length()) {
            return true;
        }
        Character c = text.charAt(i);
        if (child.containsKey(c)) {
            result.append(c);
            child = (Map<Character, Object>) child.get(c);
            if (child.containsKey(CHARACTER_END)) {
                return false;
            }
            return recursionWithResult(text, i + 1, child, result);
        }
        return true;
    }
}
