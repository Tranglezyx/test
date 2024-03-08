package com.code.hw;

import java.util.Scanner;

/**
 * @author trangle
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        if (n == 1 || n == 0) {
            System.out.println(n);
            return;
        }
        // 记录不同重复值的起始和结束下标
        int[][] countIndex = new int[65535][2];
        // 记录不同重复值的出现重复次数，下标为值，value为次数
        int[] count = new int[65535];
        int maxNum = 0;
        for (int i = 0; i < n; i++) {
            int value = array[i];
            count[value]++;
            maxNum = Math.max(count[value], maxNum);
            if (count[value] == 1) {
                countIndex[value][0] = i;
            }
            countIndex[value][1] = Math.max(countIndex[value][1], i);
        }
        int minLength = 0;
        for (int i = 0; i < count.length; i++) {
            int num = count[i];
            if (num == 0) {
                continue;
            }
            if (num == maxNum) {
                int start = countIndex[i][0];
                int end = countIndex[i][1];
                if (minLength == 0) {
                    minLength = end - start + 1;
                } else {
                    minLength = Math.min(minLength, end - start + 1);
                }
            }
        }
        System.out.println(minLength);
    }
}
