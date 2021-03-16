package com.test.code.solution;

import com.test.code.entity.ListNode;
import com.test.code.entity.UnionFindCollect;

import java.util.List;
import java.util.Objects;

/**
 * @author trangle
 */
public class EasySolution {

    /**
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * <p>
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * <p>
     * 请找出在 t 中被添加的字母。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "abcd", t = "abcde"
     * 输出："e"
     * 解释：'e' 是那个被添加的字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-difference
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        int[] flag1 = new int[26];
        int[] flag2 = new int[26];
        for (char c : chars1) {
            flag1[((int) c) - 97]++;
        }
        for (char c : chars2) {
            flag2[((int) c) - 97]++;
        }
        int num = 0;
        for (int i = 0; i < flag2.length; i++) {
            if (flag2[i] > flag1[i]) {
                num = i;
                break;
            }
        }
        return ((char) (num + 97));
    }

    /**
     * 给定一个二进制数组， 计算其中最大连续1的个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,1,0,1,1,1]
     * 输出: 3
     * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
     * 注意：
     * <p>
     * 输入的数组只包含 0 和1。
     * 输入数组的长度是正整数，且不超过 10,000。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/max-consecutive-ones
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums 数组
     * @return 最大长度
     * 思路：动态规划
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] == 1 ? 1 : 0;
        }
        int max = 0;
        int tmpMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                tmpMax++;
            } else {
                tmpMax = 0;
            }
            max = Math.max(max, tmpMax);
        }
        return max;
    }

    /**
     * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
     * <p>
     * 示例：
     * <p>
     * 输入： [-2,1,-3,4,-1,2,1,-5,4]
     * 输出： 6
     * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int max = nums[0];
        int subSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            subSum = Math.max(subSum + nums[i], nums[i]);
            max = Math.max(subSum, max);
        }
        return max;
    }

    /**
     * 反转单项链表
     * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
     *
     * @param head
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseNodeHeader = new ListNode(0);
        while (head != null) {
            // 暂存节点
            ListNode reverseNode = head;
            head = head.next;
            // 进行反转
            ListNode tmpNode = reverseNodeHeader.next;
            reverseNodeHeader.next = reverseNode;
            reverseNode.next = tmpNode;
        }
        return reverseNodeHeader.next;
    }


    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [0,1,3]
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: [0,1,2,3,4,5,6,7,9]
     * 输出: 8
     * <p>
     * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
     *
     * @param nums
     * @return 返回不存在的数字
     * <p>
     * 思路：
     * 因为是递增的，且数字长度在 0 ~ n-1中，
     * 所以可以通过判断当前数字值与其下标的大小
     * 如果当前值大于其下标，则说明缺少的值小于当前值
     * 如果当前值等于其下标，则说明缺少的值大于当前值
     */
    public int missingNumber(int[] nums) {
        int start = 0;
        int end = nums.length;
        int missNum = 0;
        // 如果长度为非偶数，则中间值自动向下取整
        int middle = (end + start) / 2;
        while (end - start != 1) {
            if (nums[middle] == middle) {
                // 等于则说明缺少值大于当前值
                start = middle;
            } else {
                // 剩下只剩一种当前值大于其下标的情况，则说明缺少值小于当前值
                end = middle;
            }
            middle = (end + start) / 2;
        }
        if (nums[middle] == middle) {
            missNum = nums[middle] + 1;
        } else {
            missNum = nums[middle] - 1;
        }
        return missNum;
    }

    /**
     * 674. 最长连续递增序列
     * <p>
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     * <p>
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return 最长递增子序列长度
     * <p>
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 最大值
        int max = 1;
        // 当前值
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                count++;
            } else {
                count = 1;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    /**
     * 打印全部
     * *
     * * * *
     * * * * * *
     * * * * * * * *
     * * * * * *
     * * * *
     * *
     *
     * @param n
     */
    public static void printAll(int n) {
        for (int i = 1; i <= n; i++) {
            printLine(i, n);
        }
        for (int i = n - 1; i >= 1; i--) {
            printLine(i, n);
        }
    }

    /**
     * 打印每一行
     *
     * @param n   当前行数
     * @param num 对称图形的上半部分总行数
     */
    public static void printLine(int n, int num) {
        // 打印空格数量
        int blankAccount = getPrintAllNumbers(num) - getPrintAllNumbers(n);
        for (int i = 1; i <= blankAccount; i++) {
            System.out.print(" ");
        }

        // 当前行需要打印*的个数
        int account = getPrintNumbers(n);

        for (int i = 1; i <= account; i++) {
            System.out.print("*");
            if (i != account) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * 第n行需要打印*的个数
     *
     * @param n
     * @return
     */
    public static int getPrintNumbers(int n) {
        return 2 * n - 1;
    }

    /**
     * 第n行自第一个*开始需要打印的总数量
     *
     * @param n
     * @return
     */
    public static int getPrintAllNumbers(int n) {
        return getPrintNumbers(n) + getPrintNumbers(n) - 1;
    }

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

    /**
     * 判断数组是否对称
     *
     * @param valueList
     * @return
     */
    public static boolean isSymmetry(List<Integer> valueList) {
        for (int i = 0, j = valueList.size() - 1; i < valueList.size(); i++, j--) {
            if (!Objects.equals(valueList.get(i), valueList.get(j))) {
                return false;
            }
            if (j <= i) {
                break;
            }
        }
        return true;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }
}
