package com.test.code;

import com.test.code.solution.EasySolution;
import com.test.code.util.SoutUtils;

import java.io.IOException;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        int[] prices = new int[]{8,7,4,2,8,1,7,7,10,1};
        SoutUtils.printIntArray(new EasySolution().finalPrices(prices));
    }

}
