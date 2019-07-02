package com.qyc.utils;

import com.qyc.mapper.config.JobEntityMapper;
import com.qyc.model.data.quartz.JobEntity;
//import com.qyc.quartz.successDemo.Job.DxJob;
import com.qyc.quartz.successDemo.Job.DynamicJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 被调用的
 *
 * @author qianyongchao
 * @date 2019/5/28 14:01
 */
@Configuration
public class JobUtils {
    @Autowired
    private JobEntityMapper jobEntityMapper;

    //通过Id获取Job
    public JobEntity getJobEntityById(Integer id) {
        return jobEntityMapper.findById(id);
    }

    //从数据库中加载获取到所有Job
    public List<JobEntity> loadJobs() {
        List<JobEntity> list = new ArrayList<>();
        jobEntityMapper.findAll().forEach(list::add);
        return list;
    }

    //获取JobDataMap.(Job参数对象)
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("group", job.getGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("JobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
    public JobDetail geJobDetail(JobKey jobKey, Integer id, String description, JobDataMap map) {

        if(id != null && id == 1) {
            return JobBuilder.newJob(DynamicJob.class)
                    .withIdentity(jobKey)
                    .withDescription(description)
                    .setJobData(map)
                    .storeDurably()
                    .build();
        }

//        if(id != null && id == 2) {
//            return JobBuilder.newJob(DxJob.class)
//                    .withIdentity(jobKey)
//                    .withDescription(description)
//                    .setJobData(map)
//                    .storeDurably()
//                    .build();
//        }


        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();

    }

    //获取Trigger (Job的触发器,执行规则)
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(), job.getGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    //获取JobKey,包含Name和Group
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getName(), job.getGroup());
    }
}