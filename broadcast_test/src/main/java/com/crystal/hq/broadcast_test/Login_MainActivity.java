package com.crystal.hq.broadcast_test;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Crystal on 2017/5/10.
 * 登录成功后进入的主界面对应的Activity
 */

public class Login_MainActivity extends BaseActivity {

    private Button force_offline_btn;
    private Button create_db;
    private Button add_data;
    private Button delete_data;
    private Button update_data;
    private Button retrieve_data;

    private Button replace_db_data;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        force_offline_btn = (Button) findViewById(R.id.force_offline);
        force_offline_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog_builder = new AlertDialog.Builder(Login_MainActivity.this);
                dialog_builder.setTitle("警告")
                        .setMessage("您已被强制下线，请重新登录。")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intt = new Intent("com.crystal.hq.broadcast_test.FORCE_OFFLINE");
                                //广播已在manifest.xml中静态注册
                                sendBroadcast(intt);
                            }
                        });
                AlertDialog alertDialog = dialog_builder.create();
                //alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                alertDialog.show();
            }
        });

        //新建一个test数据库
        dbHelper = new DatabaseHelper(Login_MainActivity.this, "test.db", null, 1);
        create_db = (Button) findViewById(R.id.create_db);
        create_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        add_data = (Button) findViewById(R.id.add_data);
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "Hello World");
                values.put("author", "Tom King");
                values.put("pages", 342);
                values.put("price", 56);
                db.insert("book", null, values);
                values.clear();
                values.put("name", "Android SDK");
                values.put("author", "Dan Brown");
                values.put("pages", 158);
                values.put("price", 32);
                db.insert("book", null, values);
            }
        });

        delete_data = (Button) findViewById(R.id.delete_data);
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //将pages超过300的书删除
                db.delete("book", "pages>?", new String[]{"300"});
            }
        });

        update_data = (Button) findViewById(R.id.update_data);
        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //将Android SDK的pages改为256
                ContentValues values = new ContentValues();
                values.put("pages", 256);
                db.update("book", values, "name=?", new String[]{"Android SDK"});
            }
        });

        retrieve_data = (Button) findViewById(R.id.retrieve_data);
        retrieve_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询数据表中所有数据
                Cursor cursor = db.query("book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        Log.e("LMA Retrieve DB", cursor.getString(cursor.getColumnIndex("name")));
                        Log.e("LMA Retrieve DB", cursor.getString(cursor.getColumnIndex("author")));
                        Log.e("LMA Retrieve DB", cursor.getString(cursor.getColumnIndex("pages")));
                        Log.e("LMA Retrieve DB", cursor.getString(cursor.getColumnIndex("price")));
                    }
                    while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        replace_db_data = (Button) findViewById(R.id.replace_db_data);
        replace_db_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                try {
                    db.beginTransaction();
                    db.delete("book", null, null);
                    if (true)
                        throw new NullPointerException();   //假设删除数据失败，抛出异常
                    ContentValues values = new ContentValues();
                    values.put("name", "Java");
                    values.put("author", "JS");
                    values.put("pages", 321);
                    values.put("price", 45);
                    db.insert("book", null, values);
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
