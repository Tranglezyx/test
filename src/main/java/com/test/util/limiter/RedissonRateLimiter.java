package com.test.util.limiter;

import lombok.AllArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

@AllArgsConstructor
public class RedissonRateLimiter implements Limiter {

    private LimiterConfigEnum configEnum;
    private RedissonClient client;

    @Override
    public boolean pass() {
        RRateLimiter rateLimiter = client.getRateLimiter(configEnum.name());
        rateLimiter.trySetRate(RateType.OVERALL, 1, 1, RateIntervalUnit.MINUTES);
        return rateLimiter.tryAcquire();
    }
}
