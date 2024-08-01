package com.test.jar.redis;

import com.test.jar.redis.constants.RedisThreadPoolConstants;
import com.test.jar.redis.manager.RedisQueuePollManager;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 16:33
 * @Description:
 */
public class RedisQueueApp {

    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128); // 设置最大连接数
        poolConfig.setMaxIdle(128); // 设置最大空闲连接数
        poolConfig.setMinIdle(16); // 设置最小空闲连接数
        poolConfig.setTestOnBorrow(true); // 获取连接时测试连接可用性
        poolConfig.setTestOnReturn(true); // 归还连接时测试连接可用性
        poolConfig.setTestWhileIdle(true); // 空闲时测试连接可用性
        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞
        poolConfig.setMaxWaitMillis(2000); // 获取连接的最大等待时间

        String redisHost = "192.168.11.131"; // Redis 服务器地址
        int redisPort = 30479; // Redis 服务器端口
        int timeout = 2000; // 连接超时时间
        String password = "LqYX8XMEMWYN2GTCgcvQ"; // Redis 密码，如果没有设置密码则为 null

        jedisPool = new JedisPool(poolConfig, redisHost, redisPort, timeout, password, 3);
    }

    public static void main(String[] args) {
        RedisQueuePollManager redisQueuePollManager = new RedisQueuePollManager(new Jedis("192.168.11.131", 30479,
                DefaultJedisClientConfig.builder().password("LqYX8XMEMWYN2GTCgcvQ")
                        .database(3)
                        .build()));
        RedisThreadPoolConstants.queueManagerExecutor.submit(redisQueuePollManager);
    }
}
