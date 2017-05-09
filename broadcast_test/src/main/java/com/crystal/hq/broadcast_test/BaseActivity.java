package com.crystal.hq.broadcast_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Crystal on 2017/5/9.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //输出当前Activity的名字
        Log.d("BaseActivity", getClass().getSimpleName());
    }
}
