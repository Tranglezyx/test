package com.test.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/5/15 18:04
 * @Description:
 */
public class HttpUrlPickApp {

    public static void main(String[] args) {
        String str = "【测试】今天是个好日子http://www.baidu.com拒收请回复R";
        String str1 = "【测试】今天是个好日子www.baidu.com拒收请回复R";
//        String str1 = "【测试】今天是个好日子i3z.cc/XawDOP4s拒收请回复R";

//        String patternStr = "https?:\\/\\/[^\\s/$.?#].[^\\s]*";
        String patternStr = "(?:http|https):\\/\\/";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group());
        }

        Matcher matcher1 = pattern.matcher(str1);
        System.out.println(matcher1.find());

    }
}
