package com.test.jvm;

import com.test.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trangle
 */
public class JvmApp {

    public static void main(String[] args) {
        String s = "ssss";
//        String s1 = new String("ssss");
//        List<JvmApp> list = new ArrayList<>();
//        int count = 0;
//        while (true) {
//            list.add(new JvmApp());
//            count++;
//        }
        User user =new User();
        User user1 =new User();
        System.out.println(user);
    }
}
