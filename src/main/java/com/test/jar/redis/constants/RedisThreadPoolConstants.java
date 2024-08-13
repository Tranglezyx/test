package com.test.jar.redis.constants;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.test.jar.redis.handler.RedisQueuePollRejectHandler;

import java.util.concurrent.*;

public interface RedisThreadPoolConstants {

    ThreadPoolExecutor queueManagerExecutor = new ThreadPoolExecutor(1,
            5,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            ThreadFactoryBuilder.create().setNamePrefix("redis-queue-manager-").build(),
            new RedisQueuePollRejectHandler());

    ThreadPoolExecutor queuePollExecutor = new ThreadPoolExecutor(30,
            300,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            ThreadFactoryBuilder.create().setNamePrefix("redis-pool-manager-").build(),
            new RedisQueuePollRejectHandler());
}
