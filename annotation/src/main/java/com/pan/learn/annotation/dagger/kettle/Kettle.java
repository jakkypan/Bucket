/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.kettle;

import javax.inject.Inject;

/**
 * Created by panhongchao on 16/4/5.
 */
public class Kettle {
    @Inject
    public Heater heater;//电水壶依赖于加热器

    public Kettle(Heater heater) {
        super();
        this.heater = heater;
    }

    public void on() {
        heater.on();
    }

    public void off() {
        heater.off();
    }

    public void brew() {
        if (heater.isHot()) {
            System.out.println("倒一杯开水");
        }
    }
}
