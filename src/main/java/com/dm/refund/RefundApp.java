package com.dm.refund;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class RefundApp {

    public static void main(String[] args) throws IOException, ParseException {
        generateRefundCompareSql("20231219", "20231231");
    }

    private static void generateRefundCompareSql(String startStr, String endStr) throws IOException, ParseException {
        String sql = "select '{}' as date, (select count(0) from t_sms_to_{}_0 where pre_deduction_status = 'SUCCESS') as deductionNum," +
                "  (select count(0) from t_sms_to_{}_0 where pre_deduction_status = 'SUCCESS' and refund_bill_status in ('SUCCESS','FAILED')) as refundNum";

        Date start = DateUtils.parseDate(startStr, "yyyyMMdd");
        Date end = DateUtils.parseDate(endStr, "yyyyMMdd");
        StringBuilder sb = new StringBuilder();
        while (start.getTime() <= end.getTime()) {
            String date = DateFormatUtils.format(start, "yyyyMMdd");
            sb.append(StrUtil.format(sql, date, date, date));
            if (start.getTime() != end.getTime()) {
                sb.append(" \n union all \n");
            }
            start = DateUtils.addDays(start, 1);
        }
        String str = "select date,deductionNum,refundNum,(deductionNum = refundNum) from (" +
                "{}" +
                ") t";
        sql = StrUtil.format(str, sb.toString());
        File sqlFile = new File(startStr);
        FileUtils.write(sqlFile, sql, "utf-8", true);
    }


    private static void generateRefundFixSqlWithRefundInfo() throws IOException {
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
            String toSql = StrUtil.format(sql, "to", "20231123", amountStr, amountStr, batchId, phone) + "\n";
            String refundSql = StrUtil.format(sql, "refund", "20231123", amountStr, amountStr, batchId, phone) + "\n";
            FileUtils.write(sqlFile, toSql, "utf-8", true);
            FileUtils.write(sqlFile, refundSql, "utf-8", true);
        }
    }
}
