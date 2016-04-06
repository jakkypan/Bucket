/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.annotation.knife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

import static android.widget.Toast.LENGTH_SHORT;

import com.pan.learn.annotation.R;

public class SimpleActivity extends Activity {
    /**
     * 设置动作Action
     */
    private static final ButterKnife.Action<View> ALPHA_FADE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setStartOffset(index * 100);
            view.startAnimation(alphaAnimation);
        }
    };

    /**
     * 设置Setter
     */
    private static final ButterKnife.Setter<View, Boolean> SETTER = new ButterKnife.Setter() {

        @Override
        public void set(View view, Object value, int index) {
            TextView textView = (TextView) view;
        }
    };

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.subtitle)
    TextView subtitle;
    @Bind(R.id.hello)
    Button hello;
    @Bind(R.id.list_of_things)
    ListView listOfThings;
    @Bind(R.id.footer)
    TextView footer;

    @Bind({R.id.title, R.id.subtitle, R.id.hello})
    List<View> headerViews;

    private SimpleAdapter adapter;

    @OnClick(R.id.hello)
    void sayHello() {
        Toast.makeText(this, "Hello, views!", LENGTH_SHORT).show();
        ButterKnife.apply(headerViews, ALPHA_FADE);
        ButterKnife.apply(headerViews, SETTER, false);
    }

    @OnLongClick(R.id.hello)
    boolean sayGetOffMe() {
        Toast.makeText(this, "Let go of me!", LENGTH_SHORT).show();
        return true;
    }

    @OnItemClick(R.id.list_of_things)
    void onItemClick(int position) {
        Toast.makeText(this, "You clicked: " + adapter.getItem(position), LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        ButterKnife.bind(this);

        // Contrived code to use the bound fields.
        title.setText("Butter Knife");
        subtitle.setText("Field and method binding for Android views.");
        footer.setText("by Jake Wharton");
        hello.setText("Say Hello");

        adapter = new SimpleAdapter(this);
        listOfThings.setAdapter(adapter);
    }
}
