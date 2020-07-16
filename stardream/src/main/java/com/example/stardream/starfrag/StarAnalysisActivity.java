package com.example.stardream.starfrag;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stardream.R;
import com.example.stardream.bean.StarInfoBean;
import com.example.stardream.utils.AssetsUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mTitleIvBack;
    private TextView mTitleTv;
    private CircleImageView mStartanalysisIv;
    private TextView mStaranalysisTvName;
    private TextView mStaranalysisTvDate;
    private ListView mStaranalysisLv;
    StarInfoBean.StarinfoBean bean;
    private Map<String, Bitmap> contentlogoImgMap;
    private TextView footerstar_tv_info;
    List<StarAnalysisBean> mDatas;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        initView();
        initLoadData();
        initFooterView();
        addDataToList();
    }

    private void addDataToList() {
        mDatas=new ArrayList<>();//初始化显示在ListView上的数据源
        StarAnalysisBean st1=new StarAnalysisBean("性格特点",bean.getName(),R.color.colorPrimary);
        StarAnalysisBean st2=new StarAnalysisBean("掌管宫位",bean.getGw(),R.color.darkblue);
        StarAnalysisBean st3=new StarAnalysisBean("显阴阳性",bean.getYy(),R.color.bgyellow);
        StarAnalysisBean st4=new StarAnalysisBean("最大特征",bean.getTz(),R.color.darkgreen);
        StarAnalysisBean st5=new StarAnalysisBean("主管星球",bean.getZg(),R.color.lightgreen);
        StarAnalysisBean st6=new StarAnalysisBean("幸运颜色",bean.getYs(),R.color.white);
        StarAnalysisBean st7=new StarAnalysisBean("搭配珠宝",bean.getZb(),R.color.darkblue);
        StarAnalysisBean st8=new StarAnalysisBean("幸运号码",bean.getHm(),R.color.lightpink);
        StarAnalysisBean st9=new StarAnalysisBean("相属金属",bean.getJs(),R.color.orange);
        AnalysisBaseAdapter analysisBaseAdapter=new AnalysisBaseAdapter(this,mDatas);
        mDatas.add(st1);
        mDatas.add(st2);
        mDatas.add(st3);
        mDatas.add(st4);
        mDatas.add(st5);
        mDatas.add(st6);
        mDatas.add(st7);
        mDatas.add(st8);
        mDatas.add(st9);

        mStaranalysisLv.setAdapter(analysisBaseAdapter);
        //数据源发生变化,提示适配器更新
        analysisBaseAdapter.notifyDataSetChanged();

    }

    private void initFooterView() {
         View Footerview=LayoutInflater.from(this).inflate(R.layout.footer_star_analysis,null);
         mStaranalysisLv.addFooterView(Footerview);
         footerstar_tv_info=Footerview.findViewById(R.id.footerstar_tv_info);
         footerstar_tv_info.setText(bean.getInfo());


    }
    //addFooterView(添加尾布局)
    //addHeaderView(添加头布局)


    private void initView() {
        mTitleIvBack = (ImageView) findViewById(R.id.title_iv_back);
        mTitleIvBack.setOnClickListener(this);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mStartanalysisIv = (CircleImageView) findViewById(R.id.startanalysis_iv);
        mStaranalysisTvName = (TextView) findViewById(R.id.staranalysis_tv_name);
        mStaranalysisTvDate = (TextView) findViewById(R.id.staranalysis_tv_date);
        mStaranalysisLv = (ListView) findViewById(R.id.staranalysis_lv);
        //接受intent.putExtra("star",bean)[接受传递过来的信息]
        Intent intent=getIntent();//接受传递过来的信息
        bean= (StarInfoBean.StarinfoBean) intent.getSerializableExtra("star");

    }
    private void initLoadData() {
        mStaranalysisTvName.setText(bean.getName());
        mStaranalysisTvDate.setText(bean.getDate());
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap=contentlogoImgMap.get(bean.getLogoname());
        mStartanalysisIv.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        finish();//返回上一界面的方法,Android可以使用finish()方法，实现函数返回的功能
    }
}