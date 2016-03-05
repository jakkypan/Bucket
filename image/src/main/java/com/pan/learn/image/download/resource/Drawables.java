/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download.resource;

import java.io.InputStream;

import com.pan.learn.image.download.ImageDownloader;

import android.content.Context;

/**
 * drawable文件
 *
 * "drawable://" + R.drawable.img // from drawables (non-9patch images)
 *
 * Created by panhongchao on 16/3/4.
 */
public class Drawables {
    public static InputStream getStreamFromDrawable(Context context, String imageUri, Object extra) {
        String drawableIdString = ImageDownloader.Scheme.DRAWABLE.crop(imageUri);
        int drawableId = Integer.parseInt(drawableIdString);
        return context.getResources().openRawResource(drawableId);
    }
}
