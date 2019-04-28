package com.test.code;

/**
 * @author trangle
 */
public class EasySolution {

    /**
     * 两数之和
     * <p>
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] results = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    results[0] = i;
                    results[1] = j;
                }
            }
        }
        return results;
    }

    /**
     * 相同的树
     * <p>
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 121. 买卖股票的最佳时机
     * <p>
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意你不能在买入股票前卖出股票。
     *
     * @param prices
     * @return
     */
    public static int maxProfit121(int[] prices) {
//        int money = 0;
//        for (int i = 0; i < prices.length; i++) {
//            for (int j = i + 1; j < prices.length; j++) {
//                if (prices[j] > prices[i]) {
//                    money = Math.max(money, prices[j] - prices[i]);
//                }
//            }
//        }
//        return money;

        if (prices.length == 0) return 0;

        int minValue = prices[0]; // 股票最低价
        int maxValue = 0; // 最大股票差价

        for (int i = 1; i < prices.length; i++) {
            int value = prices[i] - minValue;
            if (value > maxValue) maxValue = value;
            if (prices[i] < minValue) minValue = prices[i];
        }

        return maxValue;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * <p>
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices
     * @return
     */
    public static int maxProfit122(int[] prices) {
        int amount = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                amount += prices[i + 1] - prices[i];
            }
        }
        return amount;
    }

    /**
     * 53.最大子序和
     * <p>
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxSubSum = nums[0], subSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            subSum = Math.max(nums[i], subSum + nums[i]);
            maxSubSum = Math.max(maxSubSum, subSum);
        }
        return maxSubSum;
    }

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer val) {
            this.val = val;
        }
    }
}
