package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.app.MainActivity;
import com.example.shoppingmall.home.bean.ResultBeanData;
import com.example.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.LogRecord;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 当前数据类型
     */
    public int currentType = 0;
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;
    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖类型
     */
    public static final int HOT = 5;
    private final Context mContext;
    /**
     * 初始化布局
     */
    private final LayoutInflater mLayoutInflater;
    private final ResultBeanData.ResultBean resultBean;


    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView 创建ViewHolder部分
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if  (viewType==ACT){
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));

        }else if (viewType==SECKILL){
            return new SeckillHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }
        else if (viewType==RECOMMEND){
            return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
        }

        return null;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);

        }


        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据
//            List<String> imagesUrl = new ArrayList<>();
//            for (int i = 0; i < banner_info.size(); i++) {
//                String imageUrl = banner_info.get(i).getImage();
//                imagesUrl.add(imageUrl);
//            }
//-------------------------参考代码---------------------------------------------------------------
            try {
                List<String> imageUris = new ArrayList<>();
                for (int i = 0; i < resultBean.getBanner_info().size(); i++) {
                    imageUris.add(resultBean.getBanner_info().get(i).getImage());
                }
                banner.setImages(imageUris, new OnLoadImageListener() {
                    @Override
                    public void OnLoadImage(ImageView view, Object url) {
                        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);
                    }
                });
            } catch (Exception e) {

            }
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "进入了" + position, Toast.LENGTH_LONG).show();
                }
            });


        }
    }

    /**
     * 相当于getView中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());//获得广告轮播的数据
        }else if (getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder=(ChannelViewHolder)holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if (getItemViewType(position)==ACT){
            ActViewHolder actViewHolder=(ActViewHolder)holder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if (getItemViewType(position)==SECKILL){
            SeckillHolder seckillHolder=(SeckillHolder)holder;
            seckillHolder.setData(resultBean.getSeckill_info());
        }else if (getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder=(RecommendViewHolder)holder;
            recommendViewHolder.setData((List<ResultBeanData.ResultBean.RecommendInfoBean>) resultBean.getRecommend_info());
        }
    }

    /**
     * 得到的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;

        }
        return currentType;
    }


    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 5;
    }


    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gridView;


        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            this.gridView=(GridView)itemView.findViewById(R.id.gv_channel);

        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            gridView.setAdapter(new ChannelAdapter(mContext,channel_info));
            //网格布局的点击事件
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager viewPager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            this.viewPager=(ViewPager)itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            // 设置每个页面的间距
            viewPager.setPageMargin(20); //>=3
            viewPager.setOffscreenPageLimit(3); // 设置动画
            viewPager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));


            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view==object;
                }

                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    ImageView iv=new ImageView(mContext);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+act_info.get(position).getIcon_url()).into(iv);
                    container.addView(iv);
                    return iv;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
              viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                  @Override
                  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                  }

                  @Override
                  public void onPageSelected(int position) {
                 Toast.makeText(mContext,"position=="+position,Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onPageScrollStateChanged(int state) {

                  }
              });
        }
    }
//-------------------------------------------------------------------------------------------------------------------

    private class SeckillHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;
       private long da=0;
//        //开启Handler机制实现秒杀倒计时的功能
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                    da=da-1000;//让每次减少1000毫秒
                    SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
                    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                    String time=dateFormat.format(new Date(da));
                    tv_time_seckill.setText(time);
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0,1000);
                    if (da<=0){
                        //把消息移除
                        handler.removeCallbacksAndMessages(null);
                    }

                }

////

        };

        public SeckillHolder(Context mContext, View itemView) {
            super(itemView);
        this.mContext=mContext;
        tv_time_seckill=(TextView)itemView.findViewById(R.id.tv_time_seckill);
        tv_more_seckill=(TextView)itemView.findViewById(R.id.tv_more_seckill);
        rv_seckill=(RecyclerView)itemView.findViewById(R.id.rv_seckill);

        }


        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBean) {
            //设置RecyclerView
            adapter=new SeckillRecyclerViewAdapter(mContext, seckillInfoBean);
            rv_seckill.setAdapter(adapter);
            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //秒杀倒计时
//            String转换成int的方法:int i = Integer.valueOf(string);
            da=Integer.valueOf(seckillInfoBean.getEnd_time())-Integer.valueOf(seckillInfoBean.getStart_time());
           SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            String time=dateFormat.format(new Date(da));
            tv_time_seckill.setText(time);
             handler.sendEmptyMessageDelayed(0x11,1000);


        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private GridView gv_recommend;
        private TextView tv_more_recommend;
        private Context mContext;
        private RecommendGridViewAdapter recommendGridViewAdapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            gv_recommend=(GridView)itemView.findViewById(R.id.gv_recommend);
            tv_more_recommend=(TextView)itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext,"position=="+position,Toast.LENGTH_LONG).show();
                }
            });
        }
        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
            recommendGridViewAdapter=new RecommendGridViewAdapter(mContext,  recommend_info);
            gv_recommend.setAdapter(recommendGridViewAdapter);
        }
    }

}
