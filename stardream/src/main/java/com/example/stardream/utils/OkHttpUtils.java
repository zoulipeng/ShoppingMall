package com.example.stardream.utils;
import android.util.Log;
import com.example.stardream.parnterfrag.ParnterAnalysisBean;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class OkHttpUtils {
      public static String json="";
    private static ParnterAnalysisBean.ResultBean resultBean;
       public static String result1;
      public static String result2;
      public  static String result3;
    public static String result4;


    public static String getOkHttpData(String man, String women){
      final String TAG="TAG";
      man=man.replace("座","");
      women=women.replace("座","");
      String url="http://apis.juhe.cn/xzpd/query?key=7267c4a8433bf3dcbeed834b57d487b1"+"&men="+man+"&women="+women;
//       String url="https://new.qq.com/rain/a/20200804A0CVG600";
       OkHttpClient client=new OkHttpClient();
       Request request=new Request.Builder().url(url).build();
       Call call=client.newCall(request);
       call.enqueue(new Callback() {
           @Override
           public void onFailure(@NotNull Call call, @NotNull IOException e) {
             Log.d(TAG,"errprrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
           }

           @Override
           public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                 if (response.isSuccessful()){
                 json=response.body().string();
                 Log.d(TAG,json);
                 processGsonData(json);

                     
                 }
           }
       });
        return json;    //返回成功响应回来的数据
    }

    public static void processGsonData(String json) {
        Gson gson=new Gson();
        ParnterAnalysisBean bean=gson.fromJson(json,ParnterAnalysisBean.class);
        resultBean = bean.getResult();
         result1=resultBean.getMen()+"vs"+resultBean.getWomen();
         result2 = resultBean.getZhishu() + resultBean.getJieguo();
        result3 = resultBean.getLianai();
        result4 = resultBean.getLianai();
        Log.d("TAGGGGGGGGGGGGGGGGGGGGG",result1);
    }
}