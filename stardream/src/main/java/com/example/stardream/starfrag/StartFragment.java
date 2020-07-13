package com.example.stardream.starfrag;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.stardream.R;
import com.example.stardream.bean.StarInfoBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
public class StartFragment extends Fragment {
    private ViewPager mStarfragVp;
    private GridView mStarfragGv;
    private LinearLayout pointLayout;
    private List<StarInfoBean.StarinfoBean> mData;
    private StarBaseAdapter adapter;
    private int imageIds[]={R.drawable.pic_guanggao,R.drawable.james,R.drawable.butter,R.drawable.pj};
    private ArrayList<ImageView> ivList;
    private int pointIds[]={R.drawable.point_normal,R.drawable.point_focus};
    private ArrayList<ImageView> pointList;
    private StarPagerAdapter startPageradapter;
    //完成定时装置，实现自动滑动效果
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentItem=mStarfragVp.getCurrentItem();
            if (currentItem==ivList.size()-1){
                mStarfragVp.setCurrentItem(0);
            }else{
                currentItem++;
                mStarfragVp.setCurrentItem(currentItem);
            }
            handler.sendEmptyMessageDelayed(8,3000);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_star, container, false);
        initView(view);
        initPager();
        setVPListener();
        handler.sendEmptyMessageDelayed(8,3000);
        return view;
    }

    private void setVPListener() {
        mStarfragVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<pointList.size();i++){
                    pointList.get(i).setImageResource(R.mipmap.point_normal);
                }
                pointList.get(position).setImageResource(R.mipmap.point_focus);

                //////////////ViewPager设置监听点击事件////////////////////////////////////////
                switch (position){
                    case 0:
                        Toast.makeText(getContext(),"Hello1",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),"Hello2",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getContext(),"Hello3",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(),"Hello4",Toast.LENGTH_LONG).show();
                        break;

                }

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

          mStarfragVp.setPageTransformer(true,new DepthPageTransformer());
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
    private void initPager() {
        ivList=new ArrayList<>();
        pointList=new ArrayList<>();
        for (int i=0;i<imageIds.length;i++){
            ImageView iv=new ImageView(getContext());
            iv.setImageResource(imageIds[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //创建图片View的宽高
            LinearLayout.LayoutParams pl=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(pl);
            ivList.add(iv);
            //创建图片对应的指示器的小圆点
            ImageView piv=new ImageView(getContext());
            piv.setImageResource(R.mipmap.point_normal);
            LinearLayout.LayoutParams plp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置外边距
            plp.setMargins(20,0,0,0);
            piv.setLayoutParams(plp);
            pointLayout.addView(piv);
            //            为了便于操作，将小圆点添加到统一管理的集合中
            pointList.add(piv);
        }
        //默认第一个小圆点是获取焦点的状态
        pointList.get(0).setImageResource(R.mipmap.point_focus);
        startPageradapter = new StarPagerAdapter(getContext(), ivList);
        mStarfragVp.setAdapter(startPageradapter);

    }
}