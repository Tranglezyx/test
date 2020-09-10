package com.test.code.solution;

import com.test.code.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

public class MediumSolution {

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode iterator = head;
        int i = 1;
        // 前节点，如果只有节点，则为自身
        ListNode preNode = iterator;
        while (iterator.next != null) {
            // 下一个节点
            ListNode nextNode = iterator.next;
            // 当前节点
            ListNode nowNode = iterator;
            if (i % 2 == 0) {
                nowNode.next = preNode;
                preNode.next = nextNode;
            }
            if (i % 2 != 0 && nowNode.next != null && nowNode.next.next != null && i > 1) {
                nowNode.next = nowNode.next.next;
            }
            preNode = iterator;
            iterator = iterator.next;
            i++;
        }
        return head;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] charArray = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int maxSubLength = 0;
        for (int i = 0; i < charArray.length; i++) {
            int tempLength = 0;
            for (int j = i; j < charArray.length; j++) {
                if (set.add(charArray[j])) {
                    ++tempLength;
                    maxSubLength = maxSubLength > tempLength ? maxSubLength : tempLength;
                } else {
                    set.clear();
                    break;
                }
            }
        }
        return maxSubLength;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        int maxSubLength = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                if (isPalindrome(s.substring(i, j))) {
                    int tempLength = j - i;
                    if (tempLength > maxSubLength) {
                        startIndex = i;
                        endIndex = j;
                        maxSubLength = tempLength;
                    }
                }
            }
        }
        return s.substring(startIndex, endIndex);
    }

    /**
     * 判断是否是回文字符串
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.length() == 1) {
            return true;
        }
        if (s.length() % 2 == 0) {
            // 偶数长度
            String rightStr = s.substring(0, s.length() / 2);
            String leftStr = s.substring(s.length() / 2, s.length());
            if (!isReverse(rightStr, leftStr)) {
                return false;
            }
            return true;
        } else {
            // 奇数长度
            String rightStr = s.substring(0, s.length() / 2);
            String leftStr = s.substring(s.length() / 2 + 1, s.length());
            if (!isReverse(rightStr, leftStr)) {
                return false;
            }
            return true;
        }
    }

    /**
     * 是否倒序
     *
     * @param rightStr
     * @param leftStr
     * @return
     */
    public static boolean isReverse(String rightStr, String leftStr) {
        for (int i = 0, j = leftStr.length() - 1; i < rightStr.length(); i++, j--) {
            if (rightStr.charAt(i) != leftStr.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        return 0;
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * <p>
     * 输入:
     * [
     * ['1','1','0','0','0'],
     * ['1','1','0','0','0'],
     * ['0','0','1','0','0'],
     * ['0','0','0','1','1']
     * ]
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int count = 0;
        int[][] searched = new int[grid.length][100];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 未搜索过且为陆地
                if (grid[i][j] == '1' && searched[i][j] == 0) {
                    findIsland(grid, searched, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void findIsland(char[][] grid, int[][] searched, int i, int j) {
        // 超出边界值或者已搜索过或不为陆地直接返回
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || searched[i][j] == 1 || grid[i][j] == '0') {
            return;
        }
        if (grid[i][j] == '1') {
            searched[i][j] = 1;
            findIsland(grid, searched, i, j - 1);
            findIsland(grid, searched, i - 1, j);
            findIsland(grid, searched, i, j + 1);
            findIsland(grid, searched, i + 1, j);
        }
    }
}
