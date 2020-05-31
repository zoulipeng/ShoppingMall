package com.example.myqq;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatUser1 extends AppCompatActivity implements View.OnClickListener {
    private Button mChatBack;
    private ImageButton mShezhi;
    private ListView mListView1;
    private Button mChatSend;
    private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
    private String message;//服务器返回值
    private int success;//服务器返回值
    private String far_user_name;//服务器返回值
    private String tempMessage = "";//保留上一条信息
    private String send_content;//发送的信息
    //发送信息地址
    private static String url_sendmsg = MainActivity.BaseURL + "sendmsg.php";
    //接收信息地址
    private static String url_getmsg = MainActivity.BaseURL + "getmsg.php";
    private ChatMsgViewAdapter chatMsgViewAdapter;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        //启动活动时不弹出软键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        initView();
        initData();
        //声明一个线程，它将无限循环
        Thread thread = new Thread(myRun);
        //开始异步执行
        thread.start();
    }
    //聊天视图初始化
    private String[] msgArray = new String[]{"小白弱弱问一句，学习什么比较有前途呢？","学习如何赢得富婆芳心啊","肤浅，学习安卓最有前途啊","瞎逼逼，还能有比学习如何在月球种辣椒更有前途吗？"};
    private void initData() {
        ChatMsgEntity entity = new ChatMsgEntity();
        ChatMsgEntity entity2 = new ChatMsgEntity();
        ChatMsgEntity entity3 = new ChatMsgEntity();
        ChatMsgEntity entity4 = new ChatMsgEntity();
        entity.setName("张三");
        entity.setComMeg(true);
        entity.setText(msgArray[0]);
        entity2.setName("李四");
        entity2.setComMeg(true);
        entity2.setText(msgArray[1]);
        entity3.setName("大神");
        entity3.setComMeg(true);
        entity3.setText(msgArray[2]);
        entity4.setName("勿六");
        entity4.setComMeg(false);
        entity4.setText(msgArray[3]);
        mDataArrays.add(entity);
        mDataArrays.add(entity2);
        mDataArrays.add(entity3);
        mDataArrays.add(entity4);
        chatMsgViewAdapter = new ChatMsgViewAdapter(mDataArrays,this);
        mListView1.setAdapter(chatMsgViewAdapter);
    }
    private void initView() {
        mChatBack = (Button) findViewById(R.id.chat_back);
        mShezhi = (ImageButton) findViewById(R.id.shezhi);
        mListView1 = (ListView) findViewById(R.id.listView1);
        mChatSend = (Button) findViewById(R.id.chat_send);
        mChatBack.setOnClickListener(this);
        mShezhi.setOnClickListener(this);
        mChatSend.setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_back:
                finish();//返回上一页
                break;
            case R.id.shezhi:
                break;
            case R.id.chat_send:
                sendMsg();//发送信息
                break;
        }
    }
    private void sendMsg() {
        String contStrng=mEditText.getText().toString();
        if (contStrng.length()>0){
            send_content=contStrng;
            ChatMsgEntity entity=new ChatMsgEntity();
            entity.setName(MainActivity.user_name);
            entity.setComMeg(false);
            entity.setText(contStrng);
            //本地显示
            mDataArrays.add(entity);
            chatMsgViewAdapter.notifyDataSetChanged();
            mEditText.setText("");
            mListView1.setSelection(mListView1.getCount()-1);
            //发送到服务器
            sendToYun();
        }
    }
    private void sendToYun() {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        Map<String,String> map=new HashMap<>();
        map.put("send_content",send_content);
        map.put("user_name",MainActivity.user_name);
        Request request=new JsonObjectRequest(1, url_sendmsg, new Gson().toJson(map), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    message = jsonObject.getString("message");
                    success = Integer.parseInt(jsonObject.optString("success"));
                    String str = "" + success;
                    Toast.makeText(ChatUser1.this, "返回码=" + str + ":", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(request);
    }
    //一个异步线程
    Runnable myRun=new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    //放慢速度
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //查询数据库
                getMsg();
                try {
                    //放慢速度
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private void getMsg() {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        Map<String,String> map=new HashMap<>();
        map.put("user_name",MainActivity.user_name);
        Request request=new JsonObjectRequest(1, url_getmsg, new Gson().toJson(map),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    message = jsonObject.optString("message");
                    success = jsonObject.getInt("success");
                    far_user_name = jsonObject.optString("user_name");
                    String str = "" + success;
                    Toast.makeText(ChatUser1.this, " 返 回 码 =" + str + ":", Toast.LENGTH_SHORT).show();
                    if (success == 1 && tempMessage.equals(message) == false) {
                        tempMessage = message;
                        String conString = message;
                        if (conString.length() > 0) {
                            ChatMsgEntity entity = new ChatMsgEntity();
                            entity.setName(far_user_name);
                            entity.setComMeg(true);
                            entity.setText(conString);
                            //本地显示
                            mDataArrays.add(entity);
                            chatMsgViewAdapter.notifyDataSetChanged();
                            mEditText.setText("");
                            mListView1.setSelection(mListView1.getCount() - 1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        requestQueue.add(request);
    }

}