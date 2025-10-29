package com.company.dm.fix;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/10 18:09
 * @Description:
 */
@Slf4j
public class Fix20251010_1 {

    public static final String splitSymbol = "&&&";
    public static final double threshold = 0.6; // 相似度阈值（可调）

    public static void main(String[] args) throws IOException {
        List<String> templateStringList = Files.readAllLines(Paths.get("D:\\export\\template.tsv"));

        Map<String, String> bigParamTemplate = new HashMap<>(80000);
        for (String string : templateStringList) {
            Template template = Template.get(string,splitSymbol);
            if (StringUtils.isNotEmpty(template.getContent()) && template.getContent().trim().equals("{1}")) {
                bigParamTemplate.put(template.getId(), template.getContent());
            }
        }

        List<String> list = Lists.newArrayList(
//                "D:\\export\\20251010.tsv",
                "D:\\export\\20251009.tsv"
//                "D:\\export\\20251008.tsv",
//                "D:\\export\\20251007.tsv"
        );

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (String path : list) {
            executorService.execute(() -> {
                Fix20251010.process(path, bigParamTemplate);
            });
        }
    }
}
