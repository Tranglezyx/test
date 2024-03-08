package com.test.util.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author trangle
 */
public class VolatileApp {

    public static volatile int num = 0;
    public static Random random = new Random();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable printRun = () -> {
            while (num != 5) {
                System.out.println("print -- " + num);
            }
            System.out.println("end ---- " + num);
        };

        Runnable setRun = () -> {
            while (num != 5) {
                int tmp = random.nextInt(10);
                System.out.println("set ---- " + tmp);
                num = tmp;
            }
        };

        executorService.submit(printRun);
        executorService.submit(setRun);
        executorService.shutdown();
    }
}
