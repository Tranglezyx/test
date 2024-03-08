package com.test.jar.kafka;

public class KafkaSpeedExample {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            long producerStart = KafkaConstants.PRODUCER_NUM.get();
            long consumerStart = KafkaConstants.CONSUMER_NUM.get();
            Thread.sleep(1000);
            long producerEnd = KafkaConstants.PRODUCER_NUM.get();
            long consumerEnd = KafkaConstants.CONSUMER_NUM.get();
            System.out.println("kafka每秒生产速度:" + (producerEnd - producerStart) + " 消费速度:" + (consumerEnd - consumerStart));
        }
    }
}
