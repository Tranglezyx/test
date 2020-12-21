package com.test.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trangle
 */
public class CollectionsThreadApp {

    public static volatile List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // 会出现并发问题
//        List<String> list = new ArrayList<>();
        // 不会出现
//        List<String> list =  Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new Vector<>();
//        List<String> list = new CopyOnWriteArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                lock.lock();
                list.add(UUID.randomUUID().toString().substring(0, 5));
                lock.unlock();
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        System.out.println("总数据 -- " + list);
    }
}
