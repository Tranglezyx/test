package com.test;


import com.test.util.CommUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str = CommUtils.sha256("111111");
        System.out.println(CommUtils.encoderPasswordByECP("ADMIN","111111"));
        System.out.println(CommUtils.sha256("CRCLDAP+ADMIN+" + str));
        System.out.println("CB179A32B377E2251BD507F1A90D7CF38C96FD602C478DC90B739868210E9B43");
//        System.out.println(Base64.getEncoder().encodeToString("CRC1234".getBytes()));
//        System.out.println(new String(Base64.getDecoder().decode("Q1JDMTIzNA==")));
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