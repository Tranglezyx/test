package com.test.domain.entity;

import cn.hutool.core.util.IdUtil;
import com.test.annotation.Table;
import lombok.Data;

@Data
@Table(value = "t_sms_to")
public class SmsTo {

    private Long id;
    private String batchId;
    private Long targetNumber;
    private String content;

    public static SmsTo buildRandomSmsTo() {
        SmsTo smsTo = new SmsTo();
        smsTo.setId(IdUtil.getSnowflakeNextId());
        smsTo.setBatchId(IdUtil.nanoId());
        smsTo.setTargetNumber(IdUtil.getSnowflakeNextId());
        smsTo.setContent(IdUtil.fastSimpleUUID());
        return smsTo;
    }
}
