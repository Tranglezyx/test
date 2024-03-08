package com.code;

import java.util.Scanner;

/**
 * @author trangle
 */
public class Main {

    /**
     * a-97 z-122 A-65 Z-90 +:43 -:45  1:49 9:57
     * <p>
     * 分两种情况，在存在负数的情况下，正整数位数都为1
     * 不存在负数的情况下
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        String[] strArray = string.split(",");
        int people = Integer.parseInt(strArray[0]);
        int store = Integer.parseInt(strArray[1]);
        int[][] count = new int[people][2];
        for (int i = 0; i < people; i++) {
            string = sc.nextLine();
            strArray = string.split(",");
            count[i][0] = Integer.parseInt(strArray[0]);
            count[i][1] = Integer.parseInt(strArray[1]);
        }
        int maxStore = 0;
        int maxStoreCount = 0;
        int[] storeCount = new int[store + 1];
        for (int i = 0; i < count.length; i++) {
            int tmpStore = count[i][0];
            storeCount[tmpStore]++;
            if (storeCount[tmpStore] > maxStoreCount) {
                maxStore = tmpStore;
                maxStoreCount = storeCount[tmpStore];
            }
            if (storeCount[tmpStore] == maxStoreCount && tmpStore != 1) {
                maxStore = tmpStore;
                maxStoreCount = storeCount[tmpStore];
            }
        }
        if (maxStore == 1) {
            System.out.println(0);
        } else {
            System.out.println("其他情况");
        }
    }

    public static void function1() {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        String[] strArray = string.split(",");
        int max = 0;
        for (int i = 0; i < strArray.length; i++) {
            for (int j = i + 1; j < strArray.length; j++) {
                char[] chars = strArray[i].toCharArray();
                char[] nextChars = strArray[j].toCharArray();
                boolean flag = false;
                for (char aChar : chars) {
                    for (char nextChar : nextChars) {
                        if (aChar == nextChar) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
                if (!flag) {
                    max = Math.max(chars.length * nextChars.length, max);
                }
            }
        }
        System.out.println(max);
    }

    public static void function2() {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        int minCount = 0;
        boolean isMinus = false;
        for (int i = 0; i < chars.length; i++) {
            int tmp = (int) chars[i];
            if ((tmp >= 97 && tmp <= 122) || ((tmp >= 65 && tmp <= 90)) || tmp == 43) {
                if (sb.length() > 1) {
                    minCount += Integer.parseInt(sb.toString());
                }
                sb = new StringBuilder("");
                isMinus = false;
                continue;
            }
            if (tmp == 43 || tmp == 45) {
                if (sb.length() > 1) {
                    minCount += Integer.parseInt(sb.toString());
                    sb = new StringBuilder("");
                }
            }
            if (tmp == 45) {
                if (sb.length() == 0) {
                    sb.append(chars[i]);
                }
                // 如果是负号
                isMinus = true;
                continue;
            }
            int tmpNum = tmp - 48;
            if (tmpNum >= 0 & tmpNum <= 9 && !isMinus) {
                minCount += tmpNum;
            } else if (tmpNum >= 0 & tmpNum <= 9 && isMinus) {
                sb.append(tmpNum);
            }
        }
        if (sb.length() > 1) {
            minCount += Integer.parseInt(sb.toString());
        }
        System.out.println(minCount);
    }
}
