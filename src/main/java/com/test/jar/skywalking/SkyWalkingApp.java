package com.test.jar.skywalking;

import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/7/22 18:16
 * @Description:
 */
public class SkyWalkingApp {

    public static void main(String[] args) {
        // 模拟请求处理
        for (int i = 0; i < 10; i++) {
            handleRequest();
        }
    }

    @Trace
    private static void handleRequest() {
        String traceId = TraceContext.traceId();
        System.out.println("Current trace id: " + traceId);

        try {
            // 模拟处理时间
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
