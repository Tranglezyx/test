package com.test.db.clickhouse.entity;

import com.test.annotation.Table;
import com.test.db.clickhouse.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/4/8 16:19
 * @Description:
 */
@Data
@Table("t_sms_send")
public class SmsSend {


    /*
    建表SQL
    CREATE TABLE t_sms_send (
  id UInt64 COMMENT '主键id',
  business_type UInt8 DEFAULT NULL COMMENT '商业类型 1:普通短信 2:视频短信',
  sms_id UInt64 DEFAULT NULL COMMENT 'smsId',
  batch_id String DEFAULT NULL COMMENT '批次id',
  user_id UInt64 DEFAULT NULL COMMENT '用户id',
  user_sub_account_id UInt64 DEFAULT NULL COMMENT '用户子账户id',
  extension_code String DEFAULT NULL COMMENT '拓展码',
  target_number String DEFAULT NULL COMMENT '目标号码',
  operator_id UInt64 DEFAULT NULL COMMENT '运营商id',
  province_name String DEFAULT NULL COMMENT '省份名称',
  city_name String DEFAULT NULL COMMENT '市名称',
  channel_id UInt64 DEFAULT NULL COMMENT '通道id',
  send_status UInt8 DEFAULT 3 COMMENT '发送状态  1:成功  2:失败 3:未知 4:提交失败 5:平台拒绝 6:待补发 7:未知改成功 8:未知改失败',
  status_code String DEFAULT NULL COMMENT '状态码',
  splits_total Int32 DEFAULT NULL COMMENT '拆分总数',
  splits_current Int32 DEFAULT NULL COMMENT '拆分当前条数',
  sign_content String DEFAULT NULL COMMENT '签名内容',
  split_message String DEFAULT NULL COMMENT '拆分内容',
  is_billing Nullable(UInt8) DEFAULT NULL COMMENT '是否计费 0:不计费 1:计费',
  submit_time UInt64 DEFAULT NULL COMMENT '提交时间',
  send_time UInt64 DEFAULT NULL COMMENT '发送时间',
  receipt_time UInt64 DEFAULT NULL COMMENT '回执时间',
  submit_type UInt8 DEFAULT NULL COMMENT '提交方式 1.API发送，2.网页发送 3.cmpp 4.后台 ',
  user_msg_id String DEFAULT NULL COMMENT '用户msg_id',
  channel_msg_id String DEFAULT NULL COMMENT '通道msg_id',
  channel_access_code String DEFAULT NULL COMMENT '通道接入码',
  protocol_desc String DEFAULT NULL COMMENT '协议desc即解释',
  deliver_req String DEFAULT NULL COMMENT '回执具体信息',
  billing_type UInt8 DEFAULT NULL COMMENT '计费类型:1:提交成功计费，2：回执成功+未知计费 3：回执成功计费',
  is_fee_return Nullable(UInt8) DEFAULT NULL COMMENT '是否退费 1.返回费用 2.不返还 3.返还成功 4.返还失败',
  is_tariff_control UInt8 DEFAULT NULL COMMENT '是否资费 0:非资费 1:资费',
  success_rate_type UInt8 DEFAULT NULL COMMENT '成功率类型  0 按账号 / 1 按运营商',
  params String DEFAULT NULL COMMENT '变量',
  params_symbol String DEFAULT NULL COMMENT '变量符号',
  template_id UInt64 DEFAULT NULL COMMENT '模板id',
  original_channel_id UInt64 DEFAULT NULL COMMENT '原始通道id',
  outside_code String DEFAULT NULL COMMENT '外部编码',
  product_id UInt64 DEFAULT NULL COMMENT '产品id',
  audit_mode Nullable(UInt8) DEFAULT NULL COMMENT '审核的类型 1 人工 2 自动',
  audit_type Nullable(UInt8) DEFAULT NULL COMMENT '审核类型 1 单次免审 2 一日免审 3 永久免审',
  partner_id String DEFAULT NULL COMMENT '合伙人id',
  partner_bonus Nullable(Decimal(20, 4)) DEFAULT NULL COMMENT '分成单价',
  mobile_price Nullable(UInt64) DEFAULT NULL COMMENT '移动单价',
  telecom_price Nullable(UInt64) DEFAULT NULL COMMENT '电信单价',
  unicom_price Nullable(UInt64) DEFAULT NULL COMMENT '联通单价',
  refund_req String DEFAULT NULL COMMENT '退款请求码',
  refund_reps String DEFAULT NULL COMMENT '退款响应码',
  refund_date DateTime DEFAULT NULL COMMENT '退费时间',
  bill_status UInt8 NOT NULL DEFAULT 0 COMMENT '费用状态 根据回执计算是否需要收费/退费 0-未计算 1-已计算完成',
  bill_reps String DEFAULT NULL COMMENT '扣费流水号',
  update_bill_time DateTime DEFAULT NULL COMMENT '更新费用状态时间',
  user_source_no String DEFAULT NULL COMMENT '用户源扩展号',
  is_leakage Int32 DEFAULT 0 COMMENT '是否渗漏 1是 0否',
  is_through_leakage Int32 DEFAULT 0 COMMENT '是否经过渗漏服务 1是 0否',
  time_flag UInt8 DEFAULT 0 COMMENT '是否定时短信',
  retry_channel_list_str String DEFAULT NULL COMMENT '补发通道 顺序:移动、联通、电信 结构:[[],[]]',
  location_bo_list_str String DEFAULT NULL COMMENT '通道路径字符串',
  history_channel_id_set String DEFAULT NULL COMMENT '历史通道ID路径',
  repeat_channel_id UInt64 DEFAULT NULL COMMENT '重复通道ID'
) ENGINE = MergeTree()
ORDER BY id;
    */

    /**
     * 主键id
     */
    private Long id;

    /**
     * 短信类型 1:普通短信 2:视频短信
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /**
     * 批次id
     */
    @TableField("batch_id")
    private String batchId;

    /**
     * 短信标记Id
     */
    @TableField("sms_id")
    private Long smsId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户子账户id
     */
    @TableField("user_sub_account_id")
    private Long userSubAccountId;

    /**
     * 拓展码
     */
    @TableField("extension_code")
    private String extensionCode;

    /**
     * 目标号码
     */
    @TableField("target_number")
    private String targetNumber;

    /**
     * 运营商id
     */
    @TableField("operator_id")
    private Long operatorId;

    /**
     * 省份名称
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 市名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 通道id
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 原始通道
     */
    private Long originalChannelId;

    /**
     * 发送状态  1:成功  2:失败 3:未知 4:提交失败 5:平台拒绝 6:待补发 7:未知改成功 8:未知改失败
     */
    @TableField("send_status")
    private Integer sendStatus;

    /**
     * 状态码
     */
    @TableField("status_code")
    private String statusCode;

    /**
     * 拆分总数
     */
    @TableField("splits_total")
    private Integer splitsTotal;

    /**
     * 拆分当前条数
     */
    @TableField("splits_current")
    private Integer splitsCurrent;

    /**
     * 签名内容
     */
    @TableField("sign_content")
    private String signContent;

    /**
     * 拆分内容
     */
    @TableField("split_message")
    private String splitMessage;

    /**
     * 是否计费 0:不计费 1:计费
     */
    @TableField("is_billing")
    private Integer isBilling;

    /**
     * 提交时间
     */
    @TableField("submit_time")
    private Long submitTime;

    /**
     * 发送时间
     */
    @TableField("send_time")
    private Long sendTime;

    /**
     * 回执时间
     */
    @TableField("receipt_time")
    private Long receiptTime;

    /**
     * 提交方式 1.API发送，2.网页发送 3.cmpp 4.后台
     */
    @TableField("submit_type")
    private Integer submitType;

    /**
     * 用户msg_id
     */
    @TableField("user_msg_id")
    private String userMsgId;

    /**
     * 通道msg_id
     */
    @TableField("channel_msg_id")
    private String channelMsgId;

    /**
     * 通道接入码
     */
    @TableField("channel_access_code")
    private String channelAccessCode;

    /**
     * 协议desc即解释
     */
    @TableField(value = "protocol_desc")
    private String protocolDesc;
    /**
     * 回执具体信息
     */
    @TableField(value = "deliver_req")
    private String deliverReq;

    /**
     * 计费类型:1:提交成功计费，2：回执成功+未知计费 3：回执成功计费
     */
    @TableField(value = "billing_type")
    private Integer billingType;

    /**
     * 是否退费:  1:返回费用，2：不返还，3：返还成功 4: 返还失败
     */
    @TableField(value = "is_fee_return")
    private Integer isFeeReturn;

    /**
     * 是否资费控制 0:否 1:是
     */
    @TableField(value = "is_tariff_control")
    private Integer isTariffControl;

    /**
     * 变量
     */
    @TableField(value = "params")
    private String params;

    /**
     * 变量符号
     */
    @TableField(value = "params_symbol")
    private String paramsSymbol;

    /**
     * 模板id
     */
    @TableField(value = "template_id")
    private Long templateId;

    /**
     * 模板外部编码
     */
    @TableField(value = "outside_code")
    private String outsideCode;

    /**
     * 成功率类型  0 按账号 / 1 按运营商
     */
    @TableField(value = "success_rate_type")
    private Integer successRateType;

    /**
     * 用户源扩展号
     */
    @TableField(value = "user_source_no")
    private String userSourceNo;

    /**
     * 是否渗漏号码  1.是 0.否
     */
    @TableField(value = "is_leakage")
    private Integer isLeakage;

    /**
     * 具体产品ID(关联product_catalogue表)
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 审核的类型 1 人工 2 自动
     */
    @TableField("audit_mode")
    private Integer auditMode;

    /**
     * 审核类型 1 单次免审 2 一日免审 3 永久免审
     */
    @TableField("audit_type")
    private Integer auditType;

    /**
     * 移动单价，万分单位
     */
    @TableField("mobile_price")
    private Long mobilePrice;

    /**
     * 联通单价，万分单位
     */
    @TableField("unicom_price")
    private Long unicomPrice;

    /**
     * 电信单价，万分单位
     */
    @TableField("telecom_price")
    private Long telecomPrice;

    /**
     * 退款请求码
     */
    @TableField("refund_req")
    private String refundReq;

    /**
     * 退款响应码
     */
    @TableField("refund_reps")
    private String refundReps;

    /**
     * 退款时间
     */
    @TableField("refund_date")
    private Date refundDate;

    /**
     * 合伙人id
     */
    private String partnerId;

    /**
     * 分成单价
     */
    private BigDecimal partnerBonus;

    /**
     * '费用状态 根据回执计算是否需要收费/退费 0-未计算 1-已计算完成'
     */
    @TableField("bill_status")
    private Integer billStatus;

    /**
     * 扣费流水
     */
    @TableField("bill_reps")
    private String billReps;

    /**
     * 更新费用状态时间
     */
    @TableField("update_bill_time")
    private Date updateBillTime;

    /**
     * 是否经过渗漏 经过渗漏并不表示这个号码被标记渗漏，只是具备可以渗漏的条件
     * 1.是 0.否
     */
    @TableField("is_through_leakage")
    private Integer isThroughLeakage;

    /**
     * 通道集合
     */
    @TableField("location_bo_list_str")
    private String locationBoListStr;

    /**
     * 补发通道
     */
    @TableField("retry_channel_list_str")
    private String retryChannelListStr;
}
