/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by panhongchao on 16/4/5.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent((AppComponent) App.get(this).component());
    }

    protected abstract void setupComponent(AppComponent appComponent);
}
