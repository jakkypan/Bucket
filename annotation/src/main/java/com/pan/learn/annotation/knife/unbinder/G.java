/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.knife.unbinder;

import android.support.annotation.ColorInt;
import android.view.View;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class G extends E {

    @BindColor(android.R.color.darker_gray)
    @ColorInt
    int grayColor;
    @Bind(android.R.id.button2)
    View button2;

    public G(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @OnClick(android.R.id.content)
    public void onClick() {

    }
}
