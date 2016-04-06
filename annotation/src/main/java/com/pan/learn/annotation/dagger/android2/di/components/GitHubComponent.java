/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2.di.components;

import com.pan.learn.annotation.dagger.android2.MainActivity;
import com.pan.learn.annotation.dagger.android2.di.modules.GitHubModule;
import com.pan.learn.annotation.dagger.android2.di.scopes.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = GitHubModule.class)
public interface GitHubComponent {
    void inject(MainActivity activity);
}
