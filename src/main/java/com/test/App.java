package com.test;


import com.test.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.test();
    }


//    public static void test(){
//        try {
//            System.out.println("test  ------");
//            return;
//        }catch (Exception e ){
//            System.out.println("exception --------");
//        }finally {
//            System.out.println("finally ----------");
//        }
//    }
}