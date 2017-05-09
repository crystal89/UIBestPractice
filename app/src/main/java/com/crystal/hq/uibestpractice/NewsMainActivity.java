package com.crystal.hq.uibestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Crystal on 2017/5/9.
 */

public class NewsMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_main);
    }
}
