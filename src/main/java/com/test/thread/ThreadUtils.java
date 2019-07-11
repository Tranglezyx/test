package com.test.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author trangle
 */
public class ThreadUtils {

    public static final ConcurrentHashMap<Long, Object> map = new ConcurrentHashMap(100000);

    public static volatile int num = 0;

    public static volatile boolean flag = true;

    private static int count = 500;

    public static final Object object = new Object();

    public static void put(int num) {
        if (map.get(Long.valueOf(num)) != null) {
            System.out.println("出现脏数据了 ： " + num);
        } else {
            map.put(Long.valueOf(num), object);
        }
    }

    public static void numAdd(){
        num++;
    }

    public static void sout(){
//        synchronized (ThreadUtils.class){
            while(num < count){
                ThreadUtils.put(num++);
            }
//        }
    }
}
