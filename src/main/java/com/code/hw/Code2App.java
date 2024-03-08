package com.code.hw;

/**
 * @author trangle
 *
 * 查找字符串中最长连续数字子串
 */
public class Code2App {

    // 0 : 48   9 : 57
    public static void main(String[] args) {
        System.out.println("01234".substring(0,2));
    }

    public static int function(String str) {
        char[] chars = str.toCharArray();
        int maxLength = 0;
        int maxStartIndex = 0;

        int tmpMaxLength = 0;
        int tmpMaxStartIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            int num = (int) (chars[i]);
            if (num >= 48 && num <= 57) {
                tmpMaxLength++;
                if (i == 0) {
                    tmpMaxStartIndex = i;
                } else {
                    int beforeNum = (int) chars[i - 1];
                    if (beforeNum < 48 || beforeNum > 57) {
                        tmpMaxStartIndex = i;
                    }
                }
            } else if (tmpMaxLength >= maxLength) {
                maxLength = tmpMaxLength;
                maxStartIndex = tmpMaxStartIndex;

                tmpMaxLength = 0;
                tmpMaxStartIndex = 0;
            }
        }
        if (tmpMaxLength >= maxLength) {
            maxLength = tmpMaxLength;
            maxStartIndex = tmpMaxStartIndex;
        }
        if (maxLength == 0) {
            System.out.println("");
        } else {
            System.out.println(str.substring(maxStartIndex, maxStartIndex + maxLength));
        }
        return maxLength;
    }
}
