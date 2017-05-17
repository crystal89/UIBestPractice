package com.crystal.hq.broadcast_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Crystal on 2017/5/10.
 * 先执行 - 登录界面login.xml对应的LoginActivity
 */

public class LoginActivity extends BaseActivity {

    private EditText account_et;
    private EditText password_et;
    private Button login_btn;
    private CheckBox save_password_cb;
//    private String login_Info = null;
//    private Button clear_btn;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private Button to_contentMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        account_et = (EditText) findViewById(R.id.login_account);
        password_et = (EditText) findViewById(R.id.login_password);
        save_password_cb = (CheckBox) findViewById(R.id.save_password);
        login_btn = (Button) findViewById(R.id.login);
//        clear_btn = (Button) findViewById(R.id.clear_info);

/*        //从文件中获取数据
        login_Info = loadInfo();
        String[] info = login_Info.split("\\|");
        for (String str : info) {
            if (str.contains("UserAccount")) {
                account_et.setText(str.split(":")[1]);
                Log.e("account", str.split(":")[1].toString());
            }
            if (str.contains("UserPassword")) {
                password_et.setText(str.split(":")[1]);
                Log.e("password", str.split(":")[1].toString());
            }
        }*/

        //从SharedPreferences中读取数据
        try {
            prefs = getSharedPreferences("data", MODE_PRIVATE);
            if (prefs != null) {
                boolean isRemember = prefs.getBoolean("rem_password", false);
                Log.e("SharedPreferences", prefs.getAll().toString());
                if (isRemember) {
                    account_et.setText(prefs.getString("u_account", ""));
                    password_et.setText(prefs.getString("u_password", ""));
                    save_password_cb.setChecked(isRemember);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = account_et.getText().toString();
                String password = password_et.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    if (save_password_cb.isChecked()) {
                        //测试-保存文本信息到data文件中
//                    saveInfo(account, password);

                        //测试-保存数据到SharedPreferences中
                        editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                        editor.putString("u_account", account);
                        editor.putString("u_password", password);
                        editor.putBoolean("rem_password", true);
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, Login_MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "请输入正确的用户名和密码！", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清楚SharedPreferences中的数据
                SharedPreferences.Editor prefsEditor = getSharedPreferences("setting", 0).edit();
                prefsEditor.remove("u_account");
                prefsEditor.remove("u_password");
                prefsEditor.remove("rem_password");
                //prefsEditor.clear();  //将数据恢复到默认值得清空
                prefsEditor.commit();

                account_et.setText("");
                password_et.setText("");
                save_password_cb.setChecked(false);
            }
        });*/

        to_contentMainActivity = (Button) findViewById(R.id.to_contentMainActivity);
        to_contentMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ContentMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //#region "保存数据到文件中"
    private void saveInfo(String account, String password) {
        BufferedWriter writer = null;
        try {
            FileOutputStream out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("UserAccount:" + account);
            writer.append("|UserPassword:" + password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String loadInfo() {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream input = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(input));
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    //#endregion
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
