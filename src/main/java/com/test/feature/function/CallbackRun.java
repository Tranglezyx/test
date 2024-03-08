package com.test.feature.function;

/**
 * @author trangle
 */
public class CallbackRun {

    private CallRun callRun;
    private Run run;

    public CallbackRun(CallRun callRun) {
        this.callRun = callRun;
    }

    public CallbackRun(Run run) {
        this.run = run;
    }

    public Object doCallRun() {
        System.out.println("callRun结果 >> " + callRun.callRun());
        return callRun.callRun();
    }

    public void doRun() {
        run.run();
    }
}
