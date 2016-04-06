/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
/**
 * Created by Jhordan on 07/12/15.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}