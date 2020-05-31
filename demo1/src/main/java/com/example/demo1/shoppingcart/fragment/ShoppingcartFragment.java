package com.example.demo1.shoppingcart.fragment;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.demo1.base.BaseFragment;
public class ShoppingcartFragment extends BaseFragment {
    private TextView textView;
    public static String TAG= ShoppingcartFragment.class.getSimpleName();//获取当前Fragment的名称

    @Override
    protected View initView() {
         textView=new TextView(mContext);
         textView.setTextColor(Color.GREEN);
        return textView;
    }
    public void initData() {
        Log.e("TAG","主页面数据被初始化了");
        textView.setText("This is ShoppingCartFragment");
    }
}
