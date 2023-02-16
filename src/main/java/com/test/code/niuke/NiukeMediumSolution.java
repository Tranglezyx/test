package com.test.code.niuke;

import java.util.ArrayList;
import java.util.Arrays;

public class NiukeMediumSolution {

    /**
     * 描述
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
     * 题目保证str1和str2的最长公共子串存在且唯一。
     * <p>
     * longest common substring
     * <p>
     * "12345a654321","123451a654321"
     * <p>
     * "1AB2345CD","12345EF"
     * "41NY92514w8AF5q1sul7MVNFZnG","mt11NY92514w8AF5q1sul7MVNFZndJ"
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS1(String str1, String str2) {
        int[] maxIndex = new int[2];
        int maxLength = 0;
        for (int i = 0; i < str1.toCharArray().length; i++) {
            int[] tmpIndex = new int[2];
            int tmpLength = 0;
            int index = 0;
            for (int j = 0; j < str2.toCharArray().length; j++) {
                if (i + index < str1.length() && str1.charAt(i + index) == str2.charAt(j)) {
                    index++;
                    if (index > tmpLength) {
                        tmpLength = index;
                        tmpIndex[0] = i;
                        tmpIndex[1] = i + index;
                    }
                } else {
                    index = 0;
                    if (i + index < str1.length() && str1.charAt(i + index) == str2.charAt(j)) {
                        index++;
                        if (index > tmpLength) {
                            tmpLength = index;
                            tmpIndex[0] = i;
                            tmpIndex[1] = i + index;
                        }
                    }
                }
            }
            if (maxLength < tmpLength) {
                maxIndex = tmpIndex;
                maxLength = tmpLength;
            }
        }
        return str1.substring(maxIndex[0], maxIndex[1]);
    }

    /**
     * 给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
     * 所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
     * <p>
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 输入：6,3,1,5,2,3,7
     * 1,1,1,2,2,3,4
     * 返回：4
     * <p>
     * 4,2,1,2,4,3,2,4,5,6
     *
     * <p>
     * 给定数组的最长严格上升子序列的长度。
     *
     * @param arr int整型一维数组 给定的数组
     * @return int整型
     */
    public int LIS(int[] arr) {
        int max = 0;
        int[] value = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            value[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    int tempMax = value[j] + 1;
                    value[i] = Math.max(tempMax, value[i]);
                }
            }
            max = Math.max(max, value[i]);
        }
        return max;
    }

    /**
     * 描述
     * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
     * <p>
     * 1ab2c3d45zxcvbnefg
     * 1a2becrd345zxcvbnefg
     *
     * @param s1
     * @param s2
     * @return
     */
    public String LCS(String s1, String s2) {
        int length = 0;
        String str = null;
        String other = null;
        if (s1.length() < s2.length()) {
            length = s1.length();
            str = s1;
            other = s2;
        } else {
            length = s2.length();
            str = s2;
            other = s1;
        }
        StringBuilder max = new StringBuilder();
        StringBuilder tmpMax = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < other.length(); j++) {
                char otherC = other.charAt(j);
                if (c == otherC) {
//                    tmpMax =
                }
            }
        }
        return "";
    }

    /**
     * 判断岛屿数量
     *
     * @param grid char字符型二维数组
     * @return int整型
     */
    public int solve(char[][] grid) {
        int count = 0;
        int[][] index = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && index[i][j] == 0) {
                    dfsSolve(grid, i, j, index);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfsSolve(char[][] grid, int i, int j, int[][] index) {
        if (i == -1 || j == -1 || i == grid.length || j == grid[i].length || grid[i][j] == '0' || index[i][j] == 1) {
            return;
        }
        index[i][j] = 1;
        dfsSolve(grid, i + 1, j, index);
        dfsSolve(grid, i, j + 1, index);
        dfsSolve(grid, i - 1, j, index);
        dfsSolve(grid, i, j - 1, index);
    }

    /**
     * 有重复项数字的全排列
     * 输入：
     * [1,1,2]
     * 返回值：
     * [[1,1,2],[1,2,1],[2,1,1]]
     *
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> arrays = new ArrayList<>();
        Arrays.sort(num);
        int[] childIndexCount = new int[8];
        dfsPermuteUnique2(num, arrays, list, childIndexCount);
        return list;
    }

    public void dfsPermuteUnique2(int[] num, ArrayList<Integer> arrays, ArrayList<ArrayList<Integer>> list, int[] childIndexCount) {
        if (arrays.size() == num.length && !list.contains(arrays)) {
            list.add(new ArrayList<>(arrays));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            int value = num[i];
            int temp = value + 1;
            if (childIndexCount[temp] < 1) {
                arrays.add(value);
                childIndexCount[temp]++;
                dfsPermuteUnique2(num, arrays, list, childIndexCount);
                childIndexCount[temp]--;
                arrays.remove(arrays.size() - 1);
            }
        }
    }

    // 算法应该是对的，就是超时了
    public void dfsPermuteUnique(int[] num, ArrayList<Integer> arrays, ArrayList<ArrayList<Integer>> list, int[] valueCount, int[] childValueCount) {
        if (arrays.size() == num.length && !list.contains(arrays)) {
            list.add(new ArrayList<>(arrays));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            int value = num[i];
            int temp = value + 1;
            if (childValueCount[temp] < valueCount[temp]) {
                arrays.add(value);
                childValueCount[temp]++;
                dfsPermuteUnique(num, arrays, list, valueCount, childValueCount);
                childValueCount[temp]--;
                arrays.remove(arrays.size() - 1);
            }
        }
    }

    /**
     * 没有重复项数字的全排列
     * <p>
     * 描述
     * 给出一组数字，返回该组数字的所有排列
     * 例如：
     * [1,2,3]的所有排列如下
     * [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], [3,2,1].
     * （以数字在数组中的位置靠前为优先级，按字典序排列输出。）
     * <p>
     * 示例1
     * 输入：
     * [1,2,3]
     * 返回值：
     * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> arrays = new ArrayList<>();
        dfsPermute(num, arrays, list);
        return list;
    }

    public void dfsPermute(int[] num, ArrayList<Integer> arrays, ArrayList<ArrayList<Integer>> list) {
        if (arrays.size() == num.length) {
            list.add(new ArrayList<>(arrays));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            int value = num[i];
            if (!arrays.contains(value)) {
                arrays.add(value);
                dfsPermute(num, arrays, list);
                arrays.remove(arrays.size() - 1);
            }
        }
    }
}
