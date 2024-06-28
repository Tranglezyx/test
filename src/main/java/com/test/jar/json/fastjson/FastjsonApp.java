package com.test.jar.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/6/28 11:32
 * @Description:
 */
public class FastjsonApp {

    public static void main(String[] args) {
        String str = "[1,2,3]";
        Set<Long> set = new HashSet<>();
        set = JSONObject.parseObject(str,new TypeReference<Set<Long>>() {});
        System.out.println(set);
    }
}
