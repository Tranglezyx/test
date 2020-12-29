package com.test.function;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author trangle
 */
public class FunctionApp {

    public static void main(String[] args) {
        CallbackRun callbackRun = new CallbackRun(() -> {
            System.out.println("run >> ");
        });
        callbackRun.doRun();
        callbackRun = new CallbackRun(() -> {
            return 1;
        });
        callbackRun.doCallRun();
    }
}
