/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.classloader.first;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by panhongchao on 16/3/17.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException, NoSuchMethodException, SecurityException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

        MyClassLoader loader = new MyClassLoader();

        loadClass(loader);

//        findClass1(loader);

//        findClass2(loader);
    }

    /**
     * 使用自定义的ClassLoader去做的类的方法去加载的
     *
     * @param loader
     */
    private static void findClass1(MyClassLoader loader)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
            IllegalArgumentException, InvocationTargetException {
        Class<?> c = loader.findClass("com.pan.learn.classloader.first.BMW");
        System.out.println("Loaded by :" + c.getClassLoader());
        Object ob = c.newInstance();
        Method md = c.getMethod("run");
        md.invoke(ob);
    }

    /**
     * loadClass，没有重写，所以是由父类classloader加载的
     *
     * @param loader
     */
    private static void loadClass(MyClassLoader loader)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        Class<?> c = loader.loadClass("com.pan.learn.classloader.first.BMW");
        System.out.println("Loaded by :" + c.getClassLoader());

        Car car = (Car) c.newInstance();
        car.run();
        BMW bmw = (BMW) c.newInstance();
        bmw.run();
    }

    /**
     * 使用自定义的ClassLoader去做的类的方法去加载的
     *
     * error!!!!!!!
     *
     * @param loader
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static void findClass2(MyClassLoader loader)
            throws InstantiationException,
            IllegalAccessException {
        Class<?> c = loader.findClass("com.pan.learn.classloader.first.BMW");
        System.out.println("Loaded by :" + c.getClassLoader());
        Car car = (Car) c.newInstance();
        car.run();
        BMW bmw = (BMW) c.newInstance();
        bmw.run();
    }
}
