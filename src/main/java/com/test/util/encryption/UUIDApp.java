package com.test.util.encryption;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class UUIDApp {

    public static void main(String[] args) {
        log.info(UUID.randomUUID().toString());
        log.info(UUID.randomUUID().toString());
        log.info(UUID.randomUUID().toString());
        log.info(UUID.randomUUID().toString());
        log.info(UUID.randomUUID().toString());
        log.info(UUID.randomUUID().toString());
    }
}
