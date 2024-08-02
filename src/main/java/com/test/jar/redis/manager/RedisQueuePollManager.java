package com.test.jar.redis.manager;

import com.test.jar.redis.adapter.DefaultConsumerAdapter;
import com.test.jar.redis.constants.RedisQueueConstants;
import com.test.jar.redis.constants.RedisThreadPoolConstants;
import com.test.jar.redis.handler.HandlerAbstractFactory;
import com.test.jar.redis.holder.RedisPollThreadHolder;
import com.test.jar.redis.poll.RedisQueuePollThread;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 14:36
 * @Description:
 */
@Slf4j
public class RedisQueuePollManager extends Thread {

    private Jedis jedis;

    public RedisQueuePollManager(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (true) {
            scan(RedisQueueConstants.QUEUE_NAME_PATTERN, 100);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("redis消费管理线程睡眠失败，error:", e);
            }
            long now = System.currentTimeMillis();
            if ((now - start) / 1000 > 5) {
                log.info("redis poll线程池当前执行任务数量:{},等待数量:{}", RedisThreadPoolConstants.queuePollExecutor.getActiveCount(), RedisThreadPoolConstants.queuePollExecutor.getQueue().size());
                start = now;
            }
        }
    }

    public void scan(String pattern, int size) {
        ScanParams scanParams = new ScanParams().match(pattern).count(size);
        // 初始游标
        String cursor = "0";
        do {
            // 执行 SCAN 命令
            ScanResult<String> scanResult = jedis.scan(cursor, scanParams);

            // 获取新的游标
            cursor = scanResult.getCursor();

            // 获取匹配的键列表
            for (String queueName : scanResult.getResult()) {
                if (RedisPollThreadHolder.isExists(queueName)) {
                    continue;
                }
                RedisQueuePollThread redisQueuePollThread = new RedisQueuePollThread(queueName,
                        new Jedis("192.168.11.131", 30479,
                                DefaultJedisClientConfig.builder().password("LqYX8XMEMWYN2GTCgcvQ")
                                        .database(3)
                                        .build()),
                        HandlerAbstractFactory.defaultThreadStartHandler(),
                        HandlerAbstractFactory.defaultThreadStopHandler(),
                        new DefaultConsumerAdapter());
                redisQueuePollThread.setName(queueName);
                RedisThreadPoolConstants.queuePollExecutor.submit(redisQueuePollThread);
                log.info("{}消费线程启动--", queueName);
            }
        } while (!"0".equals(cursor));  // 当游标为 "0" 时，表示扫描结束
    }
}
