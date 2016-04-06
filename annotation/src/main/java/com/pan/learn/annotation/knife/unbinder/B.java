/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.knife.unbinder;

import android.support.annotation.ColorInt;
import android.view.View;

import butterknife.BindColor;
import butterknife.ButterKnife;

public class B extends A {

    @BindColor(android.R.color.white)
    @ColorInt
    int whiteColor;

    public B(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
