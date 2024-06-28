package com.test.util.string;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/5/11 11:40
 * @Description:
 */
public class StrApp {

    public static void main(String[] args) {
        int size = 4;
        int i = 1;
        System.out.println(String.format("%04d",i));

        String str = "【维信金科】尊敬的客户，您来电咨询的业务已办理成功，请您留意。\\n";
        System.out.println(str.length());
        String str1 = "【维信金科】尊敬的客户，您来电咨询的业务已办理成功，请您留意。\\n";
        System.out.println(str1.length());
    }
}
