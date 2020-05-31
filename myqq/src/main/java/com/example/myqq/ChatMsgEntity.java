package com.example.myqq;

import android.text.TextUtils;

public class ChatMsgEntity {
    //信息对应的用户
    private String name;
    //信息的内容
    private String text;
    //信息的类型(接收-true 或发送-false)
    private boolean isComMeg=true;


    public ChatMsgEntity(){
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public boolean isComMeg() {
        return isComMeg;
    }
    public void setComMeg(boolean comMeg) {
        isComMeg = comMeg;
    }
}