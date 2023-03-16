package com.stonezpl.hr.constants;

import com.stonezpl.hr.task.AbstractRedisDelayTask;
import com.stonezpl.hr.task.SubscribeTask;

/**
 * @author stonezpl
 * @Description TODO
 * @date 2023/3/15 17:13
 */
public enum RedisTaskEnum {
    subscribe("subscribe", "订阅消息", SubscribeTask.class);

    private String name;
    private String desc;

    private Class<? extends AbstractRedisDelayTask> c;

    RedisTaskEnum(String name, String desc, Class<? extends AbstractRedisDelayTask> c) {
        this.name = name;
        this.desc = desc;
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class<? extends AbstractRedisDelayTask> getC() {
        return c;
    }

    public void setC(Class<? extends AbstractRedisDelayTask> c) {
        this.c = c;
    }

    public static RedisTaskEnum getByName(String name) {
        for (RedisTaskEnum enumns : RedisTaskEnum.values()) {
            if (enumns.getName().equals(name)) {
                return enumns;
            }
        }

        return null;
    }
}
