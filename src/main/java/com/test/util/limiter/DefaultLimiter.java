package com.test.util.limiter;

import lombok.AllArgsConstructor;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * 令牌桶限流器
 */
@AllArgsConstructor
public class DefaultLimiter implements Limiter {

    private LimiterConfigEnum configEnum;
    private RedissonClient client;

    @Override
    public boolean pass() {
        String name = configEnum.name();
        RScoredSortedSet<Object> zset = client.getScoredSortedSet(name);
        long count = zset.stream().count();
        long time = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        String key = UUID.randomUUID().toString();
        if (count >= configEnum.getBurst()) {
            Double score = zset.firstScore();
            BigDecimal firstTime = new BigDecimal(score);
            BigDecimal nowTime = new BigDecimal(time);
            // 当前时间距离第一个令牌调用时间间隔产生的令牌数量 = 时间间隔 * 令牌产生速率 / 1000
            BigDecimal value = nowTime.subtract(firstTime).multiply(configEnum.getRate()).divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP);
            if (value.compareTo(BigDecimal.ONE) < 0) {
                return false;
            }
            for (int i = 0; i < Math.min(value.intValue(), configEnum.getBurst()); i++) {
                zset.pollFirst();
            }
        }
        zset.add(time, key);
        return true;
    }
}
