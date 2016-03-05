/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.image.download;

import java.io.IOException;
import java.io.InputStream;

import com.pan.learn.image.R;
import com.pan.learn.image.display.FadeInBitmapDisplayer;
import com.pan.learn.image.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by panhongchao on 16/3/2.
 */
public class DownLoadTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ImageView view = new ImageView(this);
        final BaseImageDownloader baseImageDownloader = new BaseImageDownloader(this);

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                InputStream stream = null;
                try {
                    stream = baseImageDownloader.getStream("http://www.baidu.com/img/bd_logo1.png", null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return BitmapFactory.decodeStream(stream);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
//                    view.setImageBitmap(bitmap);
//                    new FadeInBitmapDisplayer(3000).display(bitmap, view);
                    new RoundedBitmapDisplayer(10).display(bitmap, view);
                }
            }

        }.execute();

        view.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        setContentView(view);
    }
}
