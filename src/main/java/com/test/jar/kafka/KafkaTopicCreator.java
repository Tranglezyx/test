package com.test.jar.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class KafkaTopicCreator {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.11.131:31092");

        AdminClient client = AdminClient.create(props);

        NewTopic newTopic = new NewTopic("zyx-kafka", 10, KafkaConstants.REPLICATION_FACTOR);

        try {
            client.createTopics(Collections.singleton(newTopic)).all().get();
            System.out.println("Topic created successfully.");
        } catch (Exception e) {
            log.error("error = ", e);
        } finally {
            client.close();
        }
    }
}
