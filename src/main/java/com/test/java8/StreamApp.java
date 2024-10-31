package com.test.java8;

import com.alibaba.fastjson.JSONObject;
import com.test.domain.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/10/31 14:41
 * @Description:
 */
@Slf4j
public class StreamApp {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "qqq", new BigDecimal(1)));
        list.add(new User(1L, "qqq", new BigDecimal(2)));
        list.add(new User(1L, "qqq", new BigDecimal(3)));
        list.add(new User(1L, "qqq", new BigDecimal(4)));

        list.add(new User(1L, "www", new BigDecimal(1)));
        list.add(new User(1L, "ww", new BigDecimal(1)));
        list.add(new User(1L, "www", new BigDecimal(2)));
        list.add(new User(1L, "qqq", new BigDecimal(5)));
        list.add(new User(1L, "www", new BigDecimal(3)));
        list.add(new User(1L, "qqq", new BigDecimal(6)));

        Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getUserName));
        log.info("json：{}", JSONObject.toJSONString(collect));
    }
}
