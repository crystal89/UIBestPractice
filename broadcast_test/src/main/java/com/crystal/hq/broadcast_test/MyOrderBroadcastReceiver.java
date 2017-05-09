package com.crystal.hq.broadcast_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Crystal on 2017/5/9.
 * 发送有序广播
 */

public class MyOrderBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received in my order broadcast.", Toast.LENGTH_SHORT).show();
    }
}
