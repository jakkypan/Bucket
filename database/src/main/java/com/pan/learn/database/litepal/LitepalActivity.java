/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.database.litepal;

import java.util.Date;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by panhongchao on 16/5/20.
 */
public class LitepalActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase db = Connector.getDatabase();

        New n = new New();
        n.setTitle("12");
        n.setContent("12e12");
        n.setPublishDate(new Date());
        n.save();

        ContentValues values = new ContentValues();
        values.put("title", "今日iPhone6发布");
        DataSupport.updateAll(New.class, values, "title=?", "12");
    }
}
