package com.test.collection;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;

@Slf4j
public class LinkedHashSetApp {


    public static void main(String[] args) {
        LinkedHashSet<Object> hashSet = new LinkedHashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(7);
        hashSet.add(4);
        hashSet.add(3);
        log.info(JSONObject.toJSONString(hashSet));

        LinkedHashSet linkedHashSet = JSONObject.parseObject("[1,2,7,4,3]", LinkedHashSet.class);
        log.info(linkedHashSet.toString());
    }
}
