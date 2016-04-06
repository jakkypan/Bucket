/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android;

import javax.inject.Singleton;

import com.pan.learn.annotation.dagger.android.interactors.CategoryInteractor;
import com.pan.learn.annotation.dagger.android.interactors.InteractorsModule;

import dagger.Component;

/**
 * Created by Jhordan on 07/12/15.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                InteractorsModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    CategoryInteractor getFindItemsInteractor();
}
