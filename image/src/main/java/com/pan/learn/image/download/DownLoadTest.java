/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by panhongchao on 16/3/2.
 */
public class DownLoadTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView view = new ImageView(this);
        BaseImageDownloader baseImageDownloader = new BaseImageDownloader(this);
        InputStream stream = null;
        try {
            stream = baseImageDownloader.getStream("assets://t.png", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setImageBitmap(BitmapFactory.decodeStream(stream));
        setContentView(view);
    }
}
