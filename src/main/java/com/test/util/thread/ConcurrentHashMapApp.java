package com.test.util.thread;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author trangle
 */
public class ConcurrentHashMapApp {

    private static final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1024);
    private static final HashMap hashMap = new HashMap(1024);

    public static void main(String[] args) {
//        for (int i = 0; i < 1000; i++) {
//            concurrentHashMap.put(String.valueOf(i),i);
//        }
        int x,y;
        x = 5 >> 2;
        y = x >>> 2;
        System.out.println(1 << 2);  // 4
        System.out.println(1 << 4);  // 8
        System.out.println(3 << 2);  // 12
        System.out.println(7 >> 2);  // 1
        System.out.println(15 >> 2);  // 3
    }
}
