package com.company.dm.kafka;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @DEPICT
 * @Author Jw
 * @DATA 2021年10月19日-20:43
 * @VERSION 1.0
 **/
@Data
public class MessageToDto implements Serializable {

    private static final long serialVersionUID = 5693106512098172507L;
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
     * 短信ID
     */
    private String smsId;
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
     * '费用状态
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
     * '是否退费 1.返回费用 2.不返还 3.返还成功 4.返还失败
     */
    private Integer isFeeReturn;


    public Integer getIsDeduction() {

        return isDeduction == null ? 0 : isDeduction;
    }
}
