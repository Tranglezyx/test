package com.test.jar.kafka;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.test.domain.vo.CmppSmsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class KafkaTest {

    private static final AtomicLong num = new AtomicLong(0);

    public static void main(String[] args) {
//        KafkaTest.producer();
//        Executors.newFixedThreadPool(1)
//                .submit(() -> {
//                    while (true) {
//                        System.out.println(StrUtil.format("当前累计消费数量:{}", num));
//                        Thread.sleep(1000);
//                    }
//                });
        KafkaTest.consumer();
    }

    public static void consumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaConstants.BOOTSTRAP_SERVERS);
        properties.put("group.id", "captcha-slow-receipt-group");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
//        kafkaConsumer.subscribe(Arrays.asList(KafkaConstants.TOPIC_NAME));
        kafkaConsumer.subscribe(Arrays.asList("captcha_channel_slow_reply_smsg"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(StrUtil.format("time:{},partition:{},offset:{},value:{}", System.currentTimeMillis(), record.partition(), record.offset(), record.value()));
                    num.incrementAndGet();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void producer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaConstants.BOOTSTRAP_SERVERS);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        int size = 1000;
        try {
            long start = System.currentTimeMillis();
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < size; i++) {
                CmppSmsDto cmppSmsDto = new CmppSmsDto();
                cmppSmsDto.setCustomSmsId(IdUtil.nanoId(10));
                cmppSmsDto.setSendBy("1");
                cmppSmsDto.setDestId("13711111111");
                cmppSmsDto.setAccountId(100361913658L);
                cmppSmsDto.setBatchId("ia" + IdUtil.getSnowflakeNextId());
                cmppSmsDto.setMsgId(IdUtil.getSnowflakeNextIdStr());
                cmppSmsDto.setContent(StrUtil.format("【414153132】您的验证码是{}，请勿泄露。", RandomUtil.randomInt(100000,999999)));
                String msg = JSONObject.toJSONString(cmppSmsDto);

//                Future<RecordMetadata> send = producer.send(new ProducerRecord<String, String>("test", String.valueOf(i), msg));
                Future<RecordMetadata> send = producer.send(new ProducerRecord<String, String>(KafkaConstants.TOPIC_NAME, String.valueOf(i), msg));
//                RecordMetadata recordMetadata = send.get();
                System.out.println(StrUtil.format("发送进度:{},total:{}", i, size));
            }
            long end = System.currentTimeMillis();
            long speed = size / (end - start);
            System.out.println(StrUtil.format("发完完成，速率:{}/s，耗时:{}s", speed, (end - start) / 1000));
        } catch (Exception e) {
            log.error("error:", e);
        } finally {
            producer.close();
        }
    }
}
