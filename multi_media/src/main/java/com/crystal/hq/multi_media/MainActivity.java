package com.crystal.hq.multi_media;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by Crystal on 2017/5/23.
 **/


public class MainActivity extends Activity {

    private NotificationManager notificationManager;
    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取系统Notification服务
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //在Activity中使用Notification
        sendNotice = (Button) findViewById(R.id.sendNotice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int notifyId = 1;
                //点击通知，更换当前活动中的Activity
                Intent resultIntent = new Intent(MainActivity.this, NoticeActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                //RemoteViews tickerView = new RemoteViews("com.crystal.hq.multi_media", R.layout.ticker_view);
                //创建Notification
                Notification.Builder notificationBuilder = new Notification.Builder(MainActivity.this)
                        .setSmallIcon(R.mipmap.message2)
                        .setContentTitle("notification")
                        .setContentText("this is a notification test.")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                //notificationBuilder.setCustomContentView(tickerView);

                //发送Notification
                notificationManager.notify(notifyId, notificationBuilder.build());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
