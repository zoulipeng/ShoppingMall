package com.example.shoppingmall.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyApplication extends Application {
    //    onCreate()方法:
    //    在Application创建的时候调用,一般用于初始化一些东西,如全局的对象，环境的配置等。
//    public String address;


    @Override
    public void onCreate() {
        super.onCreate();
//        //初始化okhttpUtils
//        initOkhttpClient();
//    }
//--------------------------------------------------------------------------------------------

//        private void initOkhttpClient () {
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                    //其他配置
//                    .build();
//
//            OkHttpUtils.initClient(okHttpClient);
        }
    }

