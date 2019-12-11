package com.test.code.solution;

import java.util.HashSet;
import java.util.Set;

public class MediumSolution {

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
}
