package com.test.jar.redis.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 14:26
 * @Description:
 */
@Slf4j
public class RedisQueuePollRejectHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        log.info("redis队列拉取拒绝策略执行---");
    }
}
