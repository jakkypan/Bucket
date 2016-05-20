/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.database.greendao;

import java.util.Date;

import com.pan.lean.dao.DaoMaster;
import com.pan.lean.dao.DaoSession;
import com.pan.lean.dao.Note;
import com.pan.lean.dao.NoteDao;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

/**
 * Created by panhongchao on 16/5/20.
 */
public class GreenDaoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1、get database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "green-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();

        // 2、
        DaoMaster master = new DaoMaster(db);
        DaoSession session = master.newSession();
        NoteDao dao = session.getNoteDao();

        // 3、insert data
        Note node = new Note();
        node.setText("123");
        node.setComment("dsf");
        node.setComment("212");
        node.setDate(new Date());
        dao.insert(node);

        // 4、
    }
}
