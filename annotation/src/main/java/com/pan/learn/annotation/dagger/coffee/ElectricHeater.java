/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

/**
 * Created by panhongchao on 16/4/5.
 */
public class ElectricHeater implements Heater {
    boolean heating;

    @Override
    public void on() {
        System.out.println("~ ~ ~ heating ~ ~ ~");
        this.heating = true;
    }

    @Override
    public void off() {
        System.out.println(". . . turn off heater . . .");
        this.heating = false;
    }

    @Override
    public boolean isHot() {
        return heating;
    }
}