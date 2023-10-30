package com.test.string;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TemplateExtractionApp {

    private static int levenshteinDistance(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 计算莱茵斯坦距离
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[m][n];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }


    public static List<String> extractSimilarText(String text1, String text2, int threshold) {
        text1 = text1.trim();
        text2 = text2.trim();

        int levenshteinDistance = levenshteinDistance(text1, text2);

        if (levenshteinDistance <= threshold) {
            List<String> similarText = new ArrayList<>();
            for (int i = 0; i < text1.length(); i++) {
                if (text1.charAt(i) == text2.charAt(i)) {
                    similarText.add(String.valueOf(text1.charAt(i)));
                }
            }

            return similarText;
        } else {
            return new ArrayList<>();
        }
    }

    public static List<String> extractTextTemplate(String text1, String text2, int threshold) {
        // 将文本分词
        List<String> tokens1 = Splitter.on(" ").splitToList(text1);
        List<String> tokens2 = Splitter.on(" ").splitToList(text2);

        // 计算两个文本中相同的词
        Map<String, Integer> similarTokens = new HashMap<>();
        for (String token1 : tokens1) {
            for (String token2 : tokens2) {
                if (token1.equals(token2)) {
                    similarTokens.put(token1, similarTokens.getOrDefault(token1, 0) + 1);
                }
            }
        }

        // 构建文本模板
        List<String> textTemplate = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : similarTokens.entrySet()) {
            if (entry.getValue() >= threshold) {
                textTemplate.add(entry.getKey());
            }
        }

        return textTemplate;
    }

    public static void main(String[] args) {
        String text1 = "今天天气很好";
        String text2 = "今天天气晴朗";

        List<String> textTemplate = extractTextTemplate(text1, text2, 1);
        System.out.println(textTemplate);
    }
}
