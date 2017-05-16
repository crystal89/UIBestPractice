package com.crystal.hq.broadcast_test;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Crystal on 2017/5/15.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context _context;
    private String _Book = "create table book(id integer primary key autoincrement,name text,author text, pages integer,price real)";
    private String _CATEGORY = "create table category (id integer primary key autoincrement,category_name text,category_code integer)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(_Book);
            db.execSQL(_CATEGORY);
            Toast.makeText(_context, "Create database succeeded.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("exception: ", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        //数据表已存在，则删除后重新创建
        db.execSQL("drop table if exists book");
        onCreate(db);*/
        switch (oldVersion) {
            case 1:
                db.execSQL(_CATEGORY);
                break;
            case 2:
                db.execSQL("alter table book add column category_id integer");
            default:
        }
    }
}
