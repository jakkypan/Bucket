/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.kettle;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by panhongchao on 16/4/5.
 */
@Singleton
@Component(modules = KettleModule.class)
public interface KettleComponent {
    Kettle providesKettle();
}
