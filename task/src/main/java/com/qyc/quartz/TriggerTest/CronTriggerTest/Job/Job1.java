//package com.qyc.quartz.TriggerTest.CronTriggerTest.Job;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author qianyongchao
// * @date 2019/5/27 14:01
// */
//public class Job1 implements Job {
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        //打印当前的时间
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date date = new Date();
//        System.out.println("current exec time is :"+sf.format(date));
//    }
//}
