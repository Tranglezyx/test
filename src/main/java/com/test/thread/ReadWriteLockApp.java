package com.test.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author trangle
 */
public class ReadWriteLockApp {

    public static void main(String[] args) {

//        Cache cache = new Cache();
        ConcurrentCache cache = new ConcurrentCache();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.set(String.valueOf(temp), String.valueOf(temp));
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }
    }

    public static class ConcurrentCache {
        private static volatile Map<String, Object> map = new HashMap<>();
        private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void set(String key, Object value) {
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " - 保存数据");
                map.put(key, value);
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName() + " - 数据保存完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }

        public void get(String key) {
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " - 读取数据");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " - 数据读取完毕" + map.get(key));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }


    public static class Cache {
        private static final Map<String, Object> map = new HashMap<>();

        public void set(String key, Object value) {
            System.out.println(Thread.currentThread().getName() + " - 保存数据");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " - 数据保存完毕");
        }

        public void get(String key) {
            System.out.println(Thread.currentThread().getName() + " - 读取数据");
            System.out.println(Thread.currentThread().getName() + " - 数据读取完毕" + map.get(key));
        }
    }
}
