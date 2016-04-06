/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.knife.unbinder;

import android.support.annotation.ColorInt;
import android.view.View;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class C extends B {

    @BindColor(android.R.color.transparent)
    @ColorInt
    int transparentColor;
    @Bind(android.R.id.button1)
    View button1;

    public C(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
