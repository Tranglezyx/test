package com.test.jar.redis.adapter;

import java.util.List;

public interface RedisQueueConsumerAdapter {

    void process(List<String> value);
}
