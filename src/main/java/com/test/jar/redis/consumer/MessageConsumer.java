package com.test.jar.redis.consumer;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:04
 * @Description:
 */
@Slf4j
public class MessageConsumer {

    public void consume(List<String> list) {
//        log.info("消费消息,size:{}", list.size());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
