package com.test;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, ParseException, InterruptedException {
        Map<String,Object> hashMap = new HashMap<>();
        Map<String,Object> hashTable = new Hashtable<>();
        hashMap.put(null,null);
        hashTable.put("null",null);
        System.out.println(hashMap.get(null));
        System.out.println(hashTable.get("null"));
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

class FinalizeTest {

    public static FinalizeTest finalizeTest = null;

    @Override
    protected void finalize() throws Throwable {
        System.out.println(">>>> finalize >>>>");
        finalizeTest = this;
    }
}