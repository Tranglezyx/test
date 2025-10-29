package com.test.util.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/5/11 11:40
 * @Description:
 */
public class TemplateApp {

    public static class TemplateResult {
        public final String template;
        // variables.get(i) 是第 i 条文本对应的变量值列表，按模板中的 {1},{2},... 顺序
        public final List<List<String>> variables;
        public TemplateResult(String template, List<List<String>> variables) {
            this.template = template;
            this.variables = variables;
        }
    }

    public static void main(String[] args) {
        List<String> texts = Arrays.asList(
                "321位于S3asdf6+900的水井湾站1检测站的数据发生中断，中断时间长达2个小时ff",
                "位于S30234259010的水井站s检测站的数据发生中断，中断时间长达4个小时ff",
                "axcv位于S3xcvK56+900的水井湾站szc检测站的数据发生中断，中断时间长达3个小时dd",
                "sad位于S3adf6+900的水井湾站vzxc检测站的数据发生中断，中断时间长达1个小时hgg"
//                ,
//                "李四，今天是您旺旺贷的借款账单最后还款日，应还金额1605.60元，请务必于今天14:00前足额还款，以免过期未还产生不必要的费用。",
//                "张三，今天是您旺旺贷的借款账单最后还款日，应还金额2508.75元，请务必于今天14:00前足额还款，以免过期未还产生不必要的费用。"
        );

        TemplateResult res = extractTemplateAndVariables(texts);

        System.out.println("模板：");
        System.out.println(res.template);
        System.out.println("\n每条文本的变量值：");
        for (int i = 0; i < texts.size(); i++) {
            System.out.println("行 " + (i+1) + ": " + texts.get(i));
            List<String> vals = res.variables.get(i);
            for (int j = 0; j < vals.size(); j++) {
                System.out.println("  {" + (j+1) + "} = " + vals.get(j));
            }
            System.out.println();
        }
    }

    public static TemplateResult extractTemplateAndVariables(List<String> texts) {
        if (texts == null || texts.isEmpty()) {
            return new TemplateResult("", Collections.emptyList());
        }

        // 1) 找到按顺序的“锚点”列表（在所有文本中都出现的最长公共子串，递归拆分）
        List<String> anchors = findAnchorsRecursive(texts);

        int n = texts.size();
        int m = anchors.size();

        // prevEnds 保存每条文本上一次锚点结束后的索引（起始为0）
        int[] prevEnds = new int[n];
        List<List<String>> varValues = new ArrayList<>();
        for (int i = 0; i < n; i++) varValues.add(new ArrayList<>());

        StringBuilder template = new StringBuilder();
        int varIndex = 1;

        // 对每个锚点，检查前面的“between”部分是否在所有文本中都相同
        for (int k = 0; k < m; k++) {
            String anchor = anchors.get(k);
            int[] startIdx = new int[n];
            for (int i = 0; i < n; i++) {
                startIdx[i] = texts.get(i).indexOf(anchor, prevEnds[i]);
                if (startIdx[i] == -1) startIdx[i] = prevEnds[i]; // 容错（理论上不应发生）
            }

            // 取每条文本 prevEnds[i] 到 startIdx[i] 的片段作为 between
            String firstBetween = texts.get(0).substring(prevEnds[0], startIdx[0]);
            boolean allEqual = true;
            for (int i = 1; i < n; i++) {
                String b = texts.get(i).substring(prevEnds[i], startIdx[i]);
                if (!firstBetween.equals(b)) { allEqual = false; break; }
            }

            if (allEqual) {
                if (!firstBetween.isEmpty()) template.append(firstBetween);
            } else {
                // 作为变量
                template.append("{").append(varIndex).append("}");
                for (int i = 0; i < n; i++) {
                    varValues.get(i).add(texts.get(i).substring(prevEnds[i], startIdx[i]));
                }
                varIndex++;
            }

            // 加上锚点文本
            template.append(anchor);

            // 更新 prevEnds
            for (int i = 0; i < n; i++) {
                prevEnds[i] = startIdx[i] + anchor.length();
            }
        }

        // 处理尾部（最后一个锚点之后的部分）
        String firstTrailing = texts.get(0).substring(prevEnds[0]);
        boolean trailingEqual = true;
        for (int i = 1; i < n; i++) {
            if (!firstTrailing.equals(texts.get(i).substring(prevEnds[i]))) { trailingEqual = false; break; }
        }
        if (trailingEqual) {
            if (!firstTrailing.isEmpty()) template.append(firstTrailing);
        } else {
            template.append("{").append(varIndex).append("}");
            for (int i = 0; i < n; i++) {
                varValues.get(i).add(texts.get(i).substring(prevEnds[i]));
            }
        }

        return new TemplateResult(template.toString(), varValues);
    }

    // 递归找到按顺序出现的锚点（Longest common substring 全局贪心）
    private static List<String> findAnchorsRecursive(List<String> texts) {
        String anchor = longestCommonSubstring(texts);
        if (anchor == null || anchor.isEmpty()) return Collections.emptyList();

        List<String> lefts = new ArrayList<>();
        List<String> rights = new ArrayList<>();
        for (String t : texts) {
            int idx = t.indexOf(anchor);
            lefts.add(t.substring(0, idx));
            rights.add(t.substring(idx + anchor.length()));
        }

        List<String> res = new ArrayList<>();
        res.addAll(findAnchorsRecursive(lefts));
        res.add(anchor);
        res.addAll(findAnchorsRecursive(rights));
        return res;
    }

    // 在所有字符串中寻找最长公共子串（简单实现，适合中短文本）
    private static String longestCommonSubstring(List<String> texts) {
        if (texts == null || texts.isEmpty()) return "";
        // 选最短字符串遍历其所有子串（从长到短）
        String shortest = texts.get(0);
        for (String s : texts) if (s.length() < shortest.length()) shortest = s;
        int L = shortest.length();
        for (int len = L; len >= 1; len--) {
            for (int start = 0; start <= L - len; start++) {
                String sub = shortest.substring(start, start + len);
                boolean ok = true;
                for (String t : texts) {
                    if (t.indexOf(sub) == -1) { ok = false; break; }
                }
                if (ok) return sub;
            }
        }
        return "";
    }

}
