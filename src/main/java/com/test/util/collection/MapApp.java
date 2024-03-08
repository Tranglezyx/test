package com.test.util.collection;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouyx
 */
public class MapApp {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<String, String>() {{
            put("year", "1");
            put("a", "1");
            put("b", "1");
            put("c", "1");
        }};

        Map<String, String> map2 = new HashMap<String, String>() {{
            put("c", "1");
            put("year", "1");
            put("b", "1");
            put("a", "1");
        }};

        Map<String, String> map3 = new LinkedHashMap<String, String>() {{
            put("c", "1");
            put("year", "1");
            put("b", "1");
            put("a", "1");
        }};

        Map<String, String> map4 = new HashMap<>();
        map4.putAll(map3);

        System.out.println(JSON.toJSON(map1));
        System.out.println("---");
        System.out.println(JSON.toJSON(map2));
        System.out.println("---");
        System.out.println(JSON.toJSON(map3));
        System.out.println("---");
        System.out.println(JSON.toJSON(map4));
        System.out.println("---");
    }
}
