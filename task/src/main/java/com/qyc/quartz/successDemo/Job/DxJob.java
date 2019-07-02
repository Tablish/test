//package com.qyc.quartz.successDemo.Job;
//
//import com.qyc.utils.SendSmsUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.DisallowConcurrentExecution;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
///**
// * 发送短信job
// *
// * @author qianyongchao
// * @date 2019/5/31
// */
//@DisallowConcurrentExecution
//@Component
//@Slf4j
//public class DxJob implements Job, Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    public void execute(JobExecutionContext context) {
//        try {
//            log.info("发送短信job执行");
//            SendSmsUtil sendSmsUtil = new SendSmsUtil();
//            sendSmsUtil.showInfo();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}