package com.test.code.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author trangle
 * <p>
 * 查询数组中连续和最大的子序列
 */
public class CodeApp {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("rst");
        list.add("mnrt");
        list = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
        for (int i = 97; i < 122; i++) {
            System.out.printf((char)i + " ");
        }
    }

    public static void function(int[] nums) {
        // 存储最大值
        int startIndex = 0;
        int endIndex = 0;
        int maxCount = 0;

        // 存储中间值
        int tmpStartIndex = 0;
        int tmpEndIndex = 0;
        int tmpCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int tmpLength = tmpEndIndex - tmpStartIndex;
            for (int j = i; j < nums.length; j++) {
                count += nums[j];
                int length = j - i;
                if (count > tmpCount) {
                    tmpStartIndex = i;
                    tmpEndIndex = j;
                    tmpCount = count;
                }
                if (count == tmpCount && length > tmpLength) {
                    tmpStartIndex = i;
                    tmpEndIndex = j;
                    tmpCount = count;
                }
            }
            tmpLength = tmpEndIndex - tmpStartIndex;
            int maxLength = endIndex - startIndex;
            if (tmpCount > maxCount) {
                startIndex = tmpStartIndex;
                endIndex = tmpEndIndex;
                maxCount = tmpCount;
            }
            if (tmpCount == maxCount && tmpLength > maxLength) {
                startIndex = tmpStartIndex;
                endIndex = tmpEndIndex;
                maxCount = tmpCount;
            }
        }
        System.out.println("最大值起始下标为： " + startIndex);
        System.out.println("最大值截止下标为： " + endIndex);
    }
}
