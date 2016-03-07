/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.material.coordinator;

import java.util.ArrayList;

import com.pan.learn.material.R;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by panhongchao on 16/3/6.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Holder> {
    private ArrayList<String> sampleData;

    public RecycleAdapter() {
        sampleData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            sampleData.add("items" + i);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_basic_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        holder.textViewSample.setText(sampleData.get(i));
    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textViewSample;

        public Holder(View itemView) {
            super(itemView);
            textViewSample = (TextView) itemView.findViewById(R.id.textViewSample);
        }
    }
}
