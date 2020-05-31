package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;
import java.util.List;

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBean;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> listBeans;

    public SeckillRecyclerViewAdapter(Context mContext, ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBean) {
        this.mContext = mContext;
        this.seckillInfoBean = seckillInfoBean;
        this.listBeans = seckillInfoBean.getList();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_seckill, null));
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.setData(position);

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        private LinearLayout ll_root;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_figure=(ImageView)itemView.findViewById(R.id.iv_figure);
            tv_cover_price=(TextView)itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price=(TextView)itemView.findViewById(R.id.tv_cover_price);
            ll_root=(LinearLayout)itemView.findViewById(R.id.ll_root);

        }

        public void setData(int position) {
            //根据位置得到数据
            ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean=listBeans.get(position);
            tv_cover_price.setText("$"+listBean.getCover_price());
            tv_origin_price.setText("$"+listBean.getOrigin_price());
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(iv_figure);
        }
    }
}

