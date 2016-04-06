/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

import dagger.Module;
import dagger.Provides;

/**
 * Created by panhongchao on 16/4/5.
 */
@Module
public class PumpModule {
    @Provides
    Pump providePump(Heater heater) {
        return new Thermosiphon(heater);
    }
}
