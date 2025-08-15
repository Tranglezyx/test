package com.company.dm.es;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/7/28 10:49
 * @Description:
 */
@Data
@Builder
public class SmsExcelDTO {

    @ExcelProperty("用户ID")
    private String userId;

    @ExcelProperty("子账户ID")
    private String userSubAccountId;

    @ExcelProperty("扩展码")
    private String extensionCode;

    @ExcelProperty("目标号码")
    private String targetNumber;

    @ExcelProperty("发送状态")
    private String sendStatus;

    @ExcelProperty("提交时间")
    private String submitTime;

    @ExcelProperty("签名")
    private String signContent;

    @ExcelProperty("短信内容")
    private String messageContent;
}
