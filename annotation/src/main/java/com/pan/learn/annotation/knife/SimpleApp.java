/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.knife;

import com.pan.learn.annotation.BuildConfig;

import android.app.Application;
import butterknife.ButterKnife;

public class SimpleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
