package com.example.myqq;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtAccount;
    private EditText mEdtPwd;
    private Button mBtnRegister;
    private Button mBtnLogin;
    private ProgressDialog progressDialog;
    private String message;
    private int success;
    //http://192.168.1.113:808/myqq/register.php-----http://192.168.1.113:808/myqq/
    //http://49.235.158.162/myqq/register.php
    //http://49.235.158.162/myqq/register2.php?user_name=test&user_pwd=test
    //http://49.235.158.162/myqq/login2.php?user_name=test&user_pwd=test
    public static String user_name;
    public static String BaseURL="http://49.235.158.162/myqq/";
    public static String url_register=BaseURL+"register.php";
    public static String url_login=BaseURL+"login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        mEdtAccount = (EditText) findViewById(R.id.edt_account);
        mEdtPwd = (EditText) findViewById(R.id.edt_pwd);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        if (mEdtAccount.getText().toString().trim().equals("")||mEdtPwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"请输入账号或密码",Toast.LENGTH_LONG).show();

        }else {
            user_name=mEdtAccount.getText().toString();
            RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
            Map<String,String> map=new HashMap<>();
            /*
            * map技巧
            * */
            map.put("user_name",mEdtAccount.getText().toString());
            map.put("user_pwd",mEdtPwd.getText().toString());
            //在进行异步任务前先显示一个 Progress Dialog
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("正在登录...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
            Request request=new JsonObjectRequest(1, url_login, new Gson().toJson(map), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        success = jsonObject.getInt("success");
                        message = jsonObject.optString("message");
                        if (success == 1) {
                            progressDialog.dismiss();
                            String str = "" + success;
                            Toast toast = Toast.makeText(getApplicationContext(), "返回码=" + str + ":" + message, Toast.LENGTH_LONG);
                            toast.show();
                            Intent intent = new Intent(MainActivity.this, QqMainActivity.class);
                            startActivity(intent);
                        } else if (success == 0) {
                            progressDialog.dismiss();
                            String str = "" + success;
                            Toast toast = Toast.makeText(getApplicationContext(), "返回码=" + str + ":" + message, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("error", "onErrorResponse: "+volleyError.toString());
                }
            });
            requestQueue.add(request);
        }
    }

    private void register() {
        if (mEdtAccount.getText().toString().trim().equals("")||mEdtPwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"请输入账号或密码",Toast.LENGTH_LONG).show();
        }else {
            //定义一个队列
            RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
            //发送请求
            Map<String,String> map=new HashMap<>();
            map.put("user_name",mEdtAccount.getText().toString());
            map.put("user_pwd",mEdtPwd.getText().toString());
            //在进行异步任务前先显示一个 Progress Dialog
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("正在注册...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
            //定义请求
            Request request=new JsonObjectRequest(1, url_register, new Gson().toJson(map), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        success = jsonObject.getInt("success");
                        message = jsonObject.optString("message");
                        progressDialog.dismiss();
                        String str = "" + success;
                        Toast toast = Toast.makeText(getApplicationContext(), "返回码=" + str + ":" + message, Toast.LENGTH_LONG);
                        toast.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("error", "onErrorResponse: "+volleyError.toString());

                }
            });

            //将请求放入队列
            requestQueue.add(request);
        }
    }
}
