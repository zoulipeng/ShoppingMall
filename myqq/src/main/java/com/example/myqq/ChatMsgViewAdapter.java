package com.example.myqq;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

//import com.example.myqq.Entity.ChatMsgEntity;

public class ChatMsgViewAdapter extends BaseAdapter {
    public static interface IMsgViewType{
        //将信息显示状态初始化为发送的信息
        int IMVT_COM_MSG=0;
        int IMVT_TO_MSG=1;
    }
    private List<ChatMsgEntity> coll;
    private Context ctx;
    private LayoutInflater layoutInflater;
    public ChatMsgViewAdapter(List<ChatMsgEntity> coll, Context ctx) {
        this.coll = coll;
        this.ctx = ctx;
        layoutInflater=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return coll.size();
    }
    @Override
    public Object getItem(int i) {
        return coll.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getItemViewType(int position) {
        ChatMsgEntity entity=coll.get(position);
        if (entity.isComMeg()){
            return IMsgViewType.IMVT_COM_MSG;

        }else {
            return IMsgViewType.IMVT_TO_MSG;
        }
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChatMsgEntity entity=coll.get(i);
        boolean isComMsg=entity.isComMeg();
        ViewHolder viewHolder=null;
        if (view==null){
            //如果当前信息为接收的信息，则使用 chatting_item_msg_text_left 进行显示
            //否则使用 chatting_item_msg_text_right 进行显示
            if (isComMsg){
                view=layoutInflater.inflate(R.layout.chatting_item_msg_text_left,null);
            }else {
                view=layoutInflater.inflate(R.layout.chatting_item_msg_text_right,null);
            }
            viewHolder=new ViewHolder();
            viewHolder.tvUserName=(TextView)view.findViewById(R.id.tv_username);
            viewHolder.tvContent=(TextView)view.findViewById(R.id.tv_content);
            viewHolder.isComMsg=isComMsg;
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.tvUserName.setText(entity.getName());
        viewHolder.tvContent.setText(entity.getText());
        return view;
    }
    static class ViewHolder {
        public TextView tvUserName;
        public TextView tvContent;
        public boolean isComMsg=true;
    }
}
