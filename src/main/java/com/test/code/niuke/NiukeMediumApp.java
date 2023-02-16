package com.test.code.niuke;

public class NiukeMediumApp {

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','0','0','0'}, {'0','1','0','1','1'}, {'0','0','0','1','1'}, {'0','0','0','0','0'}, {'0','0','1','1','1'}};
//        char[][] grid = new char[][]{{'1'}};
//        System.out.println(new NiukeMediumSolution().LIS(new int[]{4,2,1,2,4,3,2,4,5,6}));
        System.out.println(new NiukeMediumSolution().LCS1("41NY92514w8AF5q1sul7MVNFZnG","mt11NY92514w8AF5q1sul7MVNFZndJ"));
    }
}
