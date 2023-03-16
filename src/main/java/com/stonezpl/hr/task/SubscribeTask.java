package com.stonezpl.hr.task;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.ImmutableMap;
import com.stonezpl.hr.service.IWechatService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangpeilei
 * @Description 订阅任务
 * @date 2023/3/14 18:13
 */
@Slf4j
public class SubscribeTask extends AbstractRedisDelayTask {

    public SubscribeTask(String id, String value, String taskType, long delayTime) {
        super(id, value, taskType, delayTime);
    }

    @Override
    public void execute() {
        IWechatService wechatService = SpringUtil.getBean(IWechatService.class);
        String accessToken = wechatService.getAccessToken();
        JSONObject jsonObject = JSONUtil.parseObj(this.getValue());
        String openId = jsonObject.getStr("openId");
        String title = jsonObject.getStr("title");
        String content = jsonObject.getStr("content");
        Map<String, Map<String, Object>> param = new HashMap<>();
        param.put("thing5", ImmutableMap.of("value", title));
        param.put("thing2", ImmutableMap.of("value", content));
        wechatService.sendSubscribeMessage(accessToken,
                SpringUtil.getProperty("wx.templateId"),
                openId, param);
    }
}
