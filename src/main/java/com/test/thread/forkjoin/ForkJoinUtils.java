package com.test.thread.forkjoin;

/**
 * @author trangle
 */
public class ForkJoinUtils {

    public static Long add(Long value,Long another) throws InterruptedException {
//        Thread.sleep(1);
        return value + another;
    }
}
