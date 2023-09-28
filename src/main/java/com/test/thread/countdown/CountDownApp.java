package com.test.thread.countdown;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class CountDownApp {

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    private static void test2() throws InterruptedException {
        long start = System.currentTimeMillis();
        int size = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            executorService.execute(() -> {
                try {
                    run2(countDownLatch);
                } catch (Exception e) {
                    log.error("出错", e);
                }
            });
        }
        countDownLatch.await();
        log.info("执行完成,耗时:{}ms", System.currentTimeMillis() - start);
    }

    private static void test1() {
        long start = System.currentTimeMillis();
        int size = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<CountDownLatch> latchList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            CountDownLatch latch = new CountDownLatch(1);
            latchList.add(latch);
            executorService.execute(() -> {
                try {
                    run1();
                } catch (Exception e) {
                    log.error("发送短信异常", e);
                } finally {
                    latch.countDown();
                }
            });
        }

        for (CountDownLatch latch : latchList) {
            try {
                latch.await();
            } catch (Exception e) {
                log.error("发送短信异常", e);
            }
        }

        log.info("执行完成,耗时:{}ms", System.currentTimeMillis() - start);
    }

    public static void run2(CountDownLatch latch) throws InterruptedException {
        int millis = RandomUtils.nextInt(2000, 5000);
        Thread.sleep(millis);
        log.info("休眠时间:{}ms", millis);
        latch.countDown();
    }

    public static void run1() throws InterruptedException {
        int millis = RandomUtils.nextInt(2000, 5000);
        Thread.sleep(millis);
        log.info("休眠时间:{}ms", millis);
    }
}

