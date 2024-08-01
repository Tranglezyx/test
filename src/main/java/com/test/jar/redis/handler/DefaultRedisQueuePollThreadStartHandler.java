package com.test.jar.redis.handler;

import com.test.jar.redis.holder.RedisPollThreadHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:54
 * @Description:
 */
@Slf4j
public class DefaultRedisQueuePollThreadStartHandler implements RedisQueuePollThreadStartHandler {

    @Override
    public void handle(String queueName) {
        RedisPollThreadHolder.topLine(queueName);
    }
}
