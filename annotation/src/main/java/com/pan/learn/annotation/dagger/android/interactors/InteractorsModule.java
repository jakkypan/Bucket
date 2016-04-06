

/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android.interactors;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorsModule {

    @Provides
    public CategoryInteractor provideFindItemsInteractor() {
        return new CategoryInteractorImpl();
    }


}
