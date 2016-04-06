/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by panhongchao on 16/4/5.
 */
@Singleton
@Component(modules = {DripCoffeeModule.class, PumpModule.class})
public interface CoffeeComponent {
    CoffeeMaker maker();
}
