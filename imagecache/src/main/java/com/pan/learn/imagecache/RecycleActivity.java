/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.imagecache;

import java.util.ArrayList;
import java.util.List;

import com.pan.learn.imagecache.utils.Images;
import com.pan.learn.imagecache.utils.MyUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

/**
 * Created by panhongchao on 16/3/1.
 */
public class RecycleActivity extends Activity implements AbsListView.OnScrollListener {
    private List<String> mUrList = new ArrayList<String>();
    RecyclerView recyclerView;
    ImageLoader mImageLoader;
    private int mImageWidth = 0;
    private boolean mIsGridViewIdle = true;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);
        mImageLoader = ImageLoader.build(RecycleActivity.this);

        for (String url : Images.data()) {
            mUrList.add(url);
        }
        int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        int space = (int)MyUtils.dp2px(this, 20f);
        mImageWidth = (screenWidth - space) / 3;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new NormalRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            mIsGridViewIdle = true;
            adapter.notifyDataSetChanged();
        } else {
            mIsGridViewIdle = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public class NormalRecyclerViewAdapter
            extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
        public NormalRecyclerViewAdapter() {

        }

        // 创建新View，被LayoutManager所调用
        @Override
        public NormalTextViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_list_item, viewGroup, false);
            NormalTextViewHolder holder = new NormalTextViewHolder(view);
            return holder;
        }

        // 将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(NormalTextViewHolder normalTextViewHolder, int i) {
            if (mIsGridViewIdle) {
                String uri = mUrList.get(i);
                normalTextViewHolder.imageView.setTag(uri);
                mImageLoader.bindBitmap(uri, normalTextViewHolder.imageView, mImageWidth, mImageWidth);
            }
        }

        // 获取数据的数量
        @Override
        public int getItemCount() {
            return mUrList.size();
        }

        // 自定义的ViewHolder，持有每个Item的的所有界面元素
        class NormalTextViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public NormalTextViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image);
            }
        }
    }
}
