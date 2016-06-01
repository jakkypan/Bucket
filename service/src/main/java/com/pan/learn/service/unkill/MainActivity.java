/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.service.unkill;

import com.pan.learn.service.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void triggerService(View view) {
        boolean started = AlipayService.trigger(this);
        ((Button)view).setText(started ? "Stop" : "Start");
    }
}
