package com.example.myannounce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "announce_daily.db";

    public DBHelper(Context context) { super(context, DB_NAME , null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table announce(_id integer primary key autoincrement," +
                "uName varchar(20)," +
                "Title varchar(20),"+
                "Details varchar(20),"+
                "Date varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}