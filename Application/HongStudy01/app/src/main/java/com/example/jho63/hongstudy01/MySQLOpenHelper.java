package com.example.jho63.hongstudy01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLOpenHelper extends SQLiteOpenHelper {
    public MySQLOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryContact = "create table contact ("+
                "cid integer primary key autoincrement, "+
                "name text, "+
                "phonenum text, "+
                "email text, "+
                "profile text);";

        db.execSQL(queryContact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if EXISTS contact;";
        db.execSQL(query);
        onCreate(db);
    }

}
