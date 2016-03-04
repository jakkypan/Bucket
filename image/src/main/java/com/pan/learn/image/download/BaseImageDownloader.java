/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * 图片下载类
 *
 * 支持各种资源的图片下载
 *
 * Created by panhongchao on 16/3/2.
 */
public class BaseImageDownloader implements ImageDownloader {
    /**
     * http连接超时时间
     */
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 5 * 1000;
    /**
     * http连接读取时间
     */
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 20 * 1000;
    /**
     * buffer的大小
     */
    protected static final int BUFFER_SIZE = 32 * 1024;
    protected static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    protected static final int MAX_REDIRECT_COUNT = 5;
    protected static final String CONTENT_CONTACTS_URI_PREFIX = "content://com.android.contacts/";
    private static final String ERROR_UNSUPPORTED_SCHEME = "UIL doesn't support scheme(protocol) by default [%s]. "
            + "You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))";

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
            case ASSETS:
                return getStreamFromAssets(imageUrl, extra);
            case DRAWABLE:
                return getStreamFromDrawable(imageUrl, extra);
            case FILE:

            case UNKNOWN:
            default:

        }
        return null;
    }

    private InputStream getStreamFromAssets(String imageUrl, Object extra) throws IOException {
        String filePath = Scheme.ASSETS.crop(imageUrl);
        return context.getAssets().open(filePath);
    }

    protected InputStream getStreamFromDrawable(String imageUri, Object extra) {
        String drawableIdString = Scheme.DRAWABLE.crop(imageUri);
        int drawableId = Integer.parseInt(drawableIdString);
        return context.getResources().openRawResource(drawableId);
    }

    protected InputStream getStreamFromFile(String imageUri, Object extra) throws IOException {
        String filePath = Scheme.FILE.crop(imageUri);
        if (isVideoFileUri(imageUri)) {
            return getVideoThumbnailStream(filePath);
        } else {
            BufferedInputStream imageStream = new BufferedInputStream(new FileInputStream(filePath), BUFFER_SIZE);
            return new ContentLengthInputStream(imageStream, (int) new File(filePath).length());
        }
    }
}
