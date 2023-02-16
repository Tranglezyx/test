package com.test.code.niuke;

public class NiukeDifficultSolution {

    /**
     * 买卖股票的最好时机
     * https://www.nowcoder.com/practice/4892d3ff304a4880b7a89ba01f48daf9?tpId=295&tqId=1073487&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int one = 0;
        int two = 0;
        int tmpSum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int now = prices[i];
            int left = prices[i + 1];
            if (left > now) {
                tmpSum += left - now;
                if (tmpSum > two) {
                    two = Math.max(tmpSum, two);
                } else if (tmpSum > one) {
                    one = Math.max(tmpSum, one);
                }
            } else {
                tmpSum = 0;
            }
        }
        return one + two;
    }
}
