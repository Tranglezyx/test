package com.test.util;

import cn.hutool.core.date.StopWatch;

import java.util.concurrent.TimeUnit;

public class LogUtils {

    public static String generateLogLine(StopWatch stopWatch){
        if (stopWatch == null){
            return "";
        }
        StopWatch.TaskInfo[] taskInfo = stopWatch.getTaskInfo();
        StringBuilder sb = new StringBuilder();
        sb.append("【");
        sb.append(stopWatch.getId());
        sb.append(",总耗时:");
        sb.append(stopWatch.getTotal(TimeUnit.MILLISECONDS));
        sb.append(".详情:");
        for (StopWatch.TaskInfo info : taskInfo) {
            sb.append("[");
            sb.append("任务:");
            sb.append(info.getTaskName());
            sb.append(",耗时:");
            sb.append(info.getTimeMillis());
            sb.append("ms");
            sb.append("]");
        }
        sb.append("】");
        return sb.toString();
    }
}
