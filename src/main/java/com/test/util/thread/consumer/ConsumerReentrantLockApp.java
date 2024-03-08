package com.test.util.thread.consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author trangle
 * <p>
 * 生产者消费者ReentrantLock实现
 */
public class ConsumerReentrantLockApp {

    public static void main(String[] args) {
        Number number = new Number();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    number.mod3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    number.mod5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    number.mod7();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }

    public static class Number {

        private Lock lock = new ReentrantLock();
        private Condition condition3 = lock.newCondition();
        private Condition condition5 = lock.newCondition();
        private Condition condition7 = lock.newCondition();

        private int number = 3;

        public void mod3() throws InterruptedException {
            try {
                lock.lock();
                while (number % 3 != 0) {
                    condition3.await();
                }
                System.out.println(Thread.currentThread().getName() + " --- " + number);
                number += 2;
                condition5.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void mod5() throws InterruptedException {
            try {
                lock.lock();
                while (number % 5 != 0) {
                    condition5.await();
                }
                System.out.println(Thread.currentThread().getName() + " --- " + number);
                number += 2;
                condition7.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void mod7() throws InterruptedException {
            try {
                lock.lock();
                while (number % 7 != 0) {
                    condition7.await();
                }
                System.out.println(Thread.currentThread().getName() + " --- " + number);
                number = 3;
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}


