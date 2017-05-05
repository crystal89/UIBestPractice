package com.crystal.hq.uibestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Crystal on 2017/5/5.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //输出当前Activity的名字
        Log.d("BaseActivity", getClass().getSimpleName());
    }
}
