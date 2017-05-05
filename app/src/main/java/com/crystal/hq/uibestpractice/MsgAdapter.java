package com.crystal.hq.uibestpractice;

/**
 * Created by Crystal on 2017/5/5.
 */

import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 102003449 on 2017/5/5.
 */

public class MsgAdapter extends ArrayAdapter<Msg> {
    private int resourceId;

    public MsgAdapter(Context context, int viewResourceId, List<Msg> objects) {
        super(context, viewResourceId, objects);
        resourceId = viewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.left_layout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.right_layout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.left_text_view = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.right_text_view = (TextView) view.findViewById(R.id.right_msg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (msg.getType() == 0) {
            //接收到消息，左显右隐
            viewHolder.left_layout.setVisibility(View.VISIBLE);
            viewHolder.right_layout.setVisibility(View.GONE);
            viewHolder.left_text_view.setText(msg.getContent());
        } else if (msg.getType() == 1) {
            //发送出消息，左隐右显
            viewHolder.left_layout.setVisibility(View.GONE);
            viewHolder.right_layout.setVisibility(View.VISIBLE);
            viewHolder.right_text_view.setText(msg.getContent());
        }
        return view;
    }

    class ViewHolder {
        LinearLayout left_layout;
        LinearLayout right_layout;
        TextView left_text_view;
        TextView right_text_view;
    }
}
