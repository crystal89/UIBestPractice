package com.crystal.hq.uibestpractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Crystal on 2017/5/8.
 */

public class NewsContentActivity extends Activity {

    public static void actionStart(Context context, String title, String content) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("newsTitle", title);
        intent.putExtra("newsContent", content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_content_layout);
        String newsTitle = getIntent().getStringExtra("newsTitle");
        // 获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("newsContent");
        // 获取传入的新闻内容
        NewsContentFragment ncf = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
        // 刷新NewsContentFragment界面
        ncf.onRefresh(newsTitle, newsContent);
    }
}
