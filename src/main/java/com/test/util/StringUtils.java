package com.test.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * description
 * </p>
 *
 * @author yunxiang.zhou01@hand-china.com 2018/08/01 16:28
 */
public class StringUtils {

    /**
     * MD5加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newStr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    /**
     * 正则匹配，5表示为CustomUserDetails中变量长度最短的email,14为最长的organizationId的长度
     */
    private static final String PATTERN_REGEX = "#\\{(.+?)\\}";
    private static final Pattern PATTERN = Pattern.compile(PATTERN_REGEX);
    private static final String START_WITH = "#{";
    private static final String END_WITH = "}";

    /**
     * 获取字符串中被#{}包围的字符串
     *
     * @param str
     * @return
     */
    public static List<String> getFieldList(String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        Matcher matcher = PATTERN.matcher(str);
        List<String> fieldList = new ArrayList<>();
        while (matcher.find()) {
            fieldList.add(matcher.group());
        }
        return fieldList;
    }

    /**
     * 截取获得#{}中的值
     *
     * @param str
     * @return 截取值
     */
    static String getField(String str) {
        if (str != null) {
            if (str.startsWith(START_WITH) && str.endsWith(END_WITH)) {
                return str == null ? null : str.substring(START_WITH.length(), str.length() - END_WITH.length());
            } else {
                return str;
            }
        } else {
            return null;
        }
    }

    /**
     * 处理值
     *
     * @param object object
     * @return string
     */
    static String generateValueString(Object object) {
        if (object instanceof Long) {
            return object.toString();
        } else if (object instanceof Integer) {
            return object.toString();
        } else {
            return "'" + object.toString() + "'";
        }
    }
}
