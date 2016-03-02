/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.pinnedview;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * Created by panhongchao on 16/3/2.
 */
public class FriendsAdapter extends BaseAdapter implements SectionIndexer, PinnedHeaderListView.PinnedHeaderAdapter,
        AbsListView.OnScrollListener {
    private int mLocationPosition = -1;
    private String[] mDatas;
    private List<String> mFriendsSections;
    private List<Integer> mFriendsPositions;
    private LayoutInflater inflater;

    public FriendsAdapter(Context context,String[] datas, List<String> friendsSections,
                          List<Integer> friendsPositions) {
        inflater = LayoutInflater.from(context);
        mDatas = datas;
        mFriendsSections = friendsSections;
        mFriendsPositions = friendsPositions;
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int section = getSectionForPosition(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, null);
        }
        LinearLayout mHeaderParent = (LinearLayout) convertView.findViewById(R.id.friends_item_header_parent);
        TextView mHeaderText = (TextView) convertView.findViewById(R.id.friends_item_header_text);
        if (getPositionForSection(section) == position) {
            mHeaderParent.setVisibility(View.VISIBLE);
            mHeaderText.setText(mFriendsSections.get(section));
        } else {
            mHeaderParent.setVisibility(View.GONE);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.friends_item);
        textView.setText(mDatas[position]);
        return convertView;
    }

    @Override
    public int getPinnedHeaderState(int position) {
        int realPosition = position;
        if (realPosition < 0
                || (mLocationPosition != -1 && mLocationPosition == realPosition)) {
            return PINNED_HEADER_GONE;
        }
        mLocationPosition = -1;
        int section = getSectionForPosition(realPosition);
        int nextSectionPosition = getPositionForSection(section + 1);
        if (nextSectionPosition != -1
                && realPosition == nextSectionPosition - 1) {
            return PINNED_HEADER_PUSHED_UP;
        }
        return PINNED_HEADER_VISIBLE;
    }

    @Override
    public void configurePinnedHeader(View header, int position, int alpha) {
        int realPosition = position;
        int section = getSectionForPosition(realPosition);
        String title = (String) getSections()[section];
        ((TextView) header.findViewById(R.id.friends_list_header_text))
                .setText(title);
    }

    @Override
    public Object[] getSections() {
        return mFriendsSections.toArray();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex < 0 || sectionIndex >= mFriendsSections.size()) {
            return -1;
        }
        return mFriendsPositions.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position < 0 || position >= getCount()) {
            return -1;
        }
        int index = Arrays.binarySearch(mFriendsPositions.toArray(), position);
        return index >= 0 ? index : -index - 2;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
        }
    }
}
