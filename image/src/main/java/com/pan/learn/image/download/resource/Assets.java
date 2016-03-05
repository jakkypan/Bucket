/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download.resource;

import java.io.IOException;
import java.io.InputStream;

import com.pan.learn.image.download.ImageDownloader;

import android.content.Context;

/**
 * Assets下的文件
 *
 * "assets://image.png" // from assets
 *
 * Created by panhongchao on 16/3/4.
 */
public class Assets {
    public static InputStream getStreamFromAssets(Context context, String imageUrl, Object extra) throws IOException {
        String filePath = ImageDownloader.Scheme.ASSETS.crop(imageUrl);
        return context.getAssets().open(filePath);
    }
}
