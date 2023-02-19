package com.test.code.niuke;

import com.test.code.entity.ListNode;

public class NiukeEasySolution {

    /**
     * 描述
     * 给你一个大小为 n 的字符串数组 strs ，其中包含n个字符串 , 编写一个函数来查找字符串数组中的最长公共前缀，返回这个公共前缀。
     *
     * @param strs string字符串一维数组
     * @return string字符串
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String startWith = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int minLength = Math.min(startWith.length(), str.length());
            startWith = startWith.substring(0, minLength);
            for (int j = 0; j < minLength; j++) {
                if (startWith.charAt(j) != str.charAt(j)) {
                    startWith = startWith.substring(0, j);
                    break;
                }
            }
        }
        return startWith;
    }

    /**
     * 描述
     * 给定一个整数数组
     * i 个台阶向上爬需要支付的费用，下标从0开始。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     * <p>
     * 数据范围：数组长度满足
     * <p>
     * 10, 20, 30, 40, 50, 1,  10, 1
     * 10, 20, 40, 60, 90, 61, 71, 62
     *
     * @param cost int整型一维数组
     * @return int整型
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        if (length == 1) {
            return cost[0];
        }
        int[] value = new int[length];
        value[0] = cost[0];
        value[1] = cost[1];
        for (int i = 2; i < length; i++) {
            value[i] = cost[i] + Math.min(value[i - 1], value[i - 2]);
        }
        return Math.min(value[length - 1], value[length - 2]);
    }

    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
