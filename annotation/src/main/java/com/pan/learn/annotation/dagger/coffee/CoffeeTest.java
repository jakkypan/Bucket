/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.coffee;

/**
 * Created by panhongchao on 16/4/5.
 */
public class CoffeeTest {
    public static void main(String[] args) {
        CoffeeComponent coffee = DaggerCoffeeComponent.builder().build();
        coffee.maker().brew();
    }
}
