package com.test.jar.redis.handler;

public interface RedisQueuePollThreadStartHandler {

    void handle(String queueName);
}
