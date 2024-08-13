package com.company.dm.kafka;

import lombok.Data;

/**
 * @DEPICT 补发通道 顺序:移动、电信、联通 参数
 * @Author Jw
 * @DATA 2022年08月15日-15:08
 * @VERSION 1.0
 **/
@Data
public class SmsAuditRetryChannelParamVo {

    /**
     * 移动通道id
     */
    private Long mobileChannelId;

    /**
     * 联通通道id
     */
    private Long unicomChannelId;

    /**
     * 电信通道id
     */
    private Long telecomChannelId;

}
