package com.company.dm.fix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/9/27 15:56
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrDTO {

    /**
     * 通道 ID
     */
    private String channelId;
    /**
     * 通道的接入码
     */
    private String accessCode;
    /**
     * 数据类别:
     * CmppDeliverRequestMessage
     * SMGPDeliverMessage
     */
    private Integer type;
    /**
     * 回执的msgId
     */
    private String msgId;
    /**
     * 发送回复的seqId
     */
    private String seqNo;
    /**
     * 号码
     */
    private String phone;

    /**
     * status 状态  1:成功  2:失败 3:未知 4:提交失败 5:平台拒绝 6:待补发
     */
    private Integer status;
    /**
     * 协议code
     */
    private String protocolCode;
    /**
     * 协议desc即解释(用户侧)
     */
    private String protocolDesc;
    /**
     * 协议desc即解释(通道侧)
     */
    private String channelProtocolDesc;
    /**
     * 回执具体信息
     */
    private String deliverReq;

    /**
     * 表名+smsId sms_to_20220818218049661966159872
     */
    private String toId;

    /**
     * 提交时间
     */
    private Long sendTime;
    /**
     * 回执时间
     */
    private Long reportTime;

    /**
     * 当前条数
     */
    private Integer pkNumber;

    /**
     * 总条数
     */
    private Integer pkTotal;

    /**
     * 匹配次数
     */
    private Integer count;


    private String sign;
    private String content;
    private Long accountId;
    private String smsId;
    /**
     * 扩展码
     */
    private String toPort;
    /**
     * 平台分配子码
     */
    private String platformCode;
    /**
     * 运营商id
     */
    private Long operatorId;
    /**
     * 是否签名发送测试
     */
    private Boolean isSignSendTest;
    /**
     * 批次id
     */
    private String batchId;
}
