/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.ndk.simple;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by panhongchao on 16/4/1.
 */
public class MainActivity extends Activity {
    static {
        System.loadLibrary("HelloWorld");
    }

    public native String getStringFromNative();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txtView = new TextView(this);
        txtView.setText(getStringFromNative());
        setContentView(txtView);
    }
}
