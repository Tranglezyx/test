package com.test.java8;

import com.test.domain.entity.User;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/6/18 15:50
 * @Description:
 */
public class FunctionApp {

    public static void main(String[] args) {
        User user = new User();
        set("111",user::setUserName);
        System.out.println(user);
        set("111",user::setPassword);
        System.out.println(user);

        get(user,User::getUserName);
        get(user,User::getPassword);
    }

    public static void set(String value, Consumer<String> function){
        function.accept(value);
    }

    public static void get(User user,Function<User,String> function){
        String apply = function.apply(user);
        System.out.println(apply);
    }
}
