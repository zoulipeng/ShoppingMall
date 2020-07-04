package com.example.stardream.starfrag;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.stardream.R;
import com.example.stardream.bean.StarInfoBean;

import java.util.List;
public class StartFragment extends Fragment {
    private ViewPager mStarfragVp;
    private GridView mStarfragGv;
    private LinearLayout pointLayout;
    private List<StarInfoBean.StarinfoBean> mData;
    private StarBaseAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star, container, false);
       initView(view);
        return view;
    }
//初始化控件操作
    private void initView(View view) {
        mStarfragVp=view.findViewById(R.id.starfrag_vp);
        mStarfragGv=view.findViewById(R.id.starfrag_gv);
        pointLayout=view.findViewById(R.id.starfrag_layout);
        Bundle bundle=getArguments();
        StarInfoBean starInfoBean=(StarInfoBean)bundle.getSerializable("info");
        mData=starInfoBean.getStarinfo(); //获取关于星座信息的集合数据
        adapter=new StarBaseAdapter(getContext(),mData);
        mStarfragGv.setAdapter(adapter);

    }
}