package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.entity.User;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws JsonProcessingException, IllegalAccessException, NoSuchFieldException, ParseException {

        List<Integer> list1 = new ArrayList<Integer>() {{
            add(2);
            add(4);
        }};
        List<Integer> list2 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
//        Iterator<Integer> iterator = list2.iterator();
//        for (Integer integer1 : list1) {
//            while (iterator.hasNext()) {
//                Integer integer2 = iterator.next();
//                if (integer1.equals(integer2)) {
//                    iterator.remove();
//                }
//            }
//        }
        list2 = list2.stream()
                .filter(o -> !list1.contains(o)).collect(Collectors.toList());
        System.out.println(list2);

        List<Integer> list3 = new ArrayList<Integer>() {{
            add(2);
            add(4);
        }};
        List<Integer> list4 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        Iterator<Integer> iterator1 = list4.iterator();
        while (iterator1.hasNext()) {
            Integer integer4 = iterator1.next();
            for (Integer integer3 : list3) {
                if (integer3.equals(integer4)) {
                    iterator1.remove();
                    break;
                }
            }
        }
        System.out.println(list4);

        String str = "0000-0005-0001-";
        String str1 = "0000-0005-0001-";
        System.out.println(str1.startsWith(str));
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