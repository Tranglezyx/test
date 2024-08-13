package com.company.dm;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.company.dm.kafka.MessageDto;
import com.company.dm.kafka.SmsAuditAddParamVo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/12 16:58
 * @Description:
 */
public class AuditRecordApp {

    public static void main(String[] args) throws IOException {
//        portalSmsMassData();
        auditRecordData();
    }

    private static void auditRecordData() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("D:\\result2.txt"));
        File file = new File("D:\\cmpp_audit_record.sql");

        String sqlTemplate = "INSERT INTO t_sms_audit_record_{} (id," +
                " sms_id," +
                " template_id," +
                " developer_id," +
                " account_id," +
                " audit_status," +
                " mobile_channel_id," +
                " unicom_channel_id," +
                " telecom_channel_id," +
                " create_time," +
                " to_send_time," +
                " remarks," +
                " high_risk_words," +
                " sms_content," +
                " can_check," +
                " product_id," +
                " sign_id," +
                " audit_by," +
                " audit_time," +
                " refuse_reason," +
                " commit_time," +
                " product_and_services_id," +
                " phone_record_uuid," +
                " mobile_amount," +
                " unicom_amount," +
                " telecom_amount)" +
                " VALUES ('{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}');";
        String detailTemplate = "INSERT INTO t_sms_audit_record_phone_{} (id," +
                " sms_id," +
                " phone_json," +
                " create_time," +
                " phone_size," +
                " phone_record_uuid) " +
                "VALUES ('{}', '{}', '{}', '{}', '{}', '{}');";
        for (int i = 0; i < strings.size(); i++) {
//            if (i != 0) {
//                continue;
//            }
            SmsAuditAddParamVo smsAuditAddParamVo = JSONObject.parseObject(strings.get(i), SmsAuditAddParamVo.class);
            MessageDto data = smsAuditAddParamVo.getData();
            String batchId = data.getBatchId();
            Integer operatorType = data.getOperatorType();
            Long templateId = data.getTemplateId();
            Long developerId = data.getDeveloperId();
            Long accountId = smsAuditAddParamVo.getAccountId();
            Integer auditStatus = 1;
            Integer channel = 871;

            long submitTime = IdUtil.getSnowflake().getGenerateDateTime(Long.valueOf(data.getSmsId()));
            Date createTimeDate = new Date(submitTime + 1000 * 60 * 10);
            String createTime = DateFormatUtils.format(createTimeDate, "yyyy-MM-dd HH:mm:ss");
            String sendTime = DateFormatUtils.format(new Date(data.getSendTime()), "yyyy-MM-dd HH:mm:ss");
            String remarks = "";
            String highRiskWord = data.getHighRiskWord();
            String smsContent = data.getSmsContent();
            Integer canCheck = 1;
            Long productId = smsAuditAddParamVo.getProductId();
            Long signId = data.getSignId();
            Integer auditBy = 60979170;
            String auditTime = createTime;
            String refuseReason = "";
            String commitTime = DateFormatUtils.format(new Date(submitTime), "yyyy-MM-dd HH:mm:ss");
            Integer productTypeId = 0;

            int mobNum = 0;
            int telNum = 0;
            int uniNum = 0;
            if (operatorType == 1) {
                mobNum = 1;
            }
            if (operatorType == 2) {
                telNum = 1;
            }
            if (operatorType == 3) {
                uniNum = 1;
            }
            String tableName = DateFormatUtils.format(new Date(submitTime), "yyyyMMdd");
            String uuid = IdUtil.fastUUID() + IdUtil.getSnowflakeNextId();
            String jsonTemplate = "{\"{}\":[]}";
            String json = StrUtil.format(jsonTemplate, data.getPhone());
            if(batchId.startsWith("iw") || batchId.startsWith("tw")){
                continue;
            }
            batchId = "";

            String detailSql = StrUtil.format(detailTemplate, tableName, IdUtil.getSnowflakeNextId(), batchId, json, createTimeDate.getTime(), 1, uuid);
            String recordSql = StrUtil.format(sqlTemplate, tableName,
                    IdUtil.getSnowflakeNextId(),
                    batchId,
                    templateId,
                    developerId,
                    accountId,
                    auditStatus,
                    channel, channel, channel,
                    createTime,
                    sendTime,
                    remarks,
                    highRiskWord,
                    smsContent,
                    canCheck, productId, signId, auditBy, auditTime, refuseReason, commitTime, productTypeId, uuid, mobNum, uniNum, telNum);
            FileUtils.write(file, recordSql + "\r\n", "utf-8", true);
            FileUtils.write(file, detailSql + "\r\n", "utf-8", true);
        }
    }

    private static void portalSmsMassData() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("D:\\result2.txt"));
        File file = new File("D:\\portal_sms_mass.sql");
        String sqlTemplate = "update portal_sms_mass set synthesis_state = 4 and remark = '审核错误修复数据' where mass_id = '{}';";
        Set<String> massIdSet = new HashSet<>();
        for (String string : strings) {
            SmsAuditAddParamVo smsAuditAddParamVo = JSONObject.parseObject(string, SmsAuditAddParamVo.class);
            String batchId = smsAuditAddParamVo.getData().getBatchId();
            if (batchId.startsWith("tw") || batchId.startsWith("iw")) {
                if (!massIdSet.contains(batchId)) {
                    String sql = StrUtil.format(sqlTemplate, batchId);
                    FileUtils.write(file, sql + "\r\n", "utf-8", true);
                    massIdSet.add(batchId);
                }
            }
        }
    }
}
