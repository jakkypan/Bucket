/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.crashpick.common;

/**
 * Created by panhongchao on 16/3/3.
 */
import android.app.Application;

public class CrashApp extends Application {

    private static CrashApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    public static CrashApp getInstance() {
        return sInstance;
    }

}