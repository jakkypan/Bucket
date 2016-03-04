/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.crashpick.common;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

/**
 * Created by panhongchao on 16/3/3.
 */
public class CrashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("自定义异常：这是自己抛出的异常");
            }
        });
        button.setText(Environment.getExternalStorageDirectory().getPath());
        setContentView(button);
    }
}
