package com.test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.ToLongFunction;

import com.test.annotation.Column;
import com.test.annotation.Table;
import com.test.dto.User;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @author Trangle Hello world!
 */
public class App {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(2L, "QQQ"));
        userList.add(new User(5L, "WWW"));
        userList.add(new User(1L, "EEE"));
        userList.add(new User(1L, "AAA"));

        Map<String, User> map = new HashMap();
        Map<String,User> linkMap = new LinkedHashMap<>();
        for (User user : userList) {
            map.put(user.getUserName(), user);
            linkMap.put(user.getUserName(), user);
        }
        System.out.println(userList);
        userList.sort(Comparator.comparingLong((ToLongFunction<? super User>) User::getUserId));
        System.out.println(userList);
        System.out.println(userList.get(0).getBool());
    }
}
