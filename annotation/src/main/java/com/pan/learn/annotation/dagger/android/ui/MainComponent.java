/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android.ui;


import com.pan.learn.annotation.dagger.android.ActivityScope;
import com.pan.learn.annotation.dagger.android.AppComponent;

import dagger.Component;

/**
 * Created by Jhordan on 07/12/15.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity mainActivity);

    MainPresenter getMainPresenter();

}
