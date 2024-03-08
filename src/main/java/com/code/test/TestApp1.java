package com.code.test;

/**
 * @author trangle
 */
public class TestApp1 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,2,10,1};
        int[] count = new TestApp1().runningSum(nums);
        for (int i : count) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public int[] runningSum(int[] nums) {
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            count[i] = nums[i];
            if (i > 0) {
                count[i] += count[i - 1];
            }
        }
        return count;
    }
}
