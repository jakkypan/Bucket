/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.classloader.second;

/**
 * Created by panhongchao on 16/3/17.
 */
public class Sample {
    public Sample(){
        System.out.println("Sample is loaded by " + this.getClass().getClassLoader());
        new A();
    }
}
