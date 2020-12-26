package com.test.thread.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author trangle
 */
public class CallableApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> future = new FutureTask<Integer>(new NewIntegerTask());
        new Thread(future).start();
        System.out.println(future.get());
        new Thread(future).start();
        System.out.println(future.get());
    }
}

class NewIntegerTask implements Callable<Integer> {

    private static final Random random = new Random();

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("call --- ");
        TimeUnit.SECONDS.sleep(3);
        return random.nextInt(1000);
    }
}
