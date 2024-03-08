package com.code.niuke;

import java.util.Scanner;

/**
 * 输出描述：
 * 字符中所有出现的数字前后加上符号“*”，其他字符保持不变
 * <p>
 * 示例1
 * 输入：
 * Jkdi234klowe90a3
 * 5151
 * 输出：
 * Jkdi*234*klowe*90*a*3*
 * *5151*
 *
 * @author trangle
 */
public class HJ96 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            char[] chars = sc.nextLine().toCharArray();
            int flag = -1;
            for (int i = 0; i < chars.length; i++) {
                int num = chars[i];
                // 字符0-9 ascii码为48-57
                if (num >= 48 && num <= 57) {
                    if(flag == -1){
                        flag = 0;
                    }
                    if (flag == 0) {
                        System.out.print("*");
                        flag = 1;
                    }
                } else if(flag == 1){
                    System.out.print("*");
                    flag = -1;
                }
                System.out.print(chars[i]);
                if(flag == 1 && i == chars.length - 1){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
