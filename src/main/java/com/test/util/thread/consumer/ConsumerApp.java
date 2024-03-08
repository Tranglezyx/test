package com.test.util.thread.consumer;

/**
 * @author trangle
 * <p>
 * 使用sychronized和wait(),notifyAll模拟多线程之间的生产者和消费者问题
 */
public class ConsumerApp {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

    public static class Data {
        private static int number = 0;

        public synchronized void increment() throws InterruptedException {
            while (number != 0) {
                this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + " --- " + number);
            this.notifyAll();
        }

        public synchronized void decrement() throws InterruptedException {
            // 如果用if，存在可能被生产者唤醒的可能，进而继续消费，导致消费超过0
            while (number == 0) {
                this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + " --- " + number);
            this.notifyAll();
        }
    }
}
