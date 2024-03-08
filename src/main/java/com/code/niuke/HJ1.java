package com.code.niuke;

import java.util.Scanner;

public class HJ1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String next = in.nextLine();
            int count = 0;
            for (int i = next.toCharArray().length - 1; i >= 0; i--) {
                char c = next.charAt(i);
                if (c == ' ') {
                    break;
                } else {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
