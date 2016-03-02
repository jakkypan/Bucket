/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.recycleview;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by panhongchao on 16/3/1.
 */
public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.ViewHolder> {
    private ArrayList<SampleModel> sampleData;

    public SampleRecyclerAdapter() {
        sampleData = DemoApp.getSampleData(20);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_basic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SampleModel rowData = sampleData.get(position);
        holder.textViewSample.setText(rowData.getSampleText());
        holder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }

    public void removeData (int position) {
        sampleData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int positionToAdd) {
        sampleData.add(positionToAdd, new SampleModel("New element"));
        notifyItemInserted(positionToAdd);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSample;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewSample = (TextView) itemView.findViewById(R.id.textViewSample);
        }
    }
}
