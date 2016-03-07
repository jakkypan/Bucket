/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.material;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by panhongchao on 16/3/7.
 */
public class IndexFragment extends Fragment {
    private FragmentListener listener;

    public interface FragmentListener {
        void change(int index);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        listener = (FragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment, container, false);
        view.findViewById(R.id.text_input_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.change(0);
            }
        });

        view.findViewById(R.id.floating_action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.change(1);
            }
        });

        view.findViewById(R.id.tab_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.change(2);
            }
        });

        view.findViewById(R.id.snackbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(getActivity().getWindow().getDecorView(), "this is snackbar",
                        Snackbar.LENGTH_LONG);
                snackbar.setAction("close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });

        view.findViewById(R.id.coordinator_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.change(3);
            }
        });
        return view;
    }
}
