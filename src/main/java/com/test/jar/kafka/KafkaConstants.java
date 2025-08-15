package com.test.jar.kafka;

import java.util.concurrent.atomic.AtomicLong;

public interface KafkaConstants {

    AtomicLong PRODUCER_NUM = new AtomicLong(0);
    AtomicLong CONSUMER_NUM = new AtomicLong(0);

    String TOPIC_NAME = "test";
    int NUM_PARTITIONS = 1;
    short REPLICATION_FACTOR = 1;
    String BOOTSTRAP_SERVERS = "192.168.11.131:31092";

    String GROUP_ID = "my_group";
}
