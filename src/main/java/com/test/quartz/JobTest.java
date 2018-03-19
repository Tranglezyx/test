package com.test.quartz;


import com.test.util.DateUtils;
import org.quartz.*;

import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobTest implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("任务key " + jobKey + " 执行时间 ：" + DateUtils.dateToStr(new Date(),null));
    }
}
