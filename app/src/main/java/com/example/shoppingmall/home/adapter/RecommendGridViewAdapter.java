package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;

import java.util.List;

public class RecommendGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> datas;

    public RecommendGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas=recommend_info;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecommendHoler recommendHoler;
        if (convertView==null){
            convertView= View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            recommendHoler=new RecommendHoler();
            recommendHoler.iv_recommend=(ImageView)convertView.findViewById(R.id.iv_recommend);
            recommendHoler.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            recommendHoler.tv_price=(TextView)convertView.findViewById(R.id.tv_price);
            convertView.setTag(recommendHoler);

        }else{
            recommendHoler=(RecommendHoler)convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean=datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(recommendHoler.iv_recommend);
        recommendHoler.tv_name.setText(recommendInfoBean.getName());
        recommendHoler.tv_price.setText("$"+recommendInfoBean.getCover_price());
        return convertView;
    }

    private class RecommendHoler {
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
    }
}
