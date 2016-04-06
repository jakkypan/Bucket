/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.dagger.android2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import javax.inject.Inject;

import com.pan.learn.annotation.R;
import com.pan.learn.annotation.dagger.android2.network.interfaces.GitHubApiInterface;
import com.squareup.okhttp.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Inject
    GitHubApiInterface mGitHubApiInterface;

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    OkHttpClient mOkHttpClient;

    @Bind(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this, getWindow().getDecorView());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                textView.setText("nooooooo hello!");
                            }
                        }).show();
            }
        });

        ((MyApp) getApplication()).getGitHubComponent().inject(this);

    }
}
