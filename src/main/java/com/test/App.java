package com.test;


import com.test.dto.User;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) {
        User user = new User(1L,"qqq");
        User user2 = new User(1L,"www");
        User user3 = new User(1L,"rrr");
        User user4 = new User(1L,"qqq");
        User user5 = new User(1L,"qqq");
        User user6 = new User(1L,"qqq");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        Map<String,List<User>> map = userList.stream().collect(Collectors.groupingBy(User::getUserName));

        String str = "string";
//        System.out.println(16 >>> 2);
//        System.out.println(1000 >> 1);

        Map<String,Object> hashTable = new Hashtable<>();
        hashTable.put("1",new Object());

        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("1",new Object());
        hashMap.put("2",new Object());
        hashMap.put("3",new Object());
        hashMap.put("4",new Object());

        int i = 16;
        int hash = 123;
        int hash2 = 122;
        int hash3 = 2123;
        int hash4 = 35242;
        int hash5 = 2344;
        System.out.println((i - 1) & hash);
        System.out.println((i - 1) & hash2);
        System.out.println((i - 1) & hash3);
        System.out.println((i - 1) & hash4);
        System.out.println((i - 1) & hash5);
    }


}