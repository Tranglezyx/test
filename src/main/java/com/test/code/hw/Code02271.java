package com.test.code.hw;

import java.util.Scanner;

/**
 * @author trangle
 */
public class Code02271 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            int index = 0;
            char[] chars = str.toCharArray();
            String result = "";
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == ' ') {
                    result = result + reverse(chars, index, i) + " ";
                    index = i;
                }
            }
            result = result + reverse(chars, index, chars.length);
            System.out.println(result);
        }
    }

    public static String reverse(char[] chars, int start, int end) {
        String result = "";
        String point = "";
        for (int j = end - 1; j >= start; j--) {
            char tmpChar = chars[j];
            if (tmpChar == ',' || tmpChar == '.' || tmpChar == '?') {
                point = tmpChar + point;
            } else {
                result = result + tmpChar;
            }
        }
        return result.trim() + point;
    }
}
