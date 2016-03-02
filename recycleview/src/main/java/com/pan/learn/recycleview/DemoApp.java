/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.recycleview;

import java.util.ArrayList;

/**
 * Created by panhongchao on 16/3/1.
 */
public class DemoApp {

    public static ArrayList<SampleModel> getSampleData (int size) {

        ArrayList<SampleModel> sampleData = new ArrayList<SampleModel>(size);

        for (int i = 0; i < size; i++) {
            sampleData.add(new SampleModel("Element "+i));
        }

        return sampleData;

    }
}
