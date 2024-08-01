package com.test.jar.redis.handler;

public interface RedisQueuePollThreadStopHandler {

    void handle(String queueName);
}
