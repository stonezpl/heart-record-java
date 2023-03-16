package com.stonezpl.hr.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.constants.RedisConstants;
import com.stonezpl.hr.task.RedisDelayTask;
import com.stonezpl.hr.task.SubscribeTask;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author stonezpl
 * @Description TODO
 * @date 2023/3/14 18:06
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendDelayMessage(RedisDelayTask redisTask) {
        stringRedisTemplate.opsForZSet().add(RedisConstants.DELAY_TASK_KEY, JSONUtil.toJsonStr(redisTask), redisTask.getDelayTime());
    }
}
