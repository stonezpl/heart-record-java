package com.stonezpl.hr.task;

/**
 * @author stonezpl
 * @Description Redis延迟队列
 * @date 2023/3/14 17:52
 */

public interface RedisDelayTask {
    /**
     *  任务ID
     * @return
     */
    String getId();

    /**
     *  队列中的值
     * @return
     */
    String getValue();

    /**
     *  延迟时间（单位：s）
     * @return
     */
    long getDelayTime();

    /**
     * 消息类型
     */
    String getTaskType();

    /**
     *  任务执行
     */
    void execute();
}
