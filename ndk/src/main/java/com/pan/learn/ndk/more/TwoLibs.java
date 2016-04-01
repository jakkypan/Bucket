/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.ndk.more;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;

public class TwoLibs extends Activity
{
    static {
        System.loadLibrary("Second");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView  tv = new TextView(this);
        int       x  = 1000;
        int       y  = 42;

        int  z = add(x, y);
        int zz = minus(x, y);

        tv.setText( "The sum of " + x + " and " + y + " is " + z + "\n" +
                "The left of " + x + " minus " + y + " is " + zz);
        setContentView(tv);
    }

    public native int add(int  x, int  y);

    public native int minus(int  x, int  y);
}
