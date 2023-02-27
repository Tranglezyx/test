package com.test.thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author trangle
 */
public class ForkJoinApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        List<Long> longList = new ArrayList<>(100000000);
        Random random = new Random();
        for (long i = 0; i < 100000000; i++) {
            longList.add((long)random.nextInt(10000));
        }

        long startTime = System.currentTimeMillis();
        Long result = 0L;
        for (Long aLong : longList) {
            result = ForkJoinUtils.add(result, aLong);
        }
        long pointTime = System.currentTimeMillis();

        AddTask addTask = new AddTask(longList);
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(addTask);
        Long result1 = forkJoinTask.get();
        long endTime = System.currentTimeMillis();

        System.out.println("one thread result : " + result + "  time : " + (pointTime - startTime));
        System.out.println("fork join result : " + result1 + "  time : " + (endTime - pointTime));

    }
}
