package com.code.hw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author trangle
 */
public class Code02272 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        int k = in.nextInt();
        calculate("ab", "aab", 1);
    }

    public static void calculate(String s1, String s2, int k) {
        if (s2.length() < s1.length() + k) {
            System.out.println(-1);
            return;
        }
        int index[] = new int[26];
        for (int i = 0; i < s1.toCharArray().length; i++) {
            int charValue = s1.charAt(i) - 97;
            index[charValue]++;
        }
        char[] chars = s2.toCharArray();
        for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
            int[] tmpIndex = Arrays.copyOf(index, index.length);
            int s1Length = s1.length();
            for (int j = i; j < i + s1.length(); j++) {
                int charValue = (int) chars[j] - 97;
                if (tmpIndex[charValue] > 0) {
                    s1Length--;
                    tmpIndex[charValue]--;
                }
            }
            if (s1Length == 0 && i > k) {
                System.out.println(i-k);
                return;
            }
        }
        System.out.println(-1);
    }

    public static void calculate2(String s1, String s2, int k) {
        if (s2.length() < s1.length() + k) {
            System.out.println(-1);
            return;
        }
        int index[] = new int[26];
        for (int i = 0; i < s1.toCharArray().length; i++) {
            int charValue = s1.charAt(i) - 97;
            index[charValue]++;
        }
        char[] chars = s2.toCharArray();
        for (int i = 0; i < s2.length() - k - s1.length() + 1; i++) {
            int[] tmpIndex = Arrays.copyOf(index, index.length);
            int s1Length = s1.length();
            for (int j = i; j < i + k + s1.length(); j++) {
                int charValue = (int) chars[j] - 97;
                if (tmpIndex[charValue] > 0) {
                    s1Length--;
                    tmpIndex[charValue]--;
                }
            }
            if (s1Length == 0) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
