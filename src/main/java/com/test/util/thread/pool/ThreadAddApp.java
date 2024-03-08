package com.test.util.thread.pool;

import com.google.common.collect.Lists;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadAddApp {

    private static ExecutorService accretionExecutor = new ThreadPoolExecutor(80, 80, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("accretion-"));

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int size = 10000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<BigDecimal> numbers = new ArrayList<>(size + 1000);
        for (int i = 0; i < size; i++) {
            // 随机生成一个BigDecimal数据，范围为[0, 100)，保留两位小数
            BigDecimal number = BigDecimal.valueOf(random.nextDouble() * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
            numbers.add(number);
        }
        stopWatch.stop();
        System.out.println("生成数据耗时" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();
        System.out.println(add(numbers));
        stopWatch.stop();
        System.out.println("不使用线程池耗时:" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();
        BigDecimal result = BigDecimal.ZERO;
        List<List<BigDecimal>> listList = Lists.partition(numbers, 1000);
        List<Future<BigDecimal>> futureList = new ArrayList<>();
        for (List<BigDecimal> numberList : listList) {
            Future<BigDecimal> future = accretionExecutor.submit(() -> add(numberList));
            futureList.add(future);
        }
        for (Future<BigDecimal> future : futureList) {
            try {
                result = result.add(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        stopWatch.stop();
        System.out.println(result);
        System.out.println("使用线程池耗时:" + stopWatch.getTime());
    }

    private static BigDecimal add(List<BigDecimal> numberList) throws InterruptedException {
        if (CollectionUtils.isEmpty(numberList)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : numberList) {
            result = result.add(bigDecimal);
            Thread.sleep(1);
        }
        return result;
    }
}
