package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.entity.User;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws JsonProcessingException, IllegalAccessException, NoSuchFieldException, ParseException {

        User user = new User(1L,"111");
        User user2 = new User(2L,"222");
        User user3 = new User(3L,"333");
        User user4 = new User(4L,"444");

        User tmpUser = user;
        user = user2;
        System.out.println(tmpUser);
        System.out.println(user);

//        System.out.println(get(new int[]{1,2}));
    }

    public static int get(int[] nums) {
        for (int num : nums) {
            if (num == 1) {
                return num + 2;
            }
        }
        return 0;
    }
}