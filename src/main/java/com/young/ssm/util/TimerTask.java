package com.young.ssm.util;

import org.springframework.stereotype.Component;

import java.util.Date;

public class TimerTask {

    private static int counter = 0;


    public  void execute() {
        long ms = System.currentTimeMillis();
        System.out.print("定时任务开始执行：");
        counter++;
        System.out.println("\t\t" + new Date(ms));
        System.out.println("定时任务已执行"+counter+"次");

    }
}
