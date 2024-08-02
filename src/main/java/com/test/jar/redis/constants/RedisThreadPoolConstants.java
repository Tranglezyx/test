package com.test.jar.redis.constants;

import com.test.jar.redis.handler.RedisQueuePollRejectHandler;

import java.util.concurrent.*;

public interface RedisThreadPoolConstants {

    ThreadPoolExecutor queueManagerExecutor = new ThreadPoolExecutor(2,
            5,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            Executors.defaultThreadFactory(),
            new RedisQueuePollRejectHandler());

    ThreadPoolExecutor queuePollExecutor = new ThreadPoolExecutor(30,
            200,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            Executors.defaultThreadFactory(),
            new RedisQueuePollRejectHandler());
}
