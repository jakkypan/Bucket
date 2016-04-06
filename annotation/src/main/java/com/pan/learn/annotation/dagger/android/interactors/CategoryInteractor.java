

/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android.interactors;

import com.pan.learn.annotation.dagger.android.CallBack;

/**
 * Created by Jhordan on 07/12/15.
 */

public interface CategoryInteractor {

    void findItems(CallBack listener);
}
