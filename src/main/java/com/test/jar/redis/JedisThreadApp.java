package com.test.jar.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 17:41
 * @Description:
 */
@Slf4j
public class JedisThreadApp {

    public static final Jedis jedis = new Jedis("192.168.11.131", 30479,
            DefaultJedisClientConfig.builder().password("LqYX8XMEMWYN2GTCgcvQ")
                    .database(3)
                    .build());

    public static final String script = "local result = redis.call('ZRANGE', KEYS[1], ARGV[1], ARGV[2]); " +
            "if #result > 0 then " +
            "redis.call('ZREM', KEYS[1], unpack(result)); " +
            "end; " +
            "return result;";

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
//        Executors.newFixedThreadPool(1).submit(() -> pollAll("queue:0", 10, jedis));
//        Executors.newFixedThreadPool(1).submit(() -> pollAll("queue:1", 10, jedis));

        Executors.newFixedThreadPool(1).submit(() -> pollAll("queue:0", 10, jedisPool));
        Executors.newFixedThreadPool(1).submit(() -> pollAll("queue:1", 10, jedisPool));
    }

    public static void pollAll(String queueName, int size, JedisPool jedis) {
        while (true) {
            List<String> list = (List<String>) jedis.getResource().eval(script, Arrays.asList(queueName), Arrays.asList("0", String.valueOf(size - 1)));
            if (CollectionUtils.isEmpty(list)) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                log.info("拉取数量:{}", list.size());
            }
        }
    }

    public static void pollAll(String queueName, int size, Jedis jedis) {
        while (true) {
            List<String> list = (List<String>) jedis.eval(script, Arrays.asList(queueName), Arrays.asList("0", String.valueOf(size - 1)));
            if (CollectionUtils.isEmpty(list)) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                log.info("拉取数量:{}", list.size());
            }
        }
    }
}
