package com.test.thread;

/**
 * @author trangle
 */
public class ThreadApp {

    public static void main(String[] args) {
//        Thread thread = new TestThread();
//        Thread thread2 = new TestThread();
//        Thread thread3 = new TestThread();
//        Thread thread4 = new TestThread();
//        Thread thread5 = new TestThread();
//        Thread thread6 = new TestThread();
//
//        thread.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        thread6.start();

        Thread thread = new Thread(new VolatileRunnable());

        Thread thread1 = new Thread(() -> {
            while (ThreadUtils.num < 100) {
            }
            ThreadUtils.flag = false;
            System.out.println(ThreadUtils.num + "  ======");
        });

        thread.start();
        thread1.start();
    }
}
