/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2;

import com.pan.learn.annotation.dagger.android2.di.components.DaggerGitHubComponent;
import com.pan.learn.annotation.dagger.android2.di.components.DaggerNetComponent;
import com.pan.learn.annotation.dagger.android2.di.components.GitHubComponent;
import com.pan.learn.annotation.dagger.android2.di.components.NetComponent;
import com.pan.learn.annotation.dagger.android2.di.modules.AppModule;
import com.pan.learn.annotation.dagger.android2.di.modules.GitHubModule;
import com.pan.learn.annotation.dagger.android2.di.modules.NetModule;

import android.app.Application;

public class MyApp extends Application {

    private NetComponent mNetComponent;
    private GitHubComponent mGitHubComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();

        mGitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(mNetComponent)
                .gitHubModule(new GitHubModule())
                .build();

    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public GitHubComponent getGitHubComponent() {
        return mGitHubComponent;
    }
}