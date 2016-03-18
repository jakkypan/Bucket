/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.classloader.second;

/**
 * Created by panhongchao on 16/3/17.
 */
public class A {
    public A(){
        System.out.println("A is loaded by " + this.getClass().getClassLoader());
    }
}
