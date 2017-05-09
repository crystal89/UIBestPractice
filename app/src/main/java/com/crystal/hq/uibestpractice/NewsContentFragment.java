package com.crystal.hq.uibestpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Crystal on 2017/5/8.
 */

public class NewsContentFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_fragment_layout, container, false);
        return view;
    }

    public void onRefresh(String title, String content) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitle = (TextView) view.findViewById(R.id.news_title);
        TextView newsContent = (TextView) view.findViewById(R.id.news_content);
        newsTitle.setText(title);
        newsContent.setText(content);
    }
}
