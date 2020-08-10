package com.example.stardream.parnterfrag;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stardream.R;
import com.example.stardream.utils.AssetsUtils;
import com.example.stardream.utils.LoadDataAsyncTask;
import com.example.stardream.utils.OkHttpUtils;
import com.example.stardream.utils.OnGetNetDataListener;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;

public class ParnterAnalysisActivity extends AppCompatActivity implements View.OnClickListener, OnGetNetDataListener {

    private ImageView mBack;
    private CircleImageView mCivMan;
    private CircleImageView mCivWomen;
    private TextView mTvMan;
    private TextView mTvWomen;
    private TextView mTvVs;
    private TextView mTvGrade;
    private TextView mTvDes;
    private TextView mList;
    public static String parnter_man;
    public static String parnter_women;
    private static String paenetr_man_logo;
    private  static String parnter_women_logo;
    private Map<String, Bitmap> contentlogoImgMap;
    private TextView mTvPd;
    //    private ParnterAnalysisBean.ResultBean resultBean;
    private ParnterAnalysisBean bean;
    private Bitmap Manbitmap;
    private Bitmap Womenbitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parter_analysis);
        initView();
        getLastData();
//        initLoadDataFromOkHttp();
        //执行AsyncTask的一系列操作
        LoadDataAsyncTask task=new LoadDataAsyncTask(this,this);
        task.execute();
    }
    @Override
    public void onSuccess(String s) {
        mCivMan.setImageBitmap(Manbitmap);
        mCivWomen.setImageBitmap(Womenbitmap);
        mTvMan.setText(parnter_man);
        mTvWomen.setText(parnter_women);
        mTvPd.setText("星座配对 "+parnter_man+" 和"+parnter_women+" 配对");
        mTvVs.setText(OkHttpUtils.result1);
        mTvGrade.setText("配对评分: "+OkHttpUtils.result2);
        mList.setText(OkHttpUtils.result3);
        mTvDes.setText(OkHttpUtils.result4);

    }

//    Handler handler=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            if (msg.what==0x11){
//                OkHttpUtils.getOkHttpData(parnter_man,parnter_women);
//                mTvVs.setText(OkHttpUtils.result1);
//            }
//        }
//    };

    //继承Handler+Thread类组合实现异步任务效果
//   Thread thread=new Thread(new Runnable() {
//        @Override
//        public void run() {
//                    handler.sendEmptyMessage(0x11);
//        }
//
//    }).start();



//   private void initLoadDataFromOkHttp(){
//
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                if (msg.what==0x11){
//                 OkHttpUtils.getOkHttpData(parnter_man,parnter_women);
//                 mTvVs.setText(OkHttpUtils.result1);
//                 mTvGrade.setText(OkHttpUtils.result2);
//                 mList.setText(OkHttpUtils.result3);
//                }
//            }
//        };
//       Thread thread=new Thread(new Runnable() {
//           @Override
//           public void run() {
//            handler.sendEmptyMessage(0x11);
//           }
//       });
//       thread.start();
//   }


    private void getLastData() {
        Intent intent = getIntent();
        parnter_man = intent.getStringExtra("parnter_man");
        parnter_women = intent.getStringExtra("parnter_women");
        paenetr_man_logo = intent.getStringExtra("paenetr_man_logo");
        parnter_women_logo = intent.getStringExtra("parnter_women_logo");
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();

        Manbitmap = contentlogoImgMap.get(paenetr_man_logo);
        Womenbitmap = contentlogoImgMap.get(parnter_women_logo);

    }

//    private void initLord() {
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                if (msg.what==0x11){
//                    OkHttpUtils.getOkHttpData(parnter_man,parnter_women);
//                mCivMan.setImageBitmap(Manbitmap);
//                mCivWomen.setImageBitmap(Womenbitmap);
//                mTvMan.setText(parnter_man);
//                mTvWomen.setText(parnter_women);
//                mTvPd.setText("星座配对 "+parnter_man+" 和"+parnter_women+" 配对");
////                mTvVs.setText(OkHttpUtils.result1);
//                mTvVs.setText(parnter_man+" vs "+parnter_women);
//               mTvGrade.setText(OkHttpUtils.result2);
//               mList.setText(OkHttpUtils.result3);
//                }
//            }
//        };
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//               handler.sendEmptyMessage(0x11);
//            }
//        });
//        thread.start();
//
//    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mCivMan = (CircleImageView) findViewById(R.id.civ_man);
        mCivWomen = (CircleImageView) findViewById(R.id.civ_women);
        mTvMan = (TextView) findViewById(R.id.tv_man);
        mTvWomen = (TextView) findViewById(R.id.tv_women);
        mTvVs = (TextView) findViewById(R.id.tv_vs);
        mTvGrade = (TextView) findViewById(R.id.tv_grade);
        mTvDes = (TextView) findViewById(R.id.tv_des);
        mList = (TextView) findViewById(R.id.list);
        mTvPd = (TextView) findViewById(R.id.tv_pd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();//返回上一个Activity
                break;
        }
    }
}