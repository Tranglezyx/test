package com.company.dm.kafka;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/12 15:12
 * @Description:
 */
@Slf4j
public class KafkaProducerDemo {

    public static void main(String args[]) throws Exception {

        List<String> strings = Files.readAllLines(Paths.get("D:\\result2.txt"));

        //加载kafka.properties
        Properties kafkaProperties = JavaKafkaConfigurer.getKafkaProperties();

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

        List<String> message = Lists.newArrayList();
        int total = 0;
        for (String string : strings) {
            total++;
            try {
                SmsAuditAddParamVo smsAuditAddParamVo = JSONObject.parseObject(string, SmsAuditAddParamVo.class);
                String smsContent = smsAuditAddParamVo.getSmsContent();
                int priceNum = calculateSmsCount(smsContent);
                for (int i = 1; i <= priceNum; i++) {
                    DrDTO drDTO = new DrDTO();
                    drDTO.setChannelId("871");
                    drDTO.setAccountId(smsAuditAddParamVo.getAccountId());
                    drDTO.setDeliverReq("审核错误修复数据");
                    drDTO.setPhone(smsAuditAddParamVo.getData().getPhone());
                    drDTO.setPkNumber(i);
                    drDTO.setPkTotal(priceNum);
                    drDTO.setProtocolCode("098");
                    drDTO.setProtocolDesc("098");
                    drDTO.setReportTime(System.currentTimeMillis());
                    drDTO.setSendTime(smsAuditAddParamVo.getSendTime());
                    drDTO.setSmsId(smsAuditAddParamVo.getData().getSmsId());
                    drDTO.setStatus(2);

                    ProducerRecord<String, String> kafkaMessage = new ProducerRecord<String, String>(topic, JSONObject.toJSONString(drDTO));
                    RecordMetadata result = producer.send(kafkaMessage).get(1, TimeUnit.SECONDS);
                }

                log.info("已推送，推送进度:{}", total);

                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计费条数
     *
     * @param finalSms 短信内容
     * @return 条数
     */
    public static int calculateSmsCount(String finalSms) {
        int smsCount = 1;
        // 70个字符以内算一条，超过70个字符按照67个字符为一条算
        if (finalSms.length() > 70) {
            if (finalSms.length() % 67 == 0) {
                smsCount = finalSms.length() / 67;
            } else {
                smsCount = finalSms.length() / 67 + 1;
            }
        }
        return smsCount;
    }
}
