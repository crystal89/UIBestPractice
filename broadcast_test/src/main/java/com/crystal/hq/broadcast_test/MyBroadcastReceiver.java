package com.crystal.hq.broadcast_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Crystal on 2017/5/9.
 * 发送标准广播
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received in my broadcast.", Toast.LENGTH_LONG).show();

        //广播被截断，后面的接收器无法接受到广播消息。
        abortBroadcast();
    }
}
