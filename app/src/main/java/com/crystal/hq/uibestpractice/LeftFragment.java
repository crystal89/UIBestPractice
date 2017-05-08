package com.crystal.hq.uibestpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Crystal on 2017/5/8.
 */

public class LeftFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //动态加载左侧碎片布局
        View view = inflater.inflate(R.layout.left_fragment_layout, container, false);
        return view;
    }
}
