package com.test.jar.redis.constants;

import com.test.jar.redis.handler.RedisQueuePollRejectHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
            new ArrayBlockingQueue<>(1),
            Executors.defaultThreadFactory(),
            new RedisQueuePollRejectHandler());
}
