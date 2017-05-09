package com.crystal.hq.uibestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Crystal on 2017/5/8.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private int resource_Id;

    public NewsAdapter(Context context, int text_view_resource_Id, List<News> objects) {
        super(context, text_view_resource_Id, objects);
        resource_Id = text_view_resource_Id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource_Id, null);
        } else {
            view = convertView;
        }
        TextView text_view_title = (TextView) view.findViewById(R.id.news_title);
        text_view_title.setText(news.getTitle());

        return view;
    }
}
