package com.crystal.hq.uibestpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Crystal on 2017/5/8.
 */

public class AnotherRightFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局，并转换成View
        View view = inflater.inflate(R.layout.another_right_fragment_layout, container, false);
        return view;
    }
}