package com.test.sls;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.exception.LogException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Slf4j
public class SearchLogsApp {

    public static void main(String[] args) throws LogException, IOException, ParseException {
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

    private static void queryRefundSuccessInfo() throws IOException, LogException {
        String toString = FileUtils.readFileToString(new File("无标题.json"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(toString);
        JSONArray records = jsonObject.getJSONArray("RECORDS");
        // fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
        int fromTime = (int) (System.currentTimeMillis() / 1000 - 36000);
        int toTime = (int) (System.currentTimeMillis() / 1000);
        for (Object record : records) {
            if (record instanceof JSONObject) {
                String batchId = ((JSONObject) record).getString("batch_id");
                String phone = ((JSONObject) record).getString("target_number");
                // 查询语句。
                String query = StrUtil.format("{} and {} and 退费成功", batchId, phone);
                long num = SLSUtils.queryLogsCount(query, fromTime, toTime);
                if (num > 0) {
                    log.info("退费成功,batchId:{},phone:{}", batchId, phone);
                }
            }
        }
    }


}
