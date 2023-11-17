package com.test.sls;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.Histogram;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetHistogramsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Slf4j
public class SearchLogsApp {

    // 本示例从环境变量中获取AccessKey ID和AccessKey Secret。
    static String accessId = "";
    static String accessKey = "";
    // 日志服务的服务接入点。此处以杭州为例，其它地域请根据实际情况填写。
    static String host = "cn-guangzhou.log.aliyuncs.com";
    // 创建日志服务Client。
    static Client client = new Client(host, accessId, accessKey);
    // Project名称。
    static String projectName = "k8s-log-custom-allcloud";
    // Logstore名称。
    static String logstoreName = "danmi-prod";


    public static void main(String[] args) throws LogException, IOException, ParseException {
        String startDate = "2023-10-12";
        String endTime = "2023-10-12 23:59:59";
        Date start = DateUtils.parseDate(startDate, "yyyy-MM-dd");
        Date end = DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss");
        int fromTime = (int) (start.getTime() / 1000);
        int toTime = (int) (end.getTime() / 1000);

        String str = "";
        String[] split = str.split("\n");
        long count = 0;
        for (String name : split) {
            String query = StrUtil.format("sendKafka and {}", name);
            long num = queryLogs(query, fromTime, toTime);
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
                long num = queryLogs(query, fromTime, toTime);
                if (num > 0) {
                    log.info("退费成功,batchId:{},phone:{}", batchId, phone);
                }
            }
        }
    }

    public static long queryLogs(String query, int fromTime, int toTime) throws LogException {
        GetHistogramsResponse response = client.GetHistograms(projectName, logstoreName, fromTime, toTime, "", query);
        long count = 0;
        for (Histogram histogram : response.GetHistograms()) {
            // 由于区间较多，只输出有日志分布的区间。
            if (0 < histogram.GetCount()) {
                // 输出日志数量。
                count += histogram.GetCount();
            }
        }
        return count;
    }
}
