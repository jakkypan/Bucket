/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.material;

import com.pan.learn.material.coordinator.CoordinatorLayoutFragment;
import com.pan.learn.material.tablayout.TabLayoutFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by panhongchao on 16/3/5.
 */
public class MaterialActivity extends FragmentActivity implements IndexFragment.FragmentListener {
    private static final String RIGHT = "contentfragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        IndexFragment leftFragment = new IndexFragment();
        transaction.add(R.id.left_layout, leftFragment, "indexfragment");
        transaction.commit();
    }

    @Override
    public void change(int index) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;
        switch (index) {
            case 0:
                fragment = new TextInputLayoutFragment();
                break;
            case 1:
                fragment = new FloatingActionButtonFragment();
                break;
            case 2:
                fragment = new TabLayoutFragment();
                break;
            case 3:
                fragment = new CoordinatorLayoutFragment();
                break;
        }
        if (manager.findFragmentByTag(RIGHT) == null) {
            transaction.add(R.id.right_layout, fragment, RIGHT);
        } else {
            transaction.replace(R.id.right_layout, fragment, RIGHT);
        }
        transaction.commit();
    }
}
