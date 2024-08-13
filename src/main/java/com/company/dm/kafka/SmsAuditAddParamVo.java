package com.company.dm.kafka;

import lombok.Data;

/**
 * @DEPICT 短信审核添加参数
 * @Author Jw
 * @DATA 2022年08月15日-15:08
 * @VERSION 1.0
 **/
@Data
public class SmsAuditAddParamVo {

    /**
     * 短信批次id
     */
    private String smsId;

    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 主账号id
     */
    private Long developerId;

    /**
     * 子账户id
     */
    private Long accountId;

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

    /**
     * 待发送时间
     */
    private Long sendTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 高危词
     */
    private String highRiskWords;

    /**
     * 短信内容
     */
    private String smsContent;


    /**
     * 具体数据
     */
    private MessageDto data;

    /**
     * 具体产品id
     */
    private Long productId;

    /**
     * 签名id
     */
    private Long signId;

    /**
     * 移动号码数量
     */
    private Long mobileAmount;

    /**
     * 联通号码数量
     */
    private Long unicomAmount;

    /**
     * 电信号码数量
     */
    private Long telecomAmount;

    /**
     * 发送类型 1.API发送，2.网页发送 3.cmpp
     */
    private Integer sendBy;


    /**
     * 匹配通道类型   1、关键字路由
     */
    private Integer matchChannelType;

}
