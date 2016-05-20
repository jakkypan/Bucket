/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.pan.learn.database.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by panhongchao on 16/5/20.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    /**
     * insert new items of persons
     *
     * @param persons
     */
    public void add(List<Person> persons) {
        database.beginTransaction();
        try {
            for (Person person : persons) {
                database.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)",
                        new Object[] {person.name, person.age, person.info});
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public void updateAge(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("age", person.age);
        database.update("person", cv, "name = ?", new String[]{person.name});
    }

    public void delete(Person person) {
        database.delete("person", "age >= ?", new String[] {String.valueOf(person.age)});
    }

    public List<Person> query() {
        ArrayList<Person> persons = new ArrayList<>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Person person = new Person();
            person._id = c.getInt(c.getColumnIndex("_id"));
            person.name = c.getString(c.getColumnIndex("name"));
            person.age = c.getInt(c.getColumnIndex("age"));
            person.info = c.getString(c.getColumnIndex("info"));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    public Cursor queryTheCursor() {
        Cursor c = database.rawQuery("SELECT * FROM person", null);
        return c;
    }

    public void closeDB() {
        database.close();
    }
}
