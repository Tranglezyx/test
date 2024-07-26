package com.test.jar.metrics;

import cn.hutool.core.util.RandomUtil;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/7/22 17:59
 * @Description:
 */
public class MetricsApp {

    // 创建MetricRegistry
    private static final MetricRegistry metrics = new MetricRegistry();
    // 创建Counter
    private static final Counter requests = metrics.counter("requests");
    // 创建Timer
    private static final Timer responses = metrics.timer("responses");

    public static void main(String[] args) {
        // 创建ConsoleReporter，输出到控制台
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        // 模拟请求处理
        for (int i = 0; i < 20; i++) {
            handleRequest();
        }
    }

    private static void handleRequest() {
        requests.inc(); // 递增请求计数器
        Timer.Context context = responses.time(); // 开始计时
        try {
            // 模拟处理时间
            Thread.sleep((long) (RandomUtil.randomInt(300)));
            Thread.sleep((long) (RandomUtil.randomInt(100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            context.stop(); // 停止计时
        }
    }
}
