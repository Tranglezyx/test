package com.test.util.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author trangle
 */
public class CollectionsThreadApp {

    public static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // 会出现并发问题
//        List<String> list = new ArrayList<>();
        // 不会出现
//        List<String> list =  Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
//        Lock lock = new ReentrantLock();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.writeLock().lock();
                lock.readLock().lock();
                list.add(UUID.randomUUID().toString().substring(0, 5));
                lock.writeLock().unlock();
                // 读
                System.out.println(list);
                lock.readLock().unlock();
            }, String.valueOf(i)).start();
        }
    }
}
