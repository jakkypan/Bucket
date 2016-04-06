/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android.ui;

import java.util.List;

import com.pan.learn.annotation.dagger.android.Category;

/**
 * Created by Jhordan on 07/12/15.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<Category> categories);

    void showMessage(String message);
}
