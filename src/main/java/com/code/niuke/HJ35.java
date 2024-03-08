package com.code.niuke;

import java.util.Scanner;

/**
 * 描述
 * 蛇形矩阵是由1开始的自然数依次排列成的一个矩阵上三角形。
 * <p>
 * 例如，当输入5时，应该输出的三角形为：
 * <p>
 * 1 3 6 10 15
 * <p>
 * 2 5 9 14
 * <p>
 * 4 8 13
 * <p>
 * 7 12
 * <p>
 * 11
 *
 * @author trangle
 */
public class HJ35 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int num = sc.nextInt();
            int[][] result = new int[num][num];
            result[0][0] = 1;
            for (int i = 0; i < num; i++) {
                int lineAdd = 2 + i;
                if (i > 0) {
                    result[i][0] = result[i - 1][0] + i;
                }
                for (int j = 1; j < num - i; j++) {
                    result[i][j] = result[i][j - 1] + lineAdd;
                    lineAdd++;
                }
            }
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if(result[i][j] != 0){
                        System.out.print(result[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
