package com.test.entity;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.test.annotation.Table;
import lombok.Data;

import java.util.Date;

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
