package com.test.quartz;

import com.test.util.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SchedulerTest {

    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }

    public static void schedulerJob() throws SchedulerException {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class).withIdentity("Trangle","test").build();
        //创建计划 每3秒执行一次
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").withSchedule(scheduleBuilder).build();
        Scheduler scheduler = getScheduler();
        Date date = scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("执行时间 ： " + DateUtils.dateToStr(date,null));
        scheduler.start();
    }

    public static void main(String[] args){
        try {
            schedulerJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
