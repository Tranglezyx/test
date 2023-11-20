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

@Slf4j
public class LogsApp {

    public static void main(String[] args) throws IOException, ParseException, LogException {
        String toString = FileUtils.readFileToString(new File("无标题1.json"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(toString);
        JSONArray records = jsonObject.getJSONArray("RECORDS");
        // fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
        String startDate = "2023-11-16 00:00:00";
        String endTime = "2023-11-20 23:59:59";
        log.info("需要判断的号码数量为:{}", records.size());
        int count = 0;
        for (Object record : records) {
            if (record instanceof JSONObject) {
                String batchId = ((JSONObject) record).getString("batch_id");
                String phone = ((JSONObject) record).getString("target_number");
                // 查询语句。
                String query = StrUtil.format("{} and {} and 退费成功", batchId, phone);
                long num = SLSUtils.queryLogsCount(query, startDate, endTime);
                if (num > 0) {
                    log.info("退费成功,batchId:{},phone:{}", batchId, phone);
                }
                count++;
                if (count % 10000 == 0) {
                    log.info("第 {} 条完成", count);
                }
            }
        }
    }
}
