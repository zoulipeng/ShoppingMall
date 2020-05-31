package com.example.shoppingmall.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.base.BaseFragment;

public class CommunityFragment extends BaseFragment {
    private TextView textView;
    private static String TAG= CommunityFragment.class.getSimpleName();//得到类的简写名称
    @Override
    protected View initView() {
        Log.e("TAG","主页面被初始化了");
        textView=new TextView(mContext);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG","主页面数据被初始化了");
        textView.setText("This is CommunityFragment");
    }

}
