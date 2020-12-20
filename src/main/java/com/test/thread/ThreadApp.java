package com.test.thread;

/**
 * @author trangle
 */
public class ThreadApp {

    public static  int num = 1;

    public static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new VolatileRunnable());
//
//        Thread thread1 = new Thread(() -> {
//            while (ThreadUtils.num < 100) {
//            }
//            ThreadUtils.flag = false;
//            System.out.println(ThreadUtils.num + "  ======");
//        });
//
//        thread.start();
//        thread1.start();

        Thread thread1 = new Thread(() -> {
            while(flag){
                num++;
            }
            System.out.println("add thread : " + num);
        });

        Thread thread2 = new Thread(() -> {
            while(num < 1000){

            }
            flag = false;
            System.out.println("listener thread : " + num);
        });

//        thread1.start();
//        thread2.start();

        test();
    }

    public static void test() throws InterruptedException {
        Object o = new Object();
        synchronized (ThreadApp.class){
            o.wait();
        }
    }
}
