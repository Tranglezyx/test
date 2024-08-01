package com.test.jar.redis.adapter;

import com.test.jar.redis.consumer.MessageConsumer;

import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:58
 * @Description:
 */
public class DefaultConsumerAdapter implements RedisQueueConsumerAdapter {

    @Override
    public void process(List<String> value) {
        new MessageConsumer().consume(value);
    }
}
