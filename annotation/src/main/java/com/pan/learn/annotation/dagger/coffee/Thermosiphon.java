/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

import javax.inject.Inject;

/**
 * Created by panhongchao on 16/4/5.
 */
public class Thermosiphon implements Pump {

    private final Heater heater;

    @Inject
    Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override
    public void pump() {
        if (heater.isHot()) {
            System.out.println("=> => pumping => =>");
        }
    }
}
