package com.test.util.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author trangle
 */
public class QueueApp {

    public static void main(String[] args) {
        BlockingQueue queue = new SynchronousQueue();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("设置值 >> " + temp);
                    queue.put(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    System.out.println("取值 >> " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
