package com.test.jar.redis.holder;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:39
 * @Description:
 */
@Slf4j
public class RedisPollThreadHolder {

    public static final ConcurrentHashMap HOLDER = new ConcurrentHashMap<String, Integer>(1024);

    public static boolean isExists(String queueName) {
        return HOLDER.containsKey(queueName);
    }

    public static void downLine(String queueName) {
        HOLDER.remove(queueName);
    }

    public static void topLine(String queueName) {
        HOLDER.put(queueName, 1);
    }
}
