/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.kettle;

/**
 * Created by panhongchao on 16/4/5.
 */
public class KettleTest {
    public static void main(String[] args) {
        KettleComponent component = DaggerKettleComponent.builder().build();
        Kettle kettle = component.providesKettle();
        kettle.on();
        kettle.brew();
        kettle.off();
    }
}
