package com.code.solution;

import com.code.entity.ListNode;

import java.util.List;
import java.util.Objects;

/**
 * @author trangle
 */
public class EasySolution {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：s = "(]"
     * 输出：false
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        char[] left = new char[chars.length];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(' || c == '[' || c == '{') {
                left[index] = c;
                index++;
                continue;
            }
            if (c == ')') {
                if (index != 0 && left[index - 1] == '(') {
                    index--;
                } else {
                    return false;
                }
                continue;
            }
            if (c == '}') {
                if (index != 0 && left[index - 1] == '{') {
                    index--;
                } else {
                    return false;
                }
                continue;
            }
            if (c == ']') {
                if (index != 0 && left[index - 1] == '[') {
                    index--;
                } else {
                    return false;
                }
            }
        }
        if (index != 0) {
            return false;
        }
        return true;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * <p>
     * https://leetcode.cn/problems/climbing-stairs/
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /*
        假设f(x)代表x阶梯时的方法数量，则f(x-1)为x-1阶梯时的方法数量，f(x-2)则为x-2阶梯时的方法数量，
        此时f(x) = f(x-1) + f(x-2)
        因为(x-1)再迈一级阶梯就到达x阶梯，(x-2)迈二级阶梯到达x阶梯，所以到达x阶梯的方法应该是到达x-1阶梯和x-2阶梯的方法之和
         */
        int count = 0;
        int one = 2;
        int two = 1;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        for (int i = 3; i <= n; i++) {
            count = one + two;
            two = one;
            one = count;
        }
        return count;
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
     * <p>
     * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入:s = "abccccdd"
     * 输出:7
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * <p>
     * 链接：https://leetcode-cn.com/problems/longest-palindrome
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        // a-97 z-122 A-65 Z-90
        int[] flag = new int[58];
        int count = 0;
        for (char c : s.toCharArray()) {
            int index = c - 65;
            flag[index]++;
            int mo = flag[index] % 2;
            if (mo != 0) {
                count++;
            } else {
                count--;
            }
        }
        if (count > 1) {
            return s.length() - count + 1;
        }
        return s.length();
    }

    /**
     * https://leetcode-cn.com/problems/can-place-flowers/
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        if (n == 0) {
            return true;
        }
        if (flowerbed.length == 1 && flowerbed[0] == 0 && n == 1) {
            return true;
        }
        if (n > (flowerbed.length / 2) + 1) {
            return false;
        }
        for (int i = 0; i < flowerbed.length - 1; i++) {
            if (flowerbed[i] == 0 && ((i == 0 || flowerbed[i - 1] == 0) && flowerbed[i + 1] == 0)) {
                count++;
                flowerbed[i] = 1;
                i++;
            }
            if (count >= n) {
                return true;
            }
        }
        if (flowerbed.length >= 2 && flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
            count++;
        }
        if (count >= n) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * https://leetcode-cn.com/problems/final-prices-with-a-special-discount-in-a-shop/
     *
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        if (prices.length == 1) {
            return prices;
        }
        int[] left = new int[prices.length];
        int minPrices = 0;
        int minIndex = 0;
        boolean isMin = false;
        for (int i = 0; i < prices.length; i++) {
            if (minIndex > i && minPrices <= prices[i] && isMin) {
                left[i] = prices[i] - minPrices;
            } else {
                boolean minPricesChange = false;
                for (int j = i + 1; j < prices.length; j++) {
                    if (prices[j] <= prices[i]) {
                        if (prices[i] >= prices[i + 1]) {
                            isMin = true;
                        } else {
                            isMin = false;
                        }
                        left[i] = prices[i] - prices[j];
                        minPrices = prices[j];
                        minIndex = j;
                        minPricesChange = true;
                        break;
                    }
                }
                if (!minPricesChange) {
                    left[i] = prices[i];
                }
            }
        }
        return left;
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String str = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < str.length() && j < strs[i].length(); j++) {
                if (str.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            str = str.substring(0, j);
            if ("".equals(str)) {
                return str;
            }
        }
        return str;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String minStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            minStr = getLongestPrefix(minStr, strs[i]);
        }
        return minStr;
    }

    public String getLongestPrefix(String minStr, String lastMinStr) {
        char[] lastMinChars = lastMinStr.toCharArray();
        char[] minChars = minStr.toCharArray();
        StringBuilder sb = new StringBuilder("");
        int minLength = Math.min(minChars.length, lastMinChars.length);
        for (int i = 0; i < minLength; i++) {
            if (minChars[i] == lastMinChars[i]) {
                sb.append(minChars[i]);
            } else {
                break;
            }
        }
        return sb.toString();
    }

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
