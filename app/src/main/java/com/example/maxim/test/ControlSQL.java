package com.example.maxim.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlSQL extends SQLiteOpenHelper {




    public ControlSQL(Context context) {
        super(context, "maxim", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // создаем таблицу с полями
        db.execSQL("create table table1 ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "family text,"
                + "surname text,"
                + "login text unique,"
                + "birthday text,"
                + "password text" + ");");
        db.execSQL("create table table2 ("
                + "id integer primary key autoincrement,"
                + "login text unique,"
                + "raiting integer,"
                + "answer1 text,"
                + "answer2 text,"
                + "answer3 text,"
                + "answer4 text,"
                + "answer5 text,"
                + "answer6 text,"
                + "answer7 text" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
