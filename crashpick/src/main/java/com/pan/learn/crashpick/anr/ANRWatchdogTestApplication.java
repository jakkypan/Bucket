/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.crashpick.anr;

import android.app.Application;

import com.github.anrwatchdog.ANRWatchDog;

/**
 * 实现对anr的捕获，打印出产生anr的地方
 *
 * The watchdog is a simple thread does the following in a loop:
 *
 * 1、Schedules a runnable to be run on the UI thread as soon as possible.
 * 2、Wait for 5 seconds. (5 seconds is the default, but it can be configured).
 * 3、See if the runnable has been run. If it has, go back to 1.
 * 4、If the runnable has not been run, it means that the UI thread has been blocked for at least 5 seconds, raises an
 * error with the all threads stack traces
 */
public class ANRWatchdogTestApplication extends Application {

    ANRWatchDog anrWatchDog = new ANRWatchDog(2000);

    @Override
    public void onCreate() {
        super.onCreate();

        anrWatchDog.setReportMainThreadOnly().start();
    }
}