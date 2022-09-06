package com.test.thread.pool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolCallableApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int num = 3000 * 10000;
        List<BigDecimal> values = new ArrayList<>();
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            values.add(BigDecimal.valueOf(random.nextInt(100)).add(BigDecimal.ONE));
        }
        System.out.println("数据制造完成,耗时:" + (System.currentTimeMillis() - start) + "ms");
        calculate(values);
        calculate1(values);
    }

    private static void calculate1(List<BigDecimal> values) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        AddCallableImpl addCallable = new AddCallableImpl(values.subList(0, 1000 * 10000));
        AddCallableImpl addCallable1 = new AddCallableImpl(values.subList(1000 * 10000, 2000 * 10000));
        AddCallableImpl addCallable2 = new AddCallableImpl(values.subList(2000 * 10000, 3000 * 10000));
        Future<BigDecimal> submit = threadPoolExecutor.submit(addCallable);
        Future<BigDecimal> submit1 = threadPoolExecutor.submit(addCallable1);
        Future<BigDecimal> submit2 = threadPoolExecutor.submit(addCallable2);
        while (!submit.isDone() && !submit1.isDone() && !submit2.isDone()) {

        }
        System.out.println(submit.get().add(submit1.get()).add(submit2.get()) + "  耗时:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void calculate(List<BigDecimal> values) {
        long start = System.currentTimeMillis();
        BigDecimal result = new BigDecimal(2);
        Random random = new Random();
        for (BigDecimal value : values) {
            int index = random.nextInt(4);
            if (index == 0) {
                result = result.add(value);
            } else if (index == 1) {
                result = result.subtract(value);
            } else if (index == 2) {
                result = result.multiply(value);
            } else {
                result = result.divide(value, 2, RoundingMode.HALF_UP);
            }
        }
        System.out.println(result + "  耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}
