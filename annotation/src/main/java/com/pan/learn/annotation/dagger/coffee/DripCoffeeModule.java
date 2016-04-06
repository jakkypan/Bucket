/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by panhongchao on 16/4/5.
 */
@Module
public class DripCoffeeModule {
    @Provides
    @Singleton
    Heater provideHeater() {
        return new ElectricHeater();
    }
}
