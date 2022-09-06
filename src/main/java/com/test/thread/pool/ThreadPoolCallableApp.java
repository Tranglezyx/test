package com.test.thread.pool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolCallableApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int num = 50 * 10000;
        List<BigDecimal> values = new ArrayList<>();
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            values.add(BigDecimal.valueOf(random.nextInt(100)).add(BigDecimal.ONE));
        }
        System.out.println("数据制造完成,耗时:" + (System.currentTimeMillis() - start) + "ms");
//        BigDecimal calculate = calculate(values);
        BigDecimal calculate1 = calculate1(values);
//        System.out.println(calculate.compareTo(calculate1));
    }

    private static BigDecimal calculate1(List<BigDecimal> values) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        int batch = 5;
        int batchSize = values.size() / batch;
        List<Future<BigDecimal>> threadList = new ArrayList<>();
        for (int i = 0; i < batch; i++) {
            List<BigDecimal> subList;
            if (i != batch - 1) {
                subList = values.subList(batchSize * i, batchSize * (i + 1));
            } else {
                subList = values.subList(batchSize * i, values.size());
            }
            threadList.add(threadPoolExecutor.submit(new AddCallableImpl(subList)));
        }
        int count = 0;
        while (count != threadList.size()) {
            count = 0;
            for (Future<BigDecimal> future : threadList) {
                if (future.isDone()) {
                    count++;
                }
            }
        }
        BigDecimal result = new BigDecimal(2);
        for (Future<BigDecimal> future : threadList) {
            result = result.multiply(future.get());
        }
        System.out.println("  耗时:" + (System.currentTimeMillis() - start) + "ms");
        return result;
    }

    private static BigDecimal calculate(List<BigDecimal> values) {
        long start = System.currentTimeMillis();
        BigDecimal result = new BigDecimal(2);
        Random random = new Random();
        for (BigDecimal value : values) {
            int index = random.nextInt(4);
//            if (index == 0) {
//                result = result.add(value);
//            } else if (index == 1) {
//                result = result.subtract(value);
//            } else if (index == 2) {
//                result = result.multiply(value);
//            } else {
//                result = result.divide(value, 2, RoundingMode.HALF_UP);
//            }
            result = result.multiply(value);
        }
        System.out.println("  耗时：" + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
