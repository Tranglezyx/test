package com.test.limiter;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.UUID;

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
        if (count < configEnum.getBurst()) {
            zset.add(time, key);
            return true;
        } else {
            Double score = zset.firstScore();
            BigDecimal firstTime = new BigDecimal(score);
            BigDecimal nowTime = new BigDecimal(time);
            BigDecimal value = nowTime.subtract(firstTime).multiply(configEnum.getRate()).divide(new BigDecimal(1000),2, RoundingMode.HALF_UP);
            if (value.compareTo(BigDecimal.ONE) < 0) {
                return false;
            }
            for (int i = 0; i < value.intValue(); i++) {
                zset.pollFirst();
            }
            zset.add(time, key);
            return true;
        }
    }
}
