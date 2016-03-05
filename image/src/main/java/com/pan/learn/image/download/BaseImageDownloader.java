/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download;

import java.io.IOException;
import java.io.InputStream;

import com.pan.learn.image.download.resource.Assets;
import com.pan.learn.image.download.resource.Contents;
import com.pan.learn.image.download.resource.Drawables;
import com.pan.learn.image.download.resource.Extends;
import com.pan.learn.image.download.resource.Files;
import com.pan.learn.image.download.resource.Nets;

import android.content.Context;

/**
 * 图片下载类
 *
 * 支持各种资源的图片下载
 *
 * Created by panhongchao on 16/3/2.
 */
public class BaseImageDownloader implements ImageDownloader {
    protected Context context;
    protected int connectTimeout;
    protected int readTimeout;

    public BaseImageDownloader(Context context) {
        this.context = context;
    }

    public BaseImageDownloader(Context context, int connectTimeout, int readTimeout) {
        this.context = context;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public InputStream getStream(String imageUrl, Object extra) throws IOException {
        switch (Scheme.ofUri(imageUrl)) {
            case HTTP:
            case HTTPS:
                return new Nets().getStreamFromNetwork(imageUrl, extra);
            case ASSETS:
                return Assets.getStreamFromAssets(context, imageUrl, extra);
            case DRAWABLE:
                return Drawables.getStreamFromDrawable(context, imageUrl, extra);
            case FILE:
                return Files.getStreamFromFile(imageUrl, extra);
            case CONTENT:
                return Contents.getStreamFromContent(context, imageUrl, extra);
            case UNKNOWN:
            default:
                return Extends.getStreamFromOtherSource(imageUrl, extra);
        }
    }
}
