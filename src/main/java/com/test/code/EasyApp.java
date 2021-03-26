package com.test.code;

import com.test.code.solution.EasySolution;
import com.test.code.util.SoutUtils;

import java.io.IOException;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        int[] prices = new int[]{0,0,0,0,0,1,0,0};
        System.out.println(new EasySolution().canPlaceFlowers(prices, 0));
    }

}
