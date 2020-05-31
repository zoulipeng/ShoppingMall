package com.example.myqq;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class QqMainActivity extends AppCompatActivity {
    private ViewPager mActQqmainViewpager;
    private ImageView mQqmainImage1;
    private ImageView mQqmainImage2;
    private ImageView mQqmainImage3;
    private ImageView mQqmainImage4;
    private LinearLayout mActQqmainLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_main);
        initView();
    }

    private void initView() {
        mActQqmainViewpager = (ViewPager) findViewById(R.id.act_qqmain_viewpager);
        mQqmainImage1 = (ImageView) findViewById(R.id.qqmain_image1);
        mQqmainImage2 = (ImageView) findViewById(R.id.qqmain_image2);
        mQqmainImage3 = (ImageView) findViewById(R.id.qqmain_image3);
        mQqmainImage4 = (ImageView) findViewById(R.id.qqmain_image4);
        mActQqmainLinearLayout = (LinearLayout) findViewById(R.id.act_qqmain_LinearLayout);
        mQqmainImage1.setOnClickListener(new MyOnClickListener(0));
        mQqmainImage2.setOnClickListener(new MyOnClickListener(1));
        mQqmainImage3.setOnClickListener(new MyOnClickListener(2));
        mQqmainImage4.setOnClickListener(new MyOnClickListener(3));
        LayoutInflater layoutInflater=LayoutInflater.from(this);

        View v1=layoutInflater.inflate(R.layout.main_tab_chat,null);
        View v2=layoutInflater.inflate(R.layout.main_tab_conatct,null);
        View v3=layoutInflater.inflate(R.layout.main_tab_setting,null);
        View v4=layoutInflater.inflate(R.layout.main_tab_zone,null);
        final ArrayList<View> views=new ArrayList<>();
        views.add(v1);
        views.add(v2);
        views.add(v3);
        views.add(v4);
        //新建 ViewPager 的数据适配器，完成用户滑动
        PagerAdapter mPagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };
        mActQqmainViewpager.setAdapter(mPagerAdapter);
    }
    //实现 imageView 的单击响应功能
    public class MyOnClickListener implements View.OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        @Override
        public void onClick(View view) {
            mActQqmainViewpager.setCurrentItem(index);
        }
    }
    //完成 main_tab_chat 子界面中对应事件对应的方法
    public void startchat(View v){
        Intent intent=new Intent(QqMainActivity.this,ChatActivity.class);
        startActivity(intent);
    }
    //完成user1子界面中对应事件对应的方法
    public void startchat_user1(View v){
        Intent intent2=new Intent(QqMainActivity.this,ChatUser1.class);
        startActivity(intent2);
    }
}