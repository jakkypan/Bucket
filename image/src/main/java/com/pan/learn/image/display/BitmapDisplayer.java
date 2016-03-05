/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.display;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by panhongchao on 16/3/4.
 */
public interface BitmapDisplayer {
    void display(Bitmap bitmap, ImageView imageAware);
}
