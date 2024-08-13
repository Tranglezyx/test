package com.company.dm.kafka;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @DEPICT
 * @Author Jw
 * @DATA 2021年10月20日-14:56
 * @VERSION 1.0
 **/
@Data
public class MessageDto {

    private String smsId;

    /**
     * 批次id
     */
    private String batchId;

    /**
     * 计费条数
     */
    private Integer billingCount;

    /**
     * 是否是待发送短信
     */
    private boolean toBeSent;

    /**
     * 开发者ID
     */
    private Long developerId;

    /**
     * 短信内容
     */
    private String smsContent;

    /**
     * 审核修改短信内容
     */
    private String auditModifyContent;

    /**
     * 子账号ID
     */
    private Long accountId;

    /**
     * 扩展子码，用户扩展
     * 请使用  String 的 expandId
     */
    private String spreadNo;
    /**
     * 根据前缀码截取后的用户扩展码
     */
    private String splitSpreadNo;

    /**
     * 签码配对扩展码
     */
    private String signPairNo;

    /**
     * 开发者扩展ID
     */
    private String expandId;

    /**
     * 发送方式，1.API发送，2.网页发送 3.cmpp
     */
    private Integer sendBy;

    /**
     * 发送时间
     */
    private Long sendTime;

    /**
     * 命中的高危词
     */
    private String highRiskWord;


    /**
     * 移动通道ID
     */
    private Long mobileChannelId;
    /**
     * 联通通道ID
     */
    private Long unicomChannelId;

    /**
     * 电信通道ID
     */
    private Long telecomChanelId;

    /**
     * 短信签名
     */
    private String sign;

    /**
     * 短信签名id
     */
    private Long signId;

    /**
     * 计费方式 1 按提交 2 按成功未知 3 按成功
     */
    private Integer billingType;

    /**
     * 运营商Id
     */
    private Long supplierId;


    /**
     * 批次状态,平台拒绝，取消，待审核，审核驳回，待发送，发送中，发送完成
     */
    private String status;


    /**
     * 0.表示提交失败
     * 1.表示正常数据（或平台拒绝）
     */
    private String submitFail;

    /**
     * 平台拒绝原因
     */
    private ApiCodeMessageEnum apiCodeMessage;

    /**
     * 短信类型 1:普通短信 2:视频短信
     */
    private Integer businessType;

    /**
     * 模板变量
     */
    private String params;

    /**
     * 模板变量符号
     */
    private String paramsSymbol;

    /**
     * 模板外部编码
     */
    private String outsideCode;

    /**
     * 内部模板ID
     */
    private Long templateId;

    /**
     * 视频模板ID
     */
    private String vmsTemplateId;

    /**
     * 视频标题
     */
    private String vmTitle;

    /**
     * 成功率类型  0 按账号 / 1 按运营商
     */
    private Integer successRateType;

    /**
     * 具体产品ID(关联product_catalogue表)
     */
    private Long productCatalogueId;

    /**
     * 审核的类型 1 人工 2 自动
     */
    private Integer auditMode;

    /**
     * 审核类型 1 单次免审 2 一日免审 3 永久免审 4 免审核
     */
    private Integer auditType;

    /**
     * 移动单价，万分单位
     */
    private Long mobilePrice;
    /**
     * 联通单价，万分单位
     */
    private Long unicomPrice;
    /**
     * 电信单价，万分单位
     */
    private Long telecomPrice;

    /**
     * 合伙人id
     */
    private String partnerId;

    /**
     * 分成单价
     */
    private BigDecimal partnerBonus;

    /**
     * 短信单个号码数据
     */
    private List<MessageToDto> messageTos;

    /**
     * 发送时间
     */
    private Long time;

    /**
     * 自定义用户数据
     */
    private String userData;
    /**
     * 补发通道 顺序:移动、电信、联通 结构:[[],[]]
     */
    private List<SmsAuditRetryChannelParamVo> retryChannelList;

    private Set<String> contentSet;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 单条计费
     */
    private Integer fee;
    /**
     * 计费总额
     */
    private Integer amount;
    /**
     * 电话号码类型 1移动 2电信 3联通
     */
    private Integer operatorType;

    /**
     * 单号码计费条数
     */
    private Integer splitCount;

    /**
     * 开发者短信ID
     */
    private String developerSmsId;

    /**
     * 如失败，失败结果码
     */
    private String respCode;

    /**
     * 失败 结果描述
     */
    private String respMsg;

    /**
     * 是否资费  1.是 0.否
     */
    private Integer isDeduction;

    /**
     * 省份名称
     */
    private String provinceName;


    /**
     * 市名称
     */
    private String cityName;


    /**
     * 省份id
     */
    private String provinceId;


    /**
     * 市id
     */
    private String cityId;

    /**
     * 是否渗漏号码  1.是 0.否
     */
    private Integer isLeakage;

    /**
     * 是否经过渗漏 经过渗漏并不表示这个号码被标记渗漏，只是具备可以渗漏的条件
     * 1.是 0.否
     */
    private Integer isThroughLeakage;

    /**
     * 费用状态
     */
    private Integer billStatus;

    /**
     * 扣费流水
     */
    private String billReps;

    /**
     * 退费流水号
     */
    private String refundReps;

    /**
     * 退费时间
     */
    private Date refundDate;

    /**
     * 计费更新时间
     */
    private Date updateBillDate;

    /**
     * 是否退费 1.返回费用 2.不返还 3.返还成功 4.返还失败
     */
    private Integer isFeeReturn;

    private List<Long> idList;

    /**
     * 是否定时短信
     */
    private Integer timeFlag;

    /**
     * 发送优先级
     */
    private Integer sendLevel;

    public List<MessageToDto> getMessageTos() {
        if (messageTos == null) {
            return new CopyOnWriteArrayList<>();
        }
        return messageTos;
    }

    /**
     * 匹配通道类型   1、关键字路由
     */
    private Integer matchChannelType;
}