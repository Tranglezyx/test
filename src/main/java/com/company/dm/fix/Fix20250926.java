package com.company.dm.fix;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/9/26 10:57
 * @Description:
 */
public class Fix20250926 {

    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("C:\\Users\\Trangle\\Downloads\\error20250926.txt"));
        List<String> jsonList = new ArrayList<>();
        for (String s : list) {
            jsonList.addAll(extracted(s));
        }
        System.out.println(num);
//        System.out.println(set.stream().map(item -> "'" + item + "'").collect(Collectors.joining(",")));
    }

    static int num = 0;
    static Set<String> set = new HashSet<>();

    private static List<String> extracted(String str) {
        String start = "chmn下行消费异常,数据:";
        String json = str.substring(str.indexOf(start) + start.length(), str.lastIndexOf(",错误:"));
        List<String> list = JSONObject.parseArray(json, String.class);
        List<String> resultList = new ArrayList<>();
        for (String s : list) {
            JSONObject jsonObject = JSONObject.parseObject(s);
            Long developerId = jsonObject.getLong("developerId");
            if(developerId == 511749){
                resultList.add(jsonObject.toJSONString());
                num++;
            }
        }
        return resultList;
    }
}
