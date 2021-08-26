package com.test.code.hw;

/**
 * @author trangle
 *
 * 查询数组中连续和最大的子序列
 */
public class CodeApp {

    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,1,0,-1,3,4,0,1,-2,7,8};
//        int[] nums = new int[]{-1,-2};
        int[] nums = new int[]{1, 2, -5, 1, 1, 1};
        function(nums);
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
