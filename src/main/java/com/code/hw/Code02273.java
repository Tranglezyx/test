package com.code.hw;

import java.util.Scanner;

/**
 * @author trangle
 */
public class Code02273 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            String[] split = str.split(" ");
            int[] value = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                value[i] = Integer.valueOf(split[i]);
            }
            System.out.println(process(value));
        }
    }

    public static String process(int[] nums) {
        int minLeft = 0;
        int minRight = 0;
        int max = 0;
        int minLength = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int tmpMax = 0;
            int tmpLeft = 0;
            int tmpRight = 0;
            int tmpLength = 0;
            for (int j = i + 2; j < nums.length; j++) {
                int tmpResult = calculate(nums, i, j);
                if (tmpResult > tmpMax) {
                    tmpMax = tmpResult;
                    tmpLeft = i;
                    tmpRight = j;
                    tmpLength = j - i + 1;
                }
                if (tmpResult == tmpMax && (j - i + 1) < tmpLength) {
                    tmpMax = tmpResult;
                    tmpLeft = i;
                    tmpRight = j;
                    tmpLength = j - i + 1;
                }
            }
            if (tmpMax > max) {
                max = tmpMax;
                minLeft = tmpLeft;
                minRight = tmpRight;
                minLength = tmpLength;
            }
            if (tmpMax == max && tmpLength < minLength) {
                max = tmpMax;
                minLeft = tmpLeft;
                minRight = tmpRight;
                minLength = tmpLength;
            }
        }
        if (max == 0) {
            return "0";
        } else {
            return minLeft + " " + minRight + ":" + max;
        }
    }

    public static int calculate(int[] nums, int start, int end) {
        if (end <= start + 1) {
            return 0;
        }
        int lowHigh = Math.min(nums[start], nums[end]);
        int result = 0;
        for (int i = start + 1; i < end; i++) {
            int tmpHigh = nums[i];
            if (tmpHigh < lowHigh) {
                result = result + lowHigh - tmpHigh;
            }
        }
        return result;
    }
}
