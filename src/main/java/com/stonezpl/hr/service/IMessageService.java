package com.stonezpl.hr.service;

import com.stonezpl.hr.task.RedisDelayTask;

/**
 * @author stonezpl
 * @Description 消息接口
 * @date 2023/3/14 17:57
 */
public interface IMessageService {

    /**
     * 发布延迟队列消息
     * @param redisTask
     */
    void sendDelayMessage(RedisDelayTask redisTask);
}
