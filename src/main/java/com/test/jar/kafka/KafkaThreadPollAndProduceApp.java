package com.test.jar.kafka;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/7/26 14:00
 * @Description:
 */
@Slf4j
public class KafkaThreadPollAndProduceApp {

    private static final String topicName = "zyx-kafka";
    private static final KafkaProducer<String, String> producer;
    private static final Properties props = new Properties();

    private static final int maxPartition = 10;

    public static final Executor executor = Executors.newFixedThreadPool(20);

    static {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.11.131:31092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        producer = new KafkaProducer<>(props);

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my_group");
    }

    public static void main(String[] args) {
        batchConsumer(maxPartition);
        executor.execute(KafkaThreadPollAndProduceApp::sendInput);
    }

    public static void batchConsumer(int maxPartition){
        for (int i = 0; i < maxPartition; i++) {
            int finalI = i;
            executor.execute(() -> consumer(finalI));
        }
    }

    public static void consumer(int index) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.assign(Arrays.asList(new TopicPartition(topicName, index)));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                log.info("kafka消费,topic:{},partition:{},offset:{},key:{},value:{}", record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }

    public static void sendInput() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String value = scanner.nextLine();
            int index = RandomUtil.randomInt(maxPartition);
            send(index, 1, value);
        }
    }

    public static void send(int index, int size, String value) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            KafkaEntity kafkaEntity = new KafkaEntity(index, value);
            list.add(JSONObject.toJSONString(kafkaEntity));
        }
        for (int i = 0; i < size; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, index, "", list.get(i));
            log.info("kafka发送,data={}", producerRecord);
            producer.send(producerRecord);
            if (size % 200 == 0) {
                producer.flush();
            }
        }
        producer.flush();
    }
}
