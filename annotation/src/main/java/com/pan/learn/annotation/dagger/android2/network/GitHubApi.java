/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2.network;

import java.util.ArrayList;

import com.pan.learn.annotation.dagger.android2.models.Repository;
import com.pan.learn.annotation.dagger.android2.network.interfaces.GitHubApiInterface;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class GitHubApi {

    Retrofit mRetrofit;

    GitHubApiInterface mApiInterface;

    public interface ResponseHandler {
        public void onResponse(Object data);
        public void onFailure();
    }

    public GitHubApi(Retrofit retrofit) {
        mRetrofit = retrofit;
        mApiInterface = retrofit.create(GitHubApiInterface.class);
    }

    public void getRepos(String orgName, final ResponseHandler responseHandler) {
        Call<ArrayList<Repository>> call = mApiInterface.getRepository(orgName);

        call.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Response<ArrayList<Repository>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                responseHandler.onFailure();
            }
        });
    }
}
