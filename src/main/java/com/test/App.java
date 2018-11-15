package com.test;

import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.dto.User;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author Trangle Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException, ScriptException {
//        long startTime = System.currentTimeMillis();
//
//        String script = IOUtils.toString(Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("test.js"), "utf-8");
//        InputStreamReader babelReader = new InputStreamReader(Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("script/js/babel.min.js"), "utf-8");
//        System.out.println("构建解析babel.min.js输入流费时：" + (System.currentTimeMillis() - startTime));
//
//        ScriptEngine engine = new ScriptEngineManager().getEngineByMimeType("text/javascript");
//        System.out.println("构建js引擎费时：" + (System.currentTimeMillis() - startTime));
//        SimpleBindings bindings = new SimpleBindings();
//        engine.eval(babelReader, bindings);
//        System.out.println("js引擎解析babel.min.js费时：" + (System.currentTimeMillis() - startTime));
//        bindings.put("input", script);
//        Object output = engine.eval("Babel.transform(input, { presets: ['es2015', 'react', 'stage-0'] }).code", bindings);
//        System.out.println("js引擎解析代码费时：" + (System.currentTimeMillis() - startTime));
//        System.out.println(output);

//        producer();
        comsumer();
    }

    public static void comsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka.srm.lkk.com:9092");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("HelloWorld"));
//        while (true) {
        ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("offset = %d, value = %s", record.offset(), record.value());
            System.out.println();
        }
//        }
    }

    public static void producer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka.srm.lkk.com:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 100; i++) {
                String msg = "Message " + i;
                producer.send(new ProducerRecord<String, String>("HelloWorld", msg));
                System.out.println("Sent:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }
    }
}
