package com.test;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(8);
        list.add(2);
        list.add(3);
        list.sort(Comparator.comparingInt(Integer::intValue).reversed());
        System.out.println(list);
    }
}