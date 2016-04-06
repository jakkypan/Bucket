

/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android.ui;



import com.pan.learn.annotation.dagger.android.interactors.CategoryInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jhordan on 07/12/15.
 */
@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainView mainView, CategoryInteractor categoryInteractor) {
        return new MainPresenterImpl(mainView, categoryInteractor);
    }
}
