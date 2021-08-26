package com.test.code.hw;

/**
 * @author trangle
 */
public class Code3App {

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，
     * 请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，
     * 并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * <p>
     * {{'1','1','1','1','0'},
     * {'1','1','0','1','0'},
     * {'1','1','0','0','0'},
     * {'0','0','0','0','0'}}
     * 1
     * <p>
     * {{'1','1','0','0','0'},
     * {'1','1','0','0','0'},
     * {'0','0','1','0','0'},
     * {'0','0','0','1','1'}}
     * 3
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] lands = {
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1}
        };

        int count = 0;
        for (int i = 0; i < lands.length; i++) {
            int[] tmpArray = lands[i];
            for (int j = 0; j < tmpArray.length; j++) {
                if (lands[i][j] == 1) {
                    count++;
                    find(lands, i, j);
                }
            }
        }
        System.out.println(count);
    }

    public static void find(int[][] lands, int x, int y) {
        if (x < 0 || y < 0 || x >= lands.length || y >= lands[0].length || lands[x][y] != 1) {
            return;
        }
        lands[x][y] = 2;
        find(lands, x + 1, y);
        find(lands, x - 1, y);
        find(lands, x, y + 1);
        find(lands, x, y - 1);
    }
}
