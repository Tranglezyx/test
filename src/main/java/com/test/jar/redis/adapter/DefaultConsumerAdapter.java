package com.test.jar.redis.adapter;

import cn.hutool.core.util.RandomUtil;
import com.test.jar.redis.consumer.MessageConsumer;

import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:58
 * @Description:
 */
public class DefaultConsumerAdapter implements RedisQueueConsumerAdapter {

    private MessageConsumer messageConsumer = new MessageConsumer();

    @Override
    public void process(List<String> value) {
        messageConsumer.consume(value);
    }
}
