package com.soft863.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-14 14:16
 **/
@Component
public class Job {
    @Scheduled(cron = "1/3 1 * * * *")
    public void start() {
        System.out.println ("开始调度执行");
    }
}
