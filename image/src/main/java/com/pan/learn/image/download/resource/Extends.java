/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 特殊文件留给应用方自己扩展
 *
 * Created by panhongchao on 16/3/4.
 */
public class Extends {
    private static final String ERROR_UNSUPPORTED_SCHEME = "UIL doesn't support scheme(protocol) by default [%s]. "
            + "You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))";

    public static InputStream getStreamFromOtherSource(String imageUri, Object extra) throws IOException {
        throw new UnsupportedOperationException(String.format(ERROR_UNSUPPORTED_SCHEME, imageUri));
    }
}
