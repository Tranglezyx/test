package com.company.dm.kafka;

import java.util.Properties;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/12 15:11
 * @Description:
 */
public class JavaKafkaConfigurer {

    private static Properties properties;

    public synchronized static Properties getKafkaProperties() {
        if (null != properties) {
            return properties;
        }
        //获取配置文件kafka.properties的内容
        Properties kafkaProperties = new Properties();
        try {
            kafkaProperties.load(KafkaProducerDemo.class.getClassLoader().getResourceAsStream("kafka.properties"));
        } catch (Exception e) {
            //没加载到文件，程序要考虑退出
            e.printStackTrace();
        }
        properties = kafkaProperties;
        return kafkaProperties;
    }
}
