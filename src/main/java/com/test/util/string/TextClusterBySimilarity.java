package com.test.util.string;

import java.util.*;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/10 17:43
 * @Description:
 */
public class TextClusterBySimilarity {

    public static void main(String[] args) {
        List<String> texts = Arrays.asList(
                "321位于S3asdf6+900的水井湾站1检测站的数据发生中断，中断时间长达2个小时ff",
                "af位于S30234256+900的水井站s检测站的数据发生中断，中断时间长达4个小时ff",
                "axcv位于S3xcvK56+900的水井湾站szc检测站的数据发生中断，中断时间长达3个小时dd",
                "sad位于S3adf6+900的水井湾站vzxc检测站的数据发生中断，中断时间长达1个小时hgg",
                "李四，今天是您旺旺贷的借款账单最后还款日，应还金额1605.60元，请务必于今天14:00前足额还款，以免过期未还产生不必要的费用。",
                "张三，今天是您旺旺贷的借款账单最后还款日，应还金额2508.75元，请务必于今天14:00前足额还款，以免过期未还产生不必要的费用。",
                "【志愿汇】验证码:7141，有效期为60分钟。",
                "【志愿汇】验证码:1231，有效期为60分钟。",
                "【志愿汇】验证码:4531，有效期为60分钟。"
        );

        double threshold = 0.6; // 相似度阈值（可调）
        List<List<String>> clusters = clusterBySimilarity(texts, threshold);

        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("组 " + (i + 1) + ":");
            for (String t : clusters.get(i)) {
                System.out.println("  " + t);
            }
            System.out.println();
        }
    }

    /**
     * 基于编辑距离的文本聚类
     */
    public static List<List<String>> clusterBySimilarity(List<String> texts, double threshold) {
        int n = texts.size();
        boolean[] visited = new boolean[n];
        List<List<String>> clusters = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            List<String> group = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                group.add(texts.get(cur));
                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        double sim = similarity(texts.get(cur), texts.get(j));
                        if (sim >= threshold) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
            clusters.add(group);
        }
        return clusters;
    }

    /**
     * 计算两个字符串的相似度（1 - 编辑距离比）
     */
    public static double similarity(String a, String b) {
        int dist = levenshteinDistance(a, b);
        int maxLen = Math.max(a.length(), b.length());
        if (maxLen == 0) return 1.0;
        return 1.0 - (double) dist / maxLen;
    }

    /**
     * Levenshtein 编辑距离算法
     */
    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
