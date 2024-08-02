package com.test.jar.redis.poll;

import com.test.jar.redis.handler.RedisQueuePollThreadStartHandler;
import com.test.jar.redis.handler.RedisQueuePollThreadStopHandler;
import com.test.jar.redis.adapter.RedisQueueConsumerAdapter;
import com.test.jar.redis.constants.RedisQueuePollScriptConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 14:31
 * @Description:
 */
@Data
@Slf4j
public class RedisQueuePollThread extends Thread {

    private int waitTime = 1;
    private int pollSize = 50;
    private int sleepTime = 50;
    private BigDecimal targetProcessSecond = BigDecimal.valueOf(1.2);
    private String queueName;
    private Jedis jedis;
    private RedisQueuePollThreadStartHandler startHandler;
    private RedisQueuePollThreadStopHandler downHandler;
    private RedisQueueConsumerAdapter consumerAdaptor;

    public RedisQueuePollThread(String queueName, Jedis jedis, RedisQueuePollThreadStartHandler startHandler, RedisQueuePollThreadStopHandler downHandler, RedisQueueConsumerAdapter consumerAdaptor) {
        this.queueName = queueName;
        this.jedis = jedis;
        this.startHandler = startHandler;
        this.downHandler = downHandler;
        this.consumerAdaptor = consumerAdaptor;
    }

    @Override
    public void run() {
        startHandler.handle(queueName);
        int sleepTimeTotal = 0;
        while (true) {
            int sleepSecond = sleepTimeTotal / 1000;
            if (sleepSecond > waitTime) {
                log.info("{}消费线程超过{}s未消费到消息，退出消费", queueName, sleepSecond);
                downHandler.handle(queueName);
                jedis.close();
                return;
            }
            List<String> list = (List<String>) jedis.eval(RedisQueuePollScriptConstants.POLL_SCRIPT, Arrays.asList(queueName), Arrays.asList("0", String.valueOf(pollSize - 1)));
            if (CollectionUtils.isNotEmpty(list)) {
                long start = System.currentTimeMillis();
                consumerAdaptor.process(list);
                long processTime = System.currentTimeMillis() - start;
                int tmpPollSize = calculatePollSize(pollSize, processTime);
                log.info("{}-消费 {} 数据耗时 {} ms,拉取数量调整为:{}", queueName, pollSize, processTime, tmpPollSize);
                pollSize = tmpPollSize;
            } else {
                try {
                    Thread.sleep(sleepTime);
                    sleepTimeTotal += sleepTime;
                } catch (InterruptedException e) {
                    log.error("{}线程睡眠出错", Thread.currentThread().getName());
                }
            }
        }
    }

    public int calculatePollSize(int pollSize, long time) {
        BigDecimal second = BigDecimal.valueOf(time).divide(BigDecimal.valueOf(1000), 2, RoundingMode.UP);
        BigDecimal weight = targetProcessSecond.divide(second, 2, RoundingMode.UP);
        return Math.min(BigDecimal.valueOf(pollSize).multiply(weight).intValue(), 1000);
    }
}
