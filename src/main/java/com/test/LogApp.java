package com.test;

import cn.hutool.core.date.StopWatch;
import com.test.util.LogUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogApp {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("qqq");

        stopWatch.start("111");
        Thread.sleep(100);
        stopWatch.stop();

        stopWatch.start("222");
        Thread.sleep(200);
        stopWatch.stop();

        stopWatch.start("333");
        Thread.sleep(50);
        stopWatch.stop();

        stopWatch.start("444");
        Thread.sleep(150);
        stopWatch.stop();

        StopWatch.TaskInfo[] taskInfo = stopWatch.getTaskInfo();
        for (StopWatch.TaskInfo info : taskInfo) {
            log.info("{},{}",info.getTaskName(),info.getTimeMillis());
        }
        log.info(LogUtils.generateLogLine(stopWatch));
        log.info("耗时:{}",stopWatch.prettyPrint());
    }
}
