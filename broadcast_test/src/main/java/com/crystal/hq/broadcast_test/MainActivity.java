package com.crystal.hq.broadcast_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.lang.UCharacter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private Button send_broadcast;
    private Button send_order_broadcast;
    private Button send_local_broadcast;

    //用于发送监听网络变化的广播
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    //用于发送本地广播
    private IntentFilter inttfilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态注册BroadcastReceiver，监听网络变化
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        //发送标准广播
        send_broadcast = (Button) findViewById(R.id.send_broadcast);
        send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.crystal.hq.broadcast_test.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

        //发送有序广播
        send_order_broadcast = (Button) findViewById(R.id.send_order_broadcast);
        send_order_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.crystal.hq.broadcast_test.MY_BROADCAST");
                sendOrderedBroadcast(intent, null);
            }
        });

        //发送本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);   //获取实例
        inttfilter = new IntentFilter();
        inttfilter.addAction("com.crystal.hq.broadcast_test.MY_LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, inttfilter);  //注册本地广播
        send_local_broadcast = (Button) findViewById(R.id.send_local_broadcast);
        send_local_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.crystal.hq.broadcast_test.MY_LOCAL_BROADCAST");
                //发送本地广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //监听到变化时，发送Toast消息
            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "Network is available.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Network is unavailable.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class BootCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Boot complete.", Toast.LENGTH_SHORT).show();
        }
    }

    public class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Received in local broadcast.", Toast.LENGTH_SHORT).show();
        }
    }
}
