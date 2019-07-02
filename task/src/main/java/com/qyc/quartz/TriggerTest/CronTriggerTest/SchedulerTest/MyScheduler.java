//package com.qyc.quartz.TriggerTest.CronTriggerTest.SchedulerTest;
//
//import com.qyc.quartz.TriggerTest.SimpleTriggerTest.Job.Job1;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static org.quartz.JobBuilder.newJob;
//
///**
// * @author qianyongchao
// * @date 2019/5/27 13:36
// */
//public class MyScheduler {
//    public static void main(String[] args) {
//        try {
//            // 1. 创建一个JodDetail实例 将该实例与Hello job class绑定 (链式写法)
//            JobDetail jobDetail = newJob(Job1.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
//                    .withIdentity("myJob","group1") // 定义name/group
//                    .build();
//            // 打印当前的时间
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            Date date = new Date();
//            System.out.println("current time is :" + sf.format(date));
//
//            // 2. 定义一个Trigger，定义该job在4秒后执行，并且执行一次
//            Date startTime = new Date();
//            startTime.setTime(startTime.getTime()+4000L);
//
//            CronTrigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("cronTrigger", "group1")
//                    .withSchedule(
//                            CronScheduleBuilder
//                                    .cronSchedule("* * * * * ? *")
//                    ).build();
//
//
//            // 3. 创建scheduler 两种创建方法
//            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
//
//            Scheduler scheduler = stdSchedulerFactory.getScheduler();
//
//            // 4. 将trigger和jobdetail加入这个调度
//            scheduler.scheduleJob(jobDetail, trigger);
//
//            // 5. 启动scheduler
//            scheduler.start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}