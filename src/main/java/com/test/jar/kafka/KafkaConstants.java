package com.test.jar.kafka;

import java.util.concurrent.atomic.AtomicLong;

public interface KafkaConstants {

    AtomicLong PRODUCER_NUM = new AtomicLong(0);
    AtomicLong CONSUMER_NUM = new AtomicLong(0);

    String TOPIC_NAME = "my_topic";
    int NUM_PARTITIONS = 1;
    short REPLICATION_FACTOR = 1;
    String BOOTSTRAP_SERVERS = "127.0.0.1:9092";

    String GROUP_ID = "my_group";
}
