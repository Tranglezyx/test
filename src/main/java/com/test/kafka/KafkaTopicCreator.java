package com.test.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTopicCreator {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_SERVERS);

        AdminClient client = AdminClient.create(props);

        NewTopic newTopic = new NewTopic(KafkaConstants.TOPIC_NAME, KafkaConstants.NUM_PARTITIONS, KafkaConstants.REPLICATION_FACTOR);

        try {
            client.createTopics(Collections.singleton(newTopic)).all().get();
            System.out.println("Topic created successfully.");
        } catch (InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof TopicExistsException) {
                System.out.println("Topic already exists.");
            } else {
                e.printStackTrace();
            }
        } finally {
            client.close();
        }
    }
}
