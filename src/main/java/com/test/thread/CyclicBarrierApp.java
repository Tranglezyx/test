package com.test.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author trangle
 * <p>
 * CyclicBarrier是一个加法计数器，当需要的线程都执行完后，则触发
 */
public class CyclicBarrierApp {

    private static int num = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {
            System.out.println("触发事件");
        });
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                try {
                    System.out.println("await之前的时间" + System.currentTimeMillis());
                    cyclicBarrier.await();
                    System.out.println("await之后的时间" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
