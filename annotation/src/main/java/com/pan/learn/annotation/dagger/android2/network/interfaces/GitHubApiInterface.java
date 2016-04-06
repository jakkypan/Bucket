/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2.network.interfaces;


import java.util.ArrayList;

import com.pan.learn.annotation.dagger.android2.models.Repository;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubApiInterface {
    @GET("/org/{orgName}/repos")
    Call<ArrayList<Repository>> getRepository(@Path("orgName") String orgName);

}