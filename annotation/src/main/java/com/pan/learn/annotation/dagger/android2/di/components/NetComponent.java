/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2.di.components;

import com.pan.learn.annotation.dagger.android2.di.modules.AppModule;
import com.pan.learn.annotation.dagger.android2.di.modules.NetModule;
import com.squareup.okhttp.OkHttpClient;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}