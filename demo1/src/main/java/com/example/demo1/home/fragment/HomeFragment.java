package com.example.demo1.home.fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.demo1.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    private TextView textView;
    public static String TAG=HomeFragment.class.getSimpleName();//获取当前Fragment的名称

    @Override
    protected View initView() {
         textView=new TextView(mContext);
         textView.setTextColor(Color.GREEN);
        return textView;
    }
    private void initData(){
        Log.e(TAG,"This is"+TAG);
        textView.setText("This is HomeFragment");
    }
}
