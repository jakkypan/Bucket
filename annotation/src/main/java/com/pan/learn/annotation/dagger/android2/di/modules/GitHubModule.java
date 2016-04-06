/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2.di.modules;

import com.pan.learn.annotation.dagger.android2.di.scopes.UserScope;
import com.pan.learn.annotation.dagger.android2.network.interfaces.GitHubApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

@Module
public class GitHubModule {

    @Provides
    @UserScope
    public GitHubApiInterface providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(GitHubApiInterface.class);
    }
}
