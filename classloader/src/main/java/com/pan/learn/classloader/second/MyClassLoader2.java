/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.classloader.second;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by panhongchao on 16/3/17.
 */
public class MyClassLoader2 extends ClassLoader {

    //类加载器名称
    private String name;
    //加载类的路径
    private String path = "D:/";
    private final String fileType = ".class";

    public MyClassLoader2(String name) {
        //让系统类加载器成为该 类加载器的父加载器
        super();
        this.name = name;
    }

    public MyClassLoader2(ClassLoader parent, String name) {
        //显示指定该类加载器的父加载器
        super(parent);
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * 获取.class文件的字节数组
     *
     * @param name
     *
     * @return
     */
    private byte[] loaderClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        this.name = this.name.replace(".", "/");
        try {
            File f = new File(path + name + fileType);
            is = new FileInputStream(f);
            int c = 0;
            while (-1 != (c = is.read())) {
                baos.write(c);
            }
            data = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
     * 获取Class对象
     */
    @Override
    public Class<?> findClass(String name) {
        byte[] data = loaderClassData(name);
        return this.defineClass("com.pan.learn.classloader.second." + name, data, 0, data.length);
    }
}
