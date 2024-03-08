package com.test.util.thread.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trangle
 * <p>
 * CountDownLatch是一个减法计数器，用于保证必要的线程完全执行
 */
public class CountDownLatchApp {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " - run");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("--- end ---");
    }

    public static class NewCountDownLatch {
        private static volatile int count;
        private static ReentrantLock lock = new ReentrantLock();

        public NewCountDownLatch(int count) {
            this.count = count;
        }

        public void countDown() {
            lock.lock();
            try {
                count--;
            } finally {
                lock.unlock();
            }
        }

        public void await() {
            while (count != 0) {
            }
        }
    }

}

