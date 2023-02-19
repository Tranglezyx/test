package com.test.code.hw;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int[] str2Count = new int[122 - 97 + 1];
        int str2Num = 0;
        for (char c : str2.toCharArray()) {
            int index = (int) c - 97;
            if (str2Count[index] == 0) {
                str2Count[index] = 1;
                str2Num++;
            }
        }
        String tmpStr = "";
        List<String> list = new ArrayList<>();
        for (char c : str1.toCharArray()) {
            int charValue = c;
            if ((charValue >= 97 && charValue <= 102) || (charValue >= 48 && charValue <= 57)) {
                if (!"".equals(tmpStr)) {
                    list.add(tmpStr);
                }
                tmpStr = "";
            } else {
                tmpStr += c;
            }
        }
        if (!"".equals(tmpStr)) {
            list.add(tmpStr);
        }
        int filterMaxLength = 0;
        List<String> filterList = new ArrayList<>();
        for (String str : list) {
            if (str.length() <= str2Num) {
                int[] tmp = new int[26];
                filterList.add(str);
                int tmpCount = 0;
                for (char c : str.toCharArray()) {
                    int tmpValue = (int) c - 97;
                    if (tmp[tmpValue] == 0) {
                        tmpCount++;
                        tmp[tmpValue] = 1;
                    }
                }
                filterMaxLength = Math.max(filterMaxLength, tmpCount);
            }
        }
        if (filterList.size() == 0) {
            System.out.println("Not Found");
            return;
        }
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < filterList.size(); i++) {
            String str = filterList.get(i);
            int[] tmp = new int[26];
            int tmpCount = 0;
            for (char c : str.toCharArray()) {
                int tmpValue = (int) c - 97;
                if(tmp[tmpValue] == 0){
                    tmpCount++;
                    tmp[tmpValue] = 1;
                }
            }
            if (tmpCount == filterMaxLength) {
                resultList.add(str);
            }
        }
        if (resultList.size() == 1) {
            System.out.println(resultList.get(0));
            return;
        }
        resultList = resultList.stream().sorted().collect(Collectors.toList());
        System.out.println(resultList.get(resultList.size() - 1));
    }
}
