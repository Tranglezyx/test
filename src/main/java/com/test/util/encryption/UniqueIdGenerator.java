package com.test.util.encryption;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
public class UniqueIdGenerator {
    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();
        String format = String.format("%x", currentTimeMillis);
        log.info("{}",format + RandomUtils.nextInt(10000,99999));
    }
}
