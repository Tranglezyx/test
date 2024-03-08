package com.code.solution;

import com.code.entity.UnionFindCollect;

/**
 * @author trangle
 */
public class DifficultSolution {

    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * <p>
     *  
     * <p>
     * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     * <p>
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums 数组
     * @return 长度
     * <p>
     * 思路：
     * 核心是利用并查集
     * 使用并查集要搞懂几个核心点
     * 1、怎么判断两个数字需要合并
     * （可以利用数组存值，值当下标，有值为1，无值为0，+1或-1的情况下某一边有值则合并）
     *
     * 未完成
     */
    // TODO
    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int addNum = 1000;
        int length = addNum * 2;
        int[] flag = new int[length];
        int max = 1;
        UnionFindCollect ufc = new UnionFindCollect(length);
        for (int i = 0; i < nums.length; i++) {
            // 因为存在负数的可能，所以都统一加上addNumber
            nums[i] += addNum;
            flag[nums[i]] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int tempNum = nums[i];
            // 当前值根节点
            int tempNumParent = ufc.find(tempNum);
            // 当前值根节点的长度
            int tempNumParentLength = flag[tempNumParent];
            // 可能需要合并增加的长度
            int addLength = 0;
            // 相邻有值，则合并
            if (flag[tempNum + 1] >= 1) {
                int rightTempNumParent = ufc.find(tempNum + 1);
                if (rightTempNumParent != tempNumParent) {
                    addLength = flag[rightTempNumParent];
                }
                ufc.union(tempNum, tempNum + 1);
            }
            if (flag[tempNum - 1] >= 1) {
                int leftTempNumParent = ufc.find(tempNum - 1);
                if (leftTempNumParent != tempNumParent) {
                    addLength = flag[leftTempNumParent];
                }
                ufc.union(tempNum, tempNum - 1);
            }
            // 当前值合并后的根节点
            tempNumParent = ufc.find(tempNum);
            flag[tempNumParent] = tempNumParentLength + addLength;
            max = Math.max(flag[tempNumParent], max);
        }
        return max;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * <p>
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,3,5,0,0,3,1,4]
     * 输出: 6
     * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
     * ------
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param prices
     * @return
     */
    // TODO
    public static int maxProfit(int[] prices) {
        int profit = 0;
        int[] stock = new int[2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {

                }
            }
        }
        return profit;
    }
}
