package com.test;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("dd");
        list.add("aa");
        list.add("ee");
        list.add("qq");
        System.out.println(list.stream().collect(Collectors.joining(";")));
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