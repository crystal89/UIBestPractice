package com.crystal.hq.broadcast_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by Crystal on 2017/5/10.
 */

public class Login_DialogActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(Login_DialogActivity.this);
        dialog_builder.setTitle("警告")
                .setMessage("您已被强制下线，请重新登录。")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //销毁所有Activity
                        ActivityController.finishAll();
                        //重新启动LoginActivity
                        Intent intent = new Intent(Login_DialogActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Login_DialogActivity.this.startActivity(intent);
                    }
                });
        AlertDialog alertDialog = dialog_builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
        alertDialog.show();
    }
}
