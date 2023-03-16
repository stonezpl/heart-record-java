package com.stonezpl.hr.task;

/**
 * @author stonezpl
 * @Description 抽象类
 * @date 2023/3/14 18:11
 */
public abstract class AbstractRedisDelayTask implements RedisDelayTask {

    private String id;
    private String value;
    private String taskType;
    private long delayTime;

    public AbstractRedisDelayTask(String id, String value, String taskType,long delayTime) {
        this.id = id;
        this.value = value;
        this.taskType = taskType;
        this.delayTime = delayTime;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public String toString() {
        return "AbstractRedisDelayTask{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", taskType='" + taskType + '\'' +
                ", delayTime=" + delayTime +
                '}';
    }
}
