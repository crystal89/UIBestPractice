package com.crystal.hq.broadcast_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Crystal on 2017/5/9.
 * 向ActivityController中添加或者移除activity
 * 继承BaseActivity类的activity在onCreate时打印名称
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
        //输出当前Activity的名字
        Log.d("BaseActivity", getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
