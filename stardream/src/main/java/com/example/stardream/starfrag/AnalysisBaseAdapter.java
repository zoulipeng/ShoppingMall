package com.example.stardream.starfrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stardream.R;

import java.util.List;

public class AnalysisBaseAdapter extends BaseAdapter {
    Context context;
    List<StarAnalysisBean> mDatas;
    private LayoutInflater layoutInflater;

    public AnalysisBaseAdapter(Context context, List<StarAnalysisBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolders holder;
        if (convertView==null){
            convertView= layoutInflater.inflate(R.layout.item_star_analysis,null);
            holder=new ViewHolders();
            holder.itemstar_tv_title=convertView.findViewById(R.id.itemstar_tv_title);
            holder.itemstar_tv_content=convertView.findViewById(R.id.itemstar_tv_content);
            convertView.setTag(holder);
        } else{
            holder=(ViewHolders)convertView.getTag();
        }
        StarAnalysisBean bean=mDatas.get(position);
        holder.itemstar_tv_title.setText(bean.getTitle());
        holder.itemstar_tv_content.setText(bean.getContent());
        holder.itemstar_tv_content.setBackgroundResource(bean.getColor());
        return convertView;
    }
    private class ViewHolders {
        private TextView itemstar_tv_title;
        private TextView itemstar_tv_content;
        
    }
}
