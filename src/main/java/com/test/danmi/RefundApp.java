package com.test.danmi;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class RefundApp {

    public static void main(String[] args) throws IOException {
        File file = new File("5G退费成功修复数据-20231123.json");
        File sqlFile = new File("退费成功1123.sql");
        String fileToString = FileUtils.readFileToString(file, "utf-8");
        String[] split = fileToString.split("\n");
        String sql = "update t_sms_{}_{}_0 set refund_bill_status = 'SUCCESS', refund_time = now(), refund_amount = {},real_amount = pre_deduction_amount - {},remark = '退费数据修复' where batch_id = '{}' and target_number = '{}';";
        for (String string : split) {
            String[] info = string.split(",");
            String batchId = info[0];
            String phone = info[1];
            String amountStr = info[2];
            String toSql = StrUtil.format(sql, "to", "20231123", amountStr,amountStr, batchId, phone) + "\n";
            String refundSql = StrUtil.format(sql, "refund", "20231123", amountStr,amountStr, batchId, phone) + "\n";
            FileUtils.write(sqlFile, toSql, "utf-8", true);
            FileUtils.write(sqlFile, refundSql, "utf-8", true);
        }
    }
}
