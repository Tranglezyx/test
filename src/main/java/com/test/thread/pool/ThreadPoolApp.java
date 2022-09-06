package com.test.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author trangle
 */
public class ThreadPoolApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建一个定长线程池，支持定时及周期性任务执行。
//        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        // 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序 (FIFO, LIFO, 优先级) 执行。
//        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

//        List<Callable> callableList = new ArrayList<>();
//        callableList.add(new AddTask());
//        callableList.add(new AddTask());
//        callableList.add(new AddTask());
//        Future future = fixedThreadPool.submit(new AddTask());
//        System.out.println(future.get());
//        fixedThreadPool.shutdown();

        // 线程池ThreadPoolExecutors
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                blockingQueue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 9; i++) {
            final int temp = i;
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(temp);
            });
        }
        System.out.println("线程池核心线程数量 >> " + threadPoolExecutor.getCorePoolSize());
        System.out.println("线程池运行线程数量 >> " + threadPoolExecutor.getActiveCount());
        System.out.println("线程池总任务数量 >> " + threadPoolExecutor.getTaskCount());
        System.out.println("线程池当前最大可执行数量 >> " + threadPoolExecutor.getLargestPoolSize());
        System.out.println("阻塞队列数量 >> " + blockingQueue.size());
        System.out.println("当前CPU核心数 >> " + Runtime.getRuntime().availableProcessors());
        threadPoolExecutor.shutdown();
    }

    public static class NumEntity {
        public static final AtomicInteger num = new AtomicInteger(1);

        public static void add() {
            num.incrementAndGet();
        }
    }

    public static class AddTask implements Callable {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public List<Integer> call() throws Exception {
            List<Integer> numList = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                numList.add(random.nextInt(10000));
            }
            return numList;
        }
    }
}


