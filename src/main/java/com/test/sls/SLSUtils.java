package com.test.sls;

import cn.hutool.core.util.StrUtil;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.Histogram;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetHistogramsResponse;
import com.aliyun.openservices.log.response.GetLogsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class SLSUtils {

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

    public static List<String> queryLogsContent(String query, String startDate, String endTime) throws LogException, ParseException {
        Date start = DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss");
        int fromTime = (int) (start.getTime() / 1000);
        int toTime = (int) (end.getTime() / 1000);
        return queryLogsContent(query, fromTime, toTime);
    }

    public static List<String> queryLogsContent(String query, int fromTime, int toTime) throws LogException {
        long count = queryLogsCount(query, fromTime, toTime);
        if (count == 0) {
            return null;
        }
        long batchSize = 100;
        long batchNum = count / 100 + 1;
        List<String> resultList = new ArrayList<>();
        for (long i = 0; i < batchNum; i++) {
            GetLogsResponse logsResponse = client.GetLogs(projectName, logstoreName, fromTime, toTime, "", query, batchSize, batchSize * i, true);
            for (QueriedLog log : logsResponse.getLogs()) {
                LogItem item = log.GetLogItem();
                resultList.add(item.ToJsonString());
            }
        }
        return resultList;
    }

    public static long queryLogsCount(String query, String startDate, String endTime) throws LogException, ParseException {
        Date start = DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss");
        int fromTime = (int) (start.getTime() / 1000);
        int toTime = (int) (end.getTime() / 1000);
        return queryLogsCount(query, fromTime, toTime);
    }

    public static long queryLogsCount(String query, int fromTime, int toTime) throws LogException {
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
