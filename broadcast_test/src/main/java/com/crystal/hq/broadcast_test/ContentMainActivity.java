package com.crystal.hq.broadcast_test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crystal on 2017/5/16.
 */

public class ContentMainActivity extends BaseActivity {

    private Button to_loginActivity;
    private ListView contacts_list;
    private List<String> contactsList = new ArrayList<String>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        //返回到登录界面
        to_loginActivity = (Button) findViewById(R.id.to_loginActivity);
        to_loginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initContacts();
        contacts_list = (ListView) findViewById(R.id.contacts_list);
        adapter = new ArrayAdapter(ContentMainActivity.this, android.R.layout.simple_list_item_1, contactsList);
        contacts_list.setAdapter(adapter);
    }

    //将系统联系人信息添加到contactsLis中
    private void initContacts() {
        Cursor cursor = null;

        try {
            //查询联系人
            //String[] PHONES_PROJECTION = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人name
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人phoneNumber
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(name + " \n " + phoneNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            Log.i("ContentMainActivity", Integer.toString(contactsList.size()));
        }
    }
}
