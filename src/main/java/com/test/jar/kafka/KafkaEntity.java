package com.test.jar.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/7/26 14:32
 * @Description:
 */
@Data
@AllArgsConstructor
public class KafkaEntity {

    private Integer index;
    private String value;
}
