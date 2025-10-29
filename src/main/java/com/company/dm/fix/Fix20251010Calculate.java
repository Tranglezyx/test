package com.company.dm.fix;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.test.util.string.TextClusterBySimilarity;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/10 18:09
 * @Description:
 */
@Slf4j
public class Fix20251010Calculate {

    public static final String splitSymbol = "&&&";
    public static final String contentSplitSymbol = "###";
    public static final double threshold = 0.5; // 相似度阈值（可调）

    public static final int limitNum = 100000;

    public static void main(String[] args) throws IOException {

        List<String> list = Lists.newArrayList(
                "D:\\export\\20251002.tsv.result"
//                "D:\\export\\20251009.tsv.result",
//                "D:\\export\\20251008.tsv.result",
//                "D:\\export\\20251007.tsv.result"
        );

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (String path : list) {
            executorService.execute(() -> {
                process(path);
            });
        }
    }

    private static void process(String path) {
        boolean exist = FileUtil.exist(path);
        if (!exist) {
            return;
        }
        List<CsvRow> buffer = new ArrayList<>(limitNum);
        CsvReadConfig readConfig = new CsvReadConfig();
        readConfig.setFieldSeparator('\t'); // TSV 格式
        CsvReader reader = CsvUtil.getReader(readConfig);
        reader.read(FileUtil.getReader(path, CharsetUtil.CHARSET_UTF_8), (CsvRow row) -> {
            buffer.add(row);

            if (buffer.size() >= limitNum) {
                processBatchCsvRow(buffer, path);
                buffer.clear();
            }
        });

        // 处理最后一批不足1万行的数据
        if (!buffer.isEmpty()) {
            processBatchCsvRow(buffer, path);
        }
    }

    private static void processBatchCsvRow(List<CsvRow> lines, String path) {
        List<String> list = new ArrayList<>();
        for (CsvRow line : lines) {
            list.add(line.get(0));
        }
        processBatch(list, path);
        log.info("批处理完成一批,path:{}", path);
    }

    private static void processBatch(List<String> lines, String path) {
        List<Sms> list = new ArrayList<>();
        for (String line : lines) {
            Sms sms = Sms.get(line);
            if (sms == null) {
                continue;
            }
            list.add(sms);
        }
        calculateTemplate(path, list);
    }

    private static void calculateTemplate(String path, List<Sms> list) {
        Map<String, List<Sms>> collect = list.stream().collect(Collectors.groupingBy(Sms::key));
        for (Map.Entry<String, List<Sms>> entry : collect.entrySet()) {
            List<Sms> smsList = entry.getValue();
            Sms sms = smsList.get(0);
            String developerId = sms.getDeveloperId();
            String sign = sms.getSign();
            String productId = sms.getProductId();
            List<String> contentList = smsList.stream().map(Sms::getContent).collect(Collectors.toList());
            List<List<String>> valueResult = TextClusterBySimilarity.clusterBySimilarity(contentList, threshold);

            for (List<String> stringList : valueResult) {
                String content = stringList.get(0);
                StringBuilder sb = new StringBuilder();

                int max = 3;
                String example = stringList.subList(0, stringList.size() > max ? max : stringList.size())
                        .stream().collect(Collectors.joining(contentSplitSymbol));


                sb.append(developerId)
                        .append(splitSymbol)
                        .append(sign)
                        .append(splitSymbol)
                        .append(productId)
                        .append(splitSymbol)
                        .append(content)
                        .append(splitSymbol)
                        .append(example);
                String value = StrUtil.format("{}\n", sb.toString());
                String result = path + ".cal";
                boolean exist = FileUtil.exist(result);
                if (!exist) {
                    FileUtil.newFile(result);
                }
                FileUtil.appendUtf8String(value, result);
            }
        }
    }

    private static void clusterBySimilarity(String path, List<Sms> list) {
        Map<String, List<Sms>> collect = list.stream().collect(Collectors.groupingBy(Sms::key));
        for (Map.Entry<String, List<Sms>> entry : collect.entrySet()) {
            List<Sms> smsList = entry.getValue();
            Sms sms = smsList.get(0);
            String developerId = sms.getDeveloperId();
            String sign = sms.getSign();
            String productId = sms.getProductId();
            List<String> contentList = smsList.stream().map(Sms::getContent).collect(Collectors.toList());
            List<List<String>> valueResult = TextClusterBySimilarity.clusterBySimilarity(contentList, threshold);
            for (List<String> stringList : valueResult) {
                String content = stringList.get(0);
                StringBuilder sb = new StringBuilder();
                int size = stringList.size();
                sb.append(developerId)
                        .append(splitSymbol)
                        .append(sign)
                        .append(splitSymbol)
                        .append(productId)
                        .append(splitSymbol)
                        .append(content)
                        .append(splitSymbol)
                        .append(size);
                String value = StrUtil.format("{}\n", sb.toString());
                String result = path + ".result";
                boolean exist = FileUtil.exist(result);
                if (!exist) {
                    FileUtil.newFile(result);
                }
                FileUtil.appendUtf8String(value, result);
            }
        }
    }

    @Data
    @Builder
    public static class Sms {
        private String developerId;
        private String sign;
        private String productId;
        private String content;
        private Integer num;

        public static Sms get(String line) {
            try {
                line = line.replaceAll("\r\n", "");
                String[] split = line.split(splitSymbol);
                String developerId = split[0];
                String sign = split[1];
                String productId = split[2];
                String content = split[3];
                String num = split[4];

                return Sms.builder()
                        .developerId(developerId)
                        .sign(sign)
                        .productId(productId)
                        .content(content)
                        .num(Integer.parseInt(num))
                        .build();
            } catch (Exception e) {
                log.error("解析出错,data={},error={}", line, ExceptionUtil.getSimpleMessage(e));
                return null;
            }
        }

        public String key() {
            return developerId + "-" + sign + "-" + productId;
        }
    }
}
