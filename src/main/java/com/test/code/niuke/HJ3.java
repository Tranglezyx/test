package com.test.code.niuke;

import java.util.Scanner;

/**
 * @author trangle
 */
public class HJ3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];
        int[] index = new int[501];
        for (int i = 0; i < length; i++) {
            int tmp = in.nextInt();
            array[i] = tmp;
            index[tmp] = 1;
        }
        for (int i = 0; i < index.length; i++) {
            if(index[i] != 0){
                System.out.println(i);
            }
        }
    }
}
