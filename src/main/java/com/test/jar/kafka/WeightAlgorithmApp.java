package com.test.jar.kafka;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 拉取权重算法
 *
 * @Author: zhouyunxiang
 * @Date: 2024/7/25 16:11
 * @Description:
 */
public class WeightAlgorithmApp {

    /**
     * 计算权重数量
     *
     * @param topicList
     * @param totalCount
     * @param topic
     * @return
     */
    private static int calculatePullNum(List<String> topicList, int totalCount, String topic) {
        final int size = topicList.size();
        int sum = 0;
        int currIndex = -1;
        for (int i = 1; i < size + 1; i++) {
            if (topicList.get(i - 1).equalsIgnoreCase(topic)) {
                currIndex = i;
            }
            sum += i;
        }
        double w = (double) (size + 1 - currIndex) / sum;
        return Math.max((int) (w * totalCount), 1);
    }

    public static void main(String[] args) {
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "a"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "b"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "c"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "d"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "e"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "f"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "g"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "h"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "i"));
//        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"), 10000, "j"));

        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "a"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "b"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "c"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "d"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "e"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "f"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "g"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "h"));
        System.out.println(calculatePullNum(Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i"), 10000, "i"));
    }
}
