/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.kettle;

/**
 * Created by panhongchao on 16/4/5.
 */
public class Heater {
    private boolean isHot;

    public void on() {
        System.out.println("开始烧开水啦");
        isHot = true;
    }

    public void off() {
        System.out.println("关闭加热器");
        isHot = false;
    }

    public boolean isHot() {
        return isHot;
    }
}