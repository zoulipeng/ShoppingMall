package com.example.shoppingmall.base;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
////作用:基类Fragment
//  首页:HomeFragment
//  分类：TypeFragment
//  发现：CommunityFragment
//  购物车:ShoppingCartFragment
//用户中心:UserFragment
//等都要继承该类

public abstract class BaseFragment extends Fragment {
    //上下文对象
    public Activity mContext;
    //当该类被系统创建的时候被回调
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    //子Fragment的UI创建
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //抽象类，由孩子实现，实现不同效果
    protected abstract View initView();

    //当Activity被创建了的时候回调这个方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //当子类需要联网请求数据的时候，可以重写该方法，在该方法中联网请求
    public void initData() {



    }
}


