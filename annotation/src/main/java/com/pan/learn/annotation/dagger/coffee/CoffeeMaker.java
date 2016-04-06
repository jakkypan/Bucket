/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

import javax.inject.Inject;

/**
 * Created by panhongchao on 16/4/5.
 */
public class CoffeeMaker {
    private final Heater heater;
    private final Pump pump;

    @Inject
    CoffeeMaker(Heater heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew() {
        heater.on();
        pump.pump();
        System.out.println(" [_]P coffee! [_]P ");
        heater.off();
    }
}
