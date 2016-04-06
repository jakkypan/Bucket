/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.knife.unbinder;

import android.support.annotation.ColorInt;
import android.view.View;

import butterknife.BindColor;
import butterknife.ButterKnife;

public class F extends D {

    @BindColor(android.R.color.background_light)
    @ColorInt
    int backgroundLightColor;

    public F(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
