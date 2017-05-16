package com.crystal.hq.broadcast_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Crystal on 2017/5/10.
 * 强制下线的广播接收器
 */

public class Login_ForceOfflineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            //销毁所有Activity
            ActivityController.finishAll();

            //重新启动LoginActivity
            Intent intent1 = new Intent(context, LoginActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        } catch (Exception e) {
            Log.e("Login_ForceOffline", e.toString());
        }
    }
}
