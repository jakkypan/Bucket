/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.classloader.second;

/**
 * Created by panhongchao on 16/3/17.
 */
public class Test {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //loader1的父加载器为系统类加载器
        MyClassLoader2 loader1 = new MyClassLoader2("loader1");
        loader1.setPath("/Users/panhongchao/Downloads/Learn/classloader/src/main/java/com/pan/learn/classloader"
                + "/second/cls/");
        //loader2的父加载器为loader1
        MyClassLoader2 loader2 = new MyClassLoader2(loader1, "loader2");
        loader2.setPath("/Users/panhongchao/Downloads/Learn/classloader/src/main/java/com/pan/learn/classloader"
                + "/second/cls/");
        //loader3的父加载器为根类加载器
        MyClassLoader2 loader3 = new MyClassLoader2(null, "loader3");
        loader3.setPath("/Users/panhongchao/Downloads/Learn/classloader/src/main/java/com/pan/learn/classloader"
                + "/second/cls/");

        Class clazz = loader3.loadClass("Sample");
        Object object = clazz.newInstance();
    }
}
