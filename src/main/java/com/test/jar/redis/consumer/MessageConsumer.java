package com.test.jar.redis.consumer;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/1 15:04
 * @Description:
 */
@Slf4j
public class MessageConsumer {

    private int speed = RandomUtil.randomInt(200, 500);

    public void consume(List<String> list) {
        int size = list.size();
        log.info("业务处理消息,size:{},speed:{}", size, speed);
        try {
            int sleepTime = BigDecimal.valueOf(size).divide(BigDecimal.valueOf(speed), 2, RoundingMode.UP).multiply(BigDecimal.valueOf(1000)).intValue();
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
