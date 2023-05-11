package com.test.limiter;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Slf4j
public class LimitApp {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

//        Limiter defaultLimiter = new DefaultLimiter(LimiterConfigEnum.REPORT, client);
        Limiter defaultLimiter = new RedissonRateLimiter(LimiterConfigEnum.REPORT, client);
        for (int i = 0; i < 100; i++) {
            boolean pass = defaultLimiter.pass();
            Thread.sleep(1000);
            System.out.println("第 " + (i + 1) + " 次请求，结果: " + pass);
        }
    }
}
