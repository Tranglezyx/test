package com.test.thread;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/12/19 09:43
 * @Description:
 */
@Slf4j
public class ThreadSortApp {

    public static final Executor executor = Executors.newFixedThreadPool(100);
    private static final List<SortDTO> sortDTOList = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            sortDTOList.add(new SortDTO(RandomUtil.randomString(5), RandomUtil.randomInt()));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("生成结果,json={}", JSONObject.toJSONString(sortDTOList));

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                long start = System.currentTimeMillis();
                while (true) {
                    if (System.currentTimeMillis() - start > 15000) {
                        break;
                    }
                    sortDTOList.get(RandomUtil.randomInt(0, sortDTOList.size() - 1)).setAge(RandomUtil.randomInt());
                }
            });
        }

        for (int i = 0; i < 100000; i++) {
            List<SortDTO> result = sortDTOList.stream().sorted(Comparator.comparing(SortDTO::getAge)).collect(Collectors.toList());
//            List<SortDTO> result = sort(sortDTOList);

//            Map<Integer,SortDTO> collect = sortDTOList.stream().collect(Collectors.toMap(item -> item.getAge(), item -> item, (o1, o2) -> o2));
//            List<Integer> sortList = collect.keySet().stream().sorted().collect(Collectors.toList());
//            List<SortDTO> result = new ArrayList<>();
//            sortList.forEach(item -> result.add(collect.get(item)));

            log.info("排序结果,size={}", result.size());
        }
    }

    public List<SortDTO> sort(List<SortDTO> list) {
        List<SortDTO> result = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = i + 1; j < list.size(); j++) {
//                if(list.get(i))
//            }
//        }
        return result;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SortDTO {
        private String name;
        private Integer age;
    }
}


