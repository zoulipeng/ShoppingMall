package com.example.shoppingmall.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends BaseFragment {
    ////    private TextView textView;
    private TextView tv_search_home;//输入搜索信息
    private TextView tv_message_home;//消息
    private RecyclerView rb_home;
    private ImageButton ib_top;
    private static String TAG = HomeFragment.class.getSimpleName();//得到类的简写名称
    //返回的数据
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;


    @Override
    protected View initView() {
        Log.e("TAG", "主页面被初始化了");
//        textView=new TextView(mContext);
//        textView.setTextSize(25);
//        textView.setTextColor(Color.RED);
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        rb_home = (RecyclerView) view.findViewById(R.id.rb_homes);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        //设计点击事件
        initListener();
        return view;
    }

    //图标小按钮点击事件
    private void initListener() {
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb_home.scrollToPosition(0);
            }
        });
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();


        Log.e("TAG", "主页面数据被初始化了");
//        textView.setText("This is HomeFragment");
//----------------------------------------------------------------------------------------------------
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Constants.HOME_URL;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    Log.e(TAG,response.body().string());不能调用两次
                        String str = response.body().string();//得到正确的json数据
//response.body().string()调用了多次导致的[okhttp异常： java.lang.IllegalStateException: closed],string()仅可调用一次。
//                    processData(str);
                        processGsonData(str);
                        if (resultBean != null) {
                            //有数据
                            //设置适配器的数据

                            mContext.runOnUiThread(new
                                                           Runnable() {
                                                               @Override
                                                               public void run() {

                                                                   adapter = new HomeFragmentAdapter(mContext, resultBean);


                                                                   GridLayoutManager manager = new GridLayoutManager(mContext, 1);
                                                                   //设置布局管理者
                                                                   rb_home.setLayoutManager(manager);
                                                                   rb_home.setAdapter(adapter);
                                                               }
                                                           });


                        } else {
                            //没有数据

                        }

                    }
                });
            }
        }).start();
    }

    //------------------------------------------------------------------------------
    private void processGsonData(String str) {
        Gson gson = new Gson();
        ResultBeanData resultBeanData = gson.fromJson(str, ResultBeanData.class);
        resultBean=resultBeanData.getResult();
        String content = resultBeanData.getResult().getHot_info().get(0).getName();
        String content1 = resultBeanData.getResult().getChannel_info().get(0).getChannel_name();
        Log.e(TAG, content);
        Log.e(TAG, content1);


    }


}







