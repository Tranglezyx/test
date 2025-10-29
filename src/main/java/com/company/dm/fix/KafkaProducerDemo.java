package com.company.dm.fix;

import com.company.dm.kafka.JavaKafkaConfigurer;
import com.google.common.collect.Lists;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class KafkaProducerDemo {
    public static void main(String args[]) throws Exception {

        String drDTODataPath = "C:\\Users\\Trangle\\Downloads\\drDTO_test.txt";

        List<String> strings = Files.readAllLines(Paths.get(drDTODataPath));

        //加载kafka.properties
        Properties kafkaProperties =  JavaKafkaConfigurer.getKafkaProperties();

        Properties props = new Properties();
        //设置接入点，请通过控制台获取对应Topic的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty("bootstrap.servers"));

        //Kafka消息的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //请求的最长等待时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 30 * 1000);
        //设置客户端内部重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        //设置客户端内部重试间隔
        props.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 3000);
        // 关闭幂等
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);
        //构造Producer对象，注意，该对象是线程安全的，一般来说，一个进程内一个Producer对象即可；
        //如果想提高性能，可以多构造几个对象，但不要太多，最好不要超过5个
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //构造一个Kafka消息
        String topic = kafkaProperties.getProperty("topic"); //消息所属的Topic，请在控制台申请之后，填写在这里

        List<List<String>> groupMessageList = Lists.partition(strings, 200);
        int total = 0;
        for (List<String> stringList : groupMessageList) {
            try {
                List<Future<RecordMetadata>> resultFutureList = Lists.newArrayList();
                for (String string : stringList) {
                    total++;
                    ProducerRecord<String, String> kafkaMessage =  new ProducerRecord<String, String>(topic, string);
                    Future<RecordMetadata> result = producer.send(kafkaMessage);
                    resultFutureList.add(result);
                }
                for (Future<RecordMetadata> recordMetadataFuture : resultFutureList) {
                    RecordMetadata recordMetadata = recordMetadataFuture.get(1, TimeUnit.SECONDS);
                    System.out.println("res:" + recordMetadata.toString() + ",已推送：" + total);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        for (String string : strings) {
//            total++;
//            try {
//                ProducerRecord<String, String> kafkaMessage =  new ProducerRecord<String, String>(topic, string);
//                RecordMetadata result = producer.send(kafkaMessage).get(1, TimeUnit.SECONDS);
//                System.out.println("res:" + result.toString() + ",已推送：" + total);
//                TimeUnit.MILLISECONDS.sleep(5);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
