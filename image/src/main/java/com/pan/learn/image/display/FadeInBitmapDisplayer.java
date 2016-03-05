/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.display;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by panhongchao on 16/3/4.
 */
public class FadeInBitmapDisplayer implements BitmapDisplayer {
    private int durationMillis;

    public FadeInBitmapDisplayer(int durationMillis) {
        this.durationMillis = durationMillis;
    }

    @Override
    public void display(Bitmap bitmap, ImageView imageAware) {
        imageAware.setImageBitmap(bitmap);
        animate(imageAware, durationMillis);
    }

    public static void animate(View imageView, int durationMillis) {
        if (imageView != null) {
            AlphaAnimation fadeImage = new AlphaAnimation(0, 1);
            fadeImage.setDuration(durationMillis);
            fadeImage.setInterpolator(new DecelerateInterpolator());
            imageView.startAnimation(fadeImage);
        }
    }
}
