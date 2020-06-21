package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean>channelInfoBeans;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channelInfoBeans) {
        this.mContext = mContext;
        this.channelInfoBeans = channelInfoBeans;
    }

    @Override
    public int getCount() {
        return channelInfoBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return channelInfoBeans.get(position);//获取每条数据的位置
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewChannelHoler viewChannelHolder = new ViewChannelHoler();
        if (convertView==null){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.item_channel,null,false);
//            viewChannelHolder=new ViewChannelHoler();
         viewChannelHolder.iv_channel=convertView.findViewById(R.id.iv_channel);
         viewChannelHolder.tv_channel=convertView.findViewById(R.id.tv_channel);
         convertView.setTag(viewChannelHolder);
        }else{
            viewChannelHolder = (ViewChannelHoler) convertView.getTag();
        }
        /////////////////////不是很明白
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean=channelInfoBeans.get(position);
        viewChannelHolder.tv_channel.setText(channelInfoBean.getChannel_name());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).into(viewChannelHolder.iv_channel);

        return convertView;

    }

    private class ViewChannelHoler {
        ImageView iv_channel;
        TextView tv_channel;


    }
}
