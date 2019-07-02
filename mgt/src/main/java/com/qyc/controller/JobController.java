//package com.qyc.controller;
//
//
//import com.qyc.utils.JobUtils;
//import com.qyc.model.data.quartz.JobEntity;
//import org.quartz.*;
//import org.quartz.impl.matchers.GroupMatcher;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import java.util.Set;
//
///**
// * @author qianyongchao
// * @date 2019/5/28 14:17
// */
//@RestController
//public class JobController {
//
//    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
//    @Autowired
//    private SchedulerFactoryBean schedulerFactoryBean;
//    @Autowired
//    private JobUtils jobService;
//
//    //初始化启动所有的Job
//    @Async("taskExecutor")
//    @PostConstruct
//    public void initialize() {
//        try {
//            reStartAllJobs();
//            logger.info("INIT SUCCESS");
//        } catch (SchedulerException e) {
//            logger.info("INIT EXCEPTION : " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    //根据ID重启某个Job
//    @RequestMapping( "/refresh/{id}" )
//    public String refresh(@PathVariable Integer id) throws SchedulerException {
//        String result;
//        JobEntity entity = jobService.getJobEntityById(id);
//        if (entity == null) return "error: id is not exist ";
//        synchronized (logger) {
//            JobKey jobKey = jobService.getJobKey(entity);
//            Scheduler scheduler = schedulerFactoryBean.getScheduler();
//            scheduler.pauseJob(jobKey);
//            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
//            scheduler.deleteJob(jobKey);
//            JobDataMap map = jobService.getJobDataMap(entity);
//            JobDetail jobDetail = jobService.geJobDetail(jobKey, entity.getId(), entity.getDescription(), map);
//            if (entity.getStatus().equals("OPEN")) {
//                scheduler.scheduleJob(jobDetail, jobService.getTrigger(entity));
//                result = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " success !";
//            } else {
//                result = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " failed ! , " +
//                        "Because the Job status is " + entity.getStatus();
//            }
//        }
//        return result;
//    }
//
//
//    //重启数据库中所有的Job
//    @RequestMapping( "/refresh/all" )
//    public String refreshAll() {
//        String result;
//        try {
//            reStartAllJobs();
//            result = "SUCCESS";
//        } catch (SchedulerException e) {
//            result = "EXCEPTION : " + e.getMessage();
//        }
//        return "refresh all jobs : " + result;
//    }
//
//    /**
//     * 暂停某个job
//     */
//    @RequestMapping("/pause/{id}")
//    public void pause(@PathVariable String id) {
//
//
//
//
//    }
//
//    /**
//     * 重新启动所有的job
//     */
//    private void reStartAllJobs() throws SchedulerException {
//        synchronized (logger) {                                                         //只允许一个线程进入操作
//            Scheduler scheduler = schedulerFactoryBean.getScheduler();
//            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
//            scheduler.pauseJobs(GroupMatcher.anyGroup());                               //暂停所有JOB
//            for (JobKey jobKey : set) {                                                 //删除从数据库中注册的所有JOB
//                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
//                scheduler.deleteJob(jobKey);
//            }
//            for (JobEntity job : jobService.loadJobs()) {                               //从数据库中注册的所有JOB
//                logger.info("Job register name : {} , group : {} , cron : {}", job.getName(), job.getGroup(), job.getCron());
//                JobDataMap map = jobService.getJobDataMap(job);
//                JobKey jobKey = jobService.getJobKey(job);
//
//                //jobDetail：根据job id获取不同的jobDetail -> 工具类
//
//                JobDetail jobDetail = jobService.geJobDetail(jobKey, job.getId() ,job.getDescription(), map);
//
//                logger.info("controller");
//                logger.info("job：",job);
//                logger.info("map：",map);
//                logger.info("jobKey：",jobKey);
//                logger.info("jobDetail：",jobDetail);
//
//                if (job.getStatus().equals("OPEN")) {
//                    //jobdetail 绑定了job，然后把这个job和jobDetail 绑定到一个执行计划中，然后对应job 有execute方法，执行具体的内容！
//                    scheduler.scheduleJob(jobDetail, jobService.getTrigger(job));
//
//                    //根据id分类执行不行的任务
//
//
//                } else
//                    logger.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(), job.getStatus());
//            }
//        }
//    }
//}