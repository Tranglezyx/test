package com.company.aliyun.sls;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.exception.LogException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Slf4j
public class SearchLogsApp {

    public static void main(String[] args) throws LogException, IOException, ParseException {
        queryRefundSuccessInfo();
    }

    private static void exportPhoneWithSendError() throws LogException, ParseException, IOException {
        File file = new File("2023-11-19日发送失败号码.txt");
        String query = "因流程异常无法发送号码";
        String startDate = "2023-11-19 20:00:00";
        String endTime = "2023-11-19 22:00:00";
        List<String> logList = SLSUtils.queryLogsContent(query, startDate, endTime);
        log.info("获取日志，日志数量:{}", logList.size());
        for (String log : logList) {
            JSONObject jsonObject = JSON.parseObject(log);
            String content = jsonObject.getString("content");
            String phone = content.substring(content.indexOf("因流程异常无法发送号码")).replace("因流程异常无法发送号码:", "");
            FileUtils.write(file, phone + "\n", "utf-8", true);
        }
    }

    private static void queryCount() throws ParseException, LogException {
        String startDate = "2023-10-12 00:00:00";
        String endTime = "2023-10-12 23:59:59";
        String str = "";
        String[] split = str.split("\n");
        long count = 0;
        for (String name : split) {
            String query = StrUtil.format("sendKafka and {}", name);
            long num = SLSUtils.queryLogsCount(query, startDate, endTime);
            log.info("name:{},num:{}", name, num);
            count += num;
        }
        log.info("总数量:{}", count);
    }

    private static void queryRefundSuccessInfo() throws IOException, LogException, ParseException {
        String fileName = "20231212.json";
        String toString = FileUtils.readFileToString(new File(fileName), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(toString);
        JSONArray records = jsonObject.getJSONArray("RECORDS");
        // fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
        String startDate = "2023-12-12 00:00:00";
        String endTime = "2023-12-13 23:59:59";
        log.info("需要判断的号码数量为:{}", records.size());
        File refundSuccessFile = new File("退费成功" + fileName);
        int count = 0;
        for (Object record : records) {
            if (record instanceof JSONObject) {
                String batchId = ((JSONObject) record).getString("batch_id");
                String phone = ((JSONObject) record).getString("target_number");
                // 查询语句。
                String query = StrUtil.format("{} and {} and 退费成功 and not 下行", batchId, phone);
                List<String> logList = SLSUtils.queryLogsContent(query, startDate, endTime);
                if (CollectionUtils.isNotEmpty(logList)) {
                    if (logList.size() == 1) {
                        log.info("退费成功,batchId:{},phone:{}", batchId, phone);
                        String str = logList.get(0);
                        String amount = str.substring(str.indexOf("refundAmount:"), str.indexOf(",batchId:")).replace("refundAmount:", "");
                        String value = batchId + "," + phone + "," + amount + "\n";
                        FileUtils.write(refundSuccessFile, value, "utf-8", true);
                    } else {
                        log.info("退费成功,数据错误,batchId:{},phone:{}", batchId, phone);
                    }
                }
                count++;
                if (count % 10000 == 0) {
                    log.info("第 {} 条完成", count);
                }
            }
        }
    }


}
