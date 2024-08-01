package com.test.jar.redis.handler;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:56
 * @Description:
 */
public abstract class HandlerAbstractFactory {

    private static final DefaultRedisQueuePollThreadStartHandler DEFAULT_REDIS_QUEUE_POLL_THREAD_START_HANDLER = new DefaultRedisQueuePollThreadStartHandler();
    private static final DefaultRedisQueuePollThreadStopHandler DEFAULT_REDIS_QUEUE_POLL_THREAD_STOP_HANDLER = new DefaultRedisQueuePollThreadStopHandler();

    public static DefaultRedisQueuePollThreadStartHandler defaultThreadStartHandler() {
        return DEFAULT_REDIS_QUEUE_POLL_THREAD_START_HANDLER;
    }

    public static DefaultRedisQueuePollThreadStopHandler defaultThreadStopHandler() {
        return DEFAULT_REDIS_QUEUE_POLL_THREAD_STOP_HANDLER;
    }
}
