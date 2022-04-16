package com.test.code;

import com.test.code.solution.EasySolution;
import com.test.code.util.SoutUtils;

import java.io.IOException;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        String s = "aabcaa";
        System.out.println(new EasySolution().longestPalindrome(s));
    }


}
