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

import java.io.File;
import java.io.IOException;

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


    public static void main(String[] args) throws LogException, IOException {
        String toString = FileUtils.readFileToString(new File("无标题.json"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(toString);
        JSONArray records = jsonObject.getJSONArray("RECORDS");
        for (Object record : records) {
            if (record instanceof JSONObject) {
                String batchId = ((JSONObject) record).getString("batch_id");
                String phone = ((JSONObject) record).getString("target_number");
                queryLogs(batchId, phone);
            }
        }
    }

    public static void queryLogs(String batchId, String phone) throws LogException {
        // 查询语句。
        String query = StrUtil.format("{} and {} and 退费成功", batchId, phone);
        // fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
        int fromTime = (int) (System.currentTimeMillis() / 1000 - 36000);
        int toTime = (int) (System.currentTimeMillis() / 1000);

        GetHistogramsResponse response = client.GetHistograms(projectName, logstoreName, fromTime, toTime, "", query);
        long count = 0;
        for (Histogram histogram : response.GetHistograms()) {
            // 由于区间较多，只输出有日志分布的区间。
            if (0 < histogram.GetCount()) {
                // 输出日志数量。
                count += histogram.GetCount();
            }
        }
        if (count > 0) {
            log.info("退费成功 >>> {},{}", batchId, phone);
        }
    }
}
