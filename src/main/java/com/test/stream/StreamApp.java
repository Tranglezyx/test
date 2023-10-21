package com.test.stream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.test.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApp {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "qqq", new BigDecimal(20)));
        list.add(new User(1L, "www", new BigDecimal(50)));
        list.add(new User(1L, "eee", new BigDecimal(50)));
        list.add(new User(1L, "qqq", new BigDecimal(50)));
        list.add(new User(1L, "qqq", new BigDecimal(30)));
        list.add(new User(1L, "www", new BigDecimal(20)));
        list.add(new User(1L, "aaa", new BigDecimal(50)));
        Map<String, User> map = list.stream().collect(Collectors.toMap(User::getUserName, item -> item, StreamApp::add));
        Map<String, User> map1 = list.stream().collect(Collectors.toMap(User::getUserName, item -> item,(item1,item2) -> item1));
        System.out.println(JSONObject.toJSONString(map));
        System.out.println(JSONObject.toJSONString(map1));
    }

    public static User add(User user1, User user2) {
        return new User(user1.getUserId(), user1.getUserName(), user1.getMoney().add(user2.getMoney()));
    }
}
