package com.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.annotation.Column;
import com.test.entity.User;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.*;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws JsonProcessingException {
//        List<User> userList = User.getDefaultUserListInfo();
//        System.out.println(userList.stream().map(User::getUserName).collect(Collectors.toList()));
//        System.out.println(userList.stream().collect(Collectors.groupingBy(User::getUserName)));
//        System.out.println(userList.stream()
//                .filter(user -> "qqq".equals(user.getUserName()))
//                .collect(Collectors.toList()));
//        List<User> tempList = new ArrayList<>();
//        tempList.stream().collect(Collectors.groupingBy(User::getUserName));

        System.out.println(FieldUtils.getField(User.class, "userName", true).isAnnotationPresent(Column.class));
    }
}