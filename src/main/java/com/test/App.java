package com.test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ParseException, InterruptedException {
        String patternStr = "#\\{(.+?)\\}";
        Pattern pattern = Pattern.compile(patternStr);
        String str = "afkasusernamealkdjfk今天#{userName}#{email}";
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "zhangsan");
        map.put("email", "lisi");

        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            str = str.replace(group, map.get(group.substring("#{".length(), group.length() - 1)).toString());
        }
        System.out.println(str);

    }
}