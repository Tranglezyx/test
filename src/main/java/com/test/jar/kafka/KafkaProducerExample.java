package com.test.jar.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class KafkaProducerExample {
    private static final KafkaProducer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<>(props);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<?> submit = executorService.submit(() -> send());
            list.add(submit);
        }
        long start = System.currentTimeMillis();
        producer.close();

        for (Future<?> future : list) {
            future.get();
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms");
    }

    public static void send() {
        String key = "my_key";
        String value = "INSERT INTO `sysOA`.`t_sms_to_20230925_0`(`id`, `batch_id`, `sms_id`, `developer_id`, `account_id`, `provider_message_id`, `target_number`, `provider`, `csp_id`, `csp_name`, `chatbot_id`, `chatbot_name`, `provider_chatbot_no`, `template_id`, `template_name`, `content`, `type`, `send_status`, `delay_send`, `delay_send_time`, `receive_id`, `conversation_id`, `session_id`, `sms_format`, `sms_type`, `chatbot_version`, `province`, `city`, `api_id`, `api_seq_no`, `resp_code`, `resp_desc`, `submit_type`, `submit_time`, `send_time`, `receipt_time`, `message_type`, `fall_back`, `yx_template_id`, `mms_template_id`, `sms_template_id`, `real_amount`, `refund_amount`, `pre_deduction_status`, `pre_deduction_amount`, `refund_bill_status`, `refund_bill_fail_msg`, `refund_time`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `param_list`, `hit_high_risk_word`, `audit_by`, `fail_msg`, `is_leakage`, `delivery_channel`, `text_channel_status`, `is_fallback`, `cost_type`) VALUES (1706147203867840514, '2023092500297052', '{\\\"smsId\\\":\\\"0925112232000101050290\\\",\\\"toPort\\\":\\\"\\\"}', 1996674469, 100520783735, '73ce6d04-85fe-4de8-bf1e-b1dd74d6444e', '18303447604', 'CMCC', 1663031202134065153, '旺立5G消息平台-福建泉州移动', 1570237685614772224, '旦米小店-自助点餐', '106922623006000', 1668903824239575042, '1570237685614772224', '我是cmpp测试短信内容tcegpal5', 'SEND', '4_FAIL', '0', NULL, NULL, '64bb4127-20b8-4d9f-84a5-49a822ce7bb6', '088f46d9-73e9-46ca-b612-f7492eda8550', 'RCS_FORMAT', 'TEXT', 'v1', '140000', '140700', 1570237685654077442, '10074', '4_FAIL', '操作成功', '3', '2023-09-25 11:22:38', '2023-09-25 11:22:59', '2023-09-25 11:22:59', 1, 3, NULL, NULL, 1668903824239575042, 0.0000, 2.1000, 'SUCCESS', 2.1000, 'SUCCESS', NULL, '2023-09-25 12:13:35', NULL, '1996674469', '2023-09-25 11:22:38', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SEND_ING_SENT');";
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < 500; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, key, value);
                producer.send(record);
                KafkaConstants.PRODUCER_NUM.addAndGet(1);
            }
            if (KafkaConstants.PRODUCER_NUM.get() == 2000) {
                producer.flush();
            }
        }
    }
}
