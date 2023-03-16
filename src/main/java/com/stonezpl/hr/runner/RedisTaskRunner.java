package com.stonezpl.hr.runner;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import com.stonezpl.hr.constants.RedisConstants;
import com.stonezpl.hr.constants.RedisTaskEnum;
import com.stonezpl.hr.task.SubscribeTask;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zhangpeilei
 * @Description TODO
 * @date 2023/3/14 19:38
 */
@Component
@Slf4j
public class RedisTaskRunner implements ApplicationRunner {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        new Thread(() -> {
            while (true) {
                try {
                    execTask();
                    ThreadUtil.sleep(1000);
                } catch (Exception e) {
                    log.error("执行任务失败");
                    log.error(e.getMessage(), e);
                    ThreadUtil.sleep(10000);
                }
            }
        }).start();
    }

    private void execTask() {
        long currentTimeMillis = System.currentTimeMillis();
        Set<String> messages = stringRedisTemplate.opsForZSet().rangeByScore(RedisConstants.DELAY_TASK_KEY, 0,
                currentTimeMillis);
        if (CollUtil.isNotEmpty(messages)) {
            for (String message : messages) {
                String taskType = JSONUtil.parseObj(message).getStr("taskType");
                RedisTaskEnum redisTaskEnum = RedisTaskEnum.getByName(taskType);
                JSONUtil.toBean(message, redisTaskEnum.getC()).execute();
            }
            stringRedisTemplate.opsForZSet().removeRangeByScore(RedisConstants.DELAY_TASK_KEY, 0, currentTimeMillis);
        }

    }
}
