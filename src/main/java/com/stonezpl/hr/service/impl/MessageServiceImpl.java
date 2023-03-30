package com.stonezpl.hr.service.impl;

import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.constants.RedisConstants;
import com.stonezpl.hr.service.IMessageService;
import com.stonezpl.hr.task.RedisDelayTask;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
