package com.qyc.TimerTest;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * springBoot自带的定时任务很简单：启动类加注解：@EnableScheduling；定义job（使用下面的注解）
 *
 * @author qianyongchao
 * @date 2019/5/10 14:05
 */
@Log4j
@Component
@PropertySource(value = "classpath:task-dev.properties")
public class TestJob {

    //每隔2秒执行一次：2 0 0 * * ??
//    @Scheduled(cron = "${test-cron}" )
//    public void updateCostPrice(){
//        System.out.println(" 你好 钱永超");
//    }

    /**
     * 每周一/五 08:00 执行 异步执行（不影响主线程）
     */
    @Async("taskExecutor")
    @Scheduled(cron = "${good_morning}")
    public void showInfo() {

        //发送信息
        //System.out.printf("执行");
    }
}