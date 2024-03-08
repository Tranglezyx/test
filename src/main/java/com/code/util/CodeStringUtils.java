package com.code.util;

/**
 * @author trangle
 */
public class CodeStringUtils {

    public static void main(String[] args) {
        String str = "123";
        System.out.println(isPalindrome(str));
        str = "121";
        System.out.println(isPalindrome(str));
        str = "1234";
        System.out.println(isPalindrome(str));
        str = "1221";
        System.out.println(isPalindrome(str));
        str = "1234321";
        System.out.println(isPalindrome(str));
    }

    /**
     * 判断是否是回文数
     *
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str) {
        if (str.length() == 1) {
            return true;
        }
        int num = str.length() / 2;
        char[] chars = str.toCharArray();
        for (int i = 0; i < num; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
