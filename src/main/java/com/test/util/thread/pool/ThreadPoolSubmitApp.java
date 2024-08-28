package com.test.util.thread.pool;

import com.test.util.thread.pool.handler.FastHandlerThread;
import com.test.util.thread.pool.handler.SlowHandlerThread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/8/28 17:52
 * @Description:
 */
public class ThreadPoolSubmitApp {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10, 10, 1, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    private static final ThreadPoolExecutor sendExecutor = new ThreadPoolExecutor(
            10, 10, 1, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        executor.submit(() -> submitSlowTask());
        executor.submit(() -> submitFastTask());
    }

    public static void submitSlowTask() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            sendExecutor.submit(new SlowHandlerThread());
        }
    }

    public static void submitFastTask() {
        int size = 100;
        for (int i = 0; i < size; i++) {
            sendExecutor.submit(new FastHandlerThread());
        }
    }
}
