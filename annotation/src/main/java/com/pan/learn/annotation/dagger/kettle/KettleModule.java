/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.kettle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by panhongchao on 16/4/5.
 */
@Module
public class KettleModule {
    @Singleton
    @Provides
    Heater providesHeater() {
        return new Heater();
    }

    @Singleton
    @Provides
    Kettle providersKettle(Heater heater) {
        return new Kettle(heater);
    }
}
