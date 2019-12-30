package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.esen.ecascore.casserver.LoginFunc;
import com.esen.ecascore.casserver.login.CasLoginFunc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.annotation.Column;
import com.test.entity.User;
import com.test.util.AnnotationUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws JsonProcessingException, IllegalAccessException, NoSuchFieldException {
        LoginFunc loginFunc = new CasLoginFunc();
        System.out.println(loginFunc);
//        List<User> userList = User.getDefaultUserListInfo();
//        System.out.println(userList.stream().map(User::getUserName).collect(Collectors.joining(",")));
//        System.out.println(userList.stream().collect(Collectors.toMap(User::getUserName,User::getMoney)));
//        System.out.println(userList.stream().map(User::getUserName).collect(Collectors.toList()));
//        System.out.println(userList.stream().collect(Collectors.groupingBy(User::getUserName)));
//        System.out.println(userList.stream()
//                .filter(user -> "qqq".equals(user.getUserName()))
//                .collect(Collectors.toList()));
//        List<User> tempList = new ArrayList<>();
//        tempList.stream().collect(Collectors.groupingBy(User::getUserName));

//        System.out.println(FieldUtils.getField(User.class, "userName", true).isAnnotationPresent(Column.class));
//        System.out.println(UUID.randomUUID().toString());

//        System.out.println(AnnotationUtils.getAnnotationValue(User.class, "userName", Column.class));
//        AnnotationUtils.updateFieldAnnotationValue(User.class, "userName", Column.class, "value", "qqq");
//        System.out.println(AnnotationUtils.getAnnotationValue(User.class, "userName", Column.class));

//        Map<String, Object> map = new HashMap<>();
//        map.put("11", new Date());
//        map.put("22", null);
//        System.out.println(JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm", SerializerFeature.WriteMapNullValue));
    }

    public static void test(String... str) {
        for (String s : str) {
            System.out.println(s);
        }
    }
}