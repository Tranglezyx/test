package com.code;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 写一个函数，传入字符串表达式，返回表达式的结果，如传入“30-100/5”（无括号），结果返回10
 *
 * @author trangle
 */
public class TestApp {

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
        int[] num = new int[]{1,2,3};
        int[] num2 = Arrays.copyOf(num,num.length);
        num2[0] = 2;
        System.out.println(num[0]);
        System.out.println(num2[0]);
//        BigDecimal bigDecimal = calculate("2+3+4*2*2-2/4");
//        System.out.println(bigDecimal);
    }

    public static BigDecimal calculate(String str) {
        int index = 0;
        List<String> addAndSubList = new ArrayList<>();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '+' || c == '-') {
                String value = str.substring(index, i);
                index = i + 1;
                addAndSubList.add(value);
                addAndSubList.add(c + "");
            }
        }
        BigDecimal result = null;
        addAndSubList.add(str.substring(index, str.length()));
        if (addAndSubList.size() == 0) {
            return mulAndDivide(str);
        } else {
            result = mulAndDivide(addAndSubList.get(0));
            String lastChar = "";
            for (int i = 1; i < addAndSubList.size(); i++) {
                String subString = addAndSubList.get(i);
                if ("+".equals(lastChar)) {
                    result = result.add(mulAndDivide(subString));
                }
                if ("-".equals(lastChar)) {
                    result = result.subtract(mulAndDivide(subString));
                }
                lastChar = subString;
            }
            String subString = addAndSubList.get(addAndSubList.size() - 1);
            if ("+".equals(lastChar)) {
                result = result.add(mulAndDivide(subString));
            }
            if ("-".equals(lastChar)) {
                result = result.subtract(mulAndDivide(subString));
            }
        }
        return result;
    }

    public static BigDecimal mulAndDivide(String str) {
        BigDecimal result = null;
        char[] charArray = str.toCharArray();
        char lastChar = '1';
        int index = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == '*' || c == '/') {
                String value = str.substring(index, i);
                index = i + 1;
                if (lastChar == '1') {
                    result = new BigDecimal(value);
                } else {
                    if (lastChar == '*') {
                        result = result.multiply(new BigDecimal(value));
                    }
                    if (lastChar == '/') {
                        result = result.divide(new BigDecimal(value));
                    }
                }
                lastChar = c;
            }
        }
        String value = str.substring(index, str.length());
        if (index == 0) {
            return new BigDecimal(value);
        }
        if (lastChar == '*') {
            result = result.multiply(new BigDecimal(value));
            return result;
        }
        if (lastChar == '/') {
            result = result.divide(new BigDecimal(value),4, RoundingMode.HALF_UP);
        }
        return result;
    }
}
