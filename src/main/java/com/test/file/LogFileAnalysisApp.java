package com.test.file;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/11/26 17:12
 * @Description:
 */
@Slf4j
public class LogFileAnalysisApp {

    public static void main(String[] args) throws IOException {
//        logAnalysisByID();
        logAnalysisByBatchIdAndPhone();
    }

    private static void logAnalysisByBatchIdAndPhone() throws IOException {
        Map<String, String> map = new HashMap<>(600000);
        String path = "D:\\m5g-gwin.detail-2024-11-26.0.log";
        Stream<String> lines = Files.lines(Paths.get(path));
        lines.forEach(item -> {
            boolean filter = false;
            if (item.contains("MySQL消费接收成功") && item.contains("BASIC_INFO")) {
                filter = true;
            }
            if (filter) {
                int start = item.indexOf("gwinSmsTo|");
                String batchId = item.substring(start + "gwinSmsTo|".length(), start + "2024112600000053".length() + "gwinSmsTo|".length());

                start = item.indexOf("|MySQL消费接收成功");
                String substring = item.substring(0, start);
                String phone = substring.substring(substring.length() - 11);

                String id = batchId + "-" + phone;
                if (map.containsKey(id)) {
                    log.info("相同ID数据1={}", item);
                    log.info("相同ID数据2={}", map.get(id));
                } else {
                    map.put(id, item);
                }
            }
        });

        String path2 = "D:\\m5g-gwin.detail-2024-11-26.1.log";
        Stream<String> lines2 = Files.lines(Paths.get(path2));
        lines2.forEach(item -> {
            boolean filter = false;
            if (item.contains("MySQL消费接收成功") && item.contains("BASIC_INFO")) {
                filter = true;
            }
            if (filter) {
                int start = item.indexOf("gwinSmsTo|");
                String batchId = item.substring(start + "gwinSmsTo|".length(), start + "2024112600000053".length() + "gwinSmsTo|".length());

                start = item.indexOf("MySQL消费接收成功");
                String substring = item.substring(0, start);
                String phone = substring.substring(substring.length() - 11);

                String id = batchId + "-" + phone;
                if (map.containsKey(id)) {
                    log.info("相同ID数据1={}", item);
                    log.info("相同ID数据2={}", map.get(id));
                } else {
                    map.put(id, item);
                }
            }
        });
        log.info("结束---");
    }

    private static void logAnalysisByID() throws IOException {
        Map<String, String> map = new HashMap<>(600000);
        String path = "D:\\m5g-gwin.detail-2024-11-26.0.log";
        Stream<String> lines = Files.lines(Paths.get(path));
        lines.forEach(item -> {
            int start = item.indexOf("ID=");
            String id = item.substring(start + 3, start + "1861325212810993678".length() + 3);
            boolean filter = false;
            if (item.contains("MySQL消费接收成功") && item.contains("BASIC_INFO")) {
                filter = true;
            }
            if (filter) {
                if (map.containsKey(id)) {
                    log.info("相同ID数据1={}", item);
                    log.info("相同ID数据2={}", map.get(id));
                } else {
                    map.put(id, item);
                }
            }
        });

        String path2 = "D:\\m5g-gwin.detail-2024-11-26.1.log";
        Stream<String> lines2 = Files.lines(Paths.get(path2));
        lines2.forEach(item -> {
            int start = item.indexOf("ID=");
            String id = item.substring(start + 3, start + "1861325212810993678".length() + 3);
            boolean filter = false;
            if (item.contains("MySQL消费接收成功") && item.contains("BASIC_INFO")) {
                filter = true;
            }
            if (filter) {
                if (map.containsKey(id)) {
                    log.info("相同ID数据1={}", item);
                    log.info("相同ID数据2={}", map.get(id));
                } else {
                    map.put(id, item);
                }
            }
        });
        log.info("结束---");
    }
}
