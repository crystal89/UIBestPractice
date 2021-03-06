package com.crystal.hq.uibestpractice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private EditText input_edit_text;
    private Button send_button;
    private ListView msg_list_view;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<Msg>();

    private Button fragment_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMSG();
        //将消息存放到适配器中
        msgAdapter = new MsgAdapter(MainActivity.this, R.layout.message_item_layout, msgList);
        msg_list_view = (ListView) findViewById(R.id.msg_list_view);
        msg_list_view.setAdapter(msgAdapter);

        input_edit_text = (EditText) findViewById(R.id.input_edit_text);
        send_button = (Button) findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String context = input_edit_text.getText().toString();
                if (!"".equals(context)) {
                    Msg msg = new Msg(context, Msg.TYPE_SEND);
                    msgList.add(msg);
                    //有新消息时刷新ListView的显示
                    msgAdapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    msg_list_view.setSelection(msgList.size());
                    input_edit_text.setText("");
                }
            }
        });

        fragment_button =(Button)findViewById(R.id.button);
        fragment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        AnotherRightFragment anotherRF = new AnotherRightFragment();
                        FragmentManager fm =getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.right_fragment,anotherRF,"com.crystal.hq.uibestpractice.AnotherRightFragment");
//                        transaction.replace(R.id.right_fragment,anotherRF);
                        //Back键，返回栈RightFragment
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //初始化一些消息内容
    private void initMSG() {
        msgList.add(new Msg("Hello!", Msg.TYPE_RECIVED));
        msgList.add(new Msg("Hi,Who is that?", Msg.TYPE_SEND));
        msgList.add(new Msg("Tom,Nice talking to you.", Msg.TYPE_RECIVED));
    }
}
