package com.test.feature.function;

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
