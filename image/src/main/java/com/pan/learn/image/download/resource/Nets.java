/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download.resource;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.pan.learn.image.download.util.ContentLengthInputStream;

import android.net.Uri;

/**
 * 网络请求的图片
 *
 * http://xxxx
 *
 * Created by panhongchao on 16/3/4.
 */
public class Nets {
    /**
     * http连接超时时间
     */
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 5 * 1000;
    /**
     * http连接读取时间
     */
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 20 * 1000;
    protected static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    protected static final int MAX_REDIRECT_COUNT = 5;
    protected static final int BUFFER_SIZE = 32 * 1024;

    protected int connectTimeout;
    protected int readTimeout;

    public Nets(int readTimeout, int connectTimeout) {
        this.readTimeout = readTimeout;
        this.connectTimeout = connectTimeout;
    }

    public Nets() {
        this.readTimeout = DEFAULT_HTTP_READ_TIMEOUT;
        this.connectTimeout = DEFAULT_HTTP_CONNECT_TIMEOUT;
    }

    public InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
        HttpURLConnection conn = createConnection(imageUri, extra);

        int redirectCount = 0;
        while (conn.getResponseCode() / 100 == 3 && redirectCount < MAX_REDIRECT_COUNT) {
            conn = createConnection(conn.getHeaderField("Location"), extra);
            redirectCount++;
        }

        InputStream imageStream;
        try {
            imageStream = conn.getInputStream();
        } catch (IOException e) {
            // Read all data to allow reuse connection (http://bit.ly/1ad35PY)
            readAndCloseStream(conn.getErrorStream());
            throw e;
        }
        if (conn.getResponseCode() != 200) {
            closeSilently(imageStream);
            throw new IOException("Image request failed with response code " + conn.getResponseCode());
        }

        return new ContentLengthInputStream(new BufferedInputStream(imageStream, BUFFER_SIZE), conn.getContentLength());
    }

    protected HttpURLConnection createConnection(String url, Object extra) throws IOException {
        String encodedUrl = Uri.encode(url, ALLOWED_URI_CHARS);
        HttpURLConnection conn = (HttpURLConnection) new URL(encodedUrl).openConnection();
        conn.setConnectTimeout(connectTimeout);
        conn.setReadTimeout(readTimeout);
        return conn;
    }

    public void readAndCloseStream(InputStream is) {
        final byte[] bytes = new byte[BUFFER_SIZE];
        try {
            while (is.read(bytes, 0, BUFFER_SIZE) != -1);
        } catch (IOException ignored) {
        } finally {
            closeSilently(is);
        }
    }

    public void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignored) {
            }
        }
    }
}
