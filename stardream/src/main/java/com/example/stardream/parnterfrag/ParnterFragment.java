package com.example.stardream.parnterfrag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.stardream.R;
import com.example.stardream.bean.StarInfoBean;
import com.example.stardream.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

public class ParnterFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    private ImageView manIv;
    private Spinner manSp;
    private ImageView womenIv;
    private Spinner womenSp;
    private Button priceBtn;
    private Button matchBtn;
    private List<StarInfoBean.StarinfoBean> starinfoList;
//    private static String spList[] ={"白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座","水瓶座","双鱼座"};
    private ArrayAdapter<String> adapter;
    private Map<String, Bitmap> logoImgMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parnter, null);
        initView(view);
        initSpinner();
        return view;
    }

    private void initSpinner() {
        //定义一个存储十二星座的数组spList
        String []spList=new String[12];
        for (int i=0;i<starinfoList.size();i++){
            spList[i]=starinfoList.get(i).getName();
        }
        adapter=new ArrayAdapter<String>(getContext(),R.layout.item_parnter_sp,spList);
        //设置展开的时候下拉菜单的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        manSp.setAdapter(adapter);
        womenSp.setAdapter(adapter);
        String logoname=starinfoList.get(2).getLogoname();
        logoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap=logoImgMap.get(logoname);
        manIv.setImageBitmap(bitmap);
        womenIv.setImageBitmap(bitmap);
    }
    private void initView( View view) {
        manIv=view.findViewById(R.id.parnterfrag_iv_man);
        womenIv=view.findViewById(R.id.parnter_iv_women);
        manSp=view.findViewById(R.id.parnterfrag_sp_man);
        //设置manSp的监听事件
        manSp.setOnItemSelectedListener(this);
        womenSp=view.findViewById(R.id.parnterfrag_sp_women);
        //设置womenSp的监听事件
        womenSp.setOnItemSelectedListener(this);
        priceBtn=view.findViewById(R.id.parnterfrag_btn_price);
        matchBtn=view.findViewById(R.id.parnterfrag_bt_match);
        //设置按钮的监听事件
        priceBtn.setOnClickListener(this);
        matchBtn.setOnClickListener(this);
        Bundle bundle=getArguments();
        StarInfoBean bean= (StarInfoBean) bundle.getSerializable("info");
        starinfoList = bean.getStarinfo();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.parnterfrag_btn_price:
                break;
            case R.id.parnterfrag_bt_match:
                /**
                 * Return the position of the currently selected item within the adapter's data set
                 *
                 * @return int Position (starting at 0), or {@link #INVALID_POSITION} if there is nothing selected.
                 * */
                int mspItem=manSp.getSelectedItemPosition();
                int wspItem=womenSp.getSelectedItemPosition();
                Intent intent=new Intent(getContext(),ParnterAnalysisActivity.class);
                intent.putExtra("parnter_man",starinfoList.get(mspItem).getName());
                intent.putExtra("parnter_women",starinfoList.get(wspItem).getName());
                intent.putExtra("paenetr_man_logo",starinfoList.get(mspItem).getLogoname());
                intent.putExtra("parnter_women_logo",starinfoList.get(wspItem).getLogoname());
                startActivity(intent);

                break;
        }
        
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.parnterfrag_sp_man:
            String logoname=starinfoList.get(position).getLogoname();
            logoImgMap=AssetsUtils.getContentlogoImgMap();
            Bitmap bitmap1=logoImgMap.get(logoname);
            manIv.setImageBitmap(bitmap1);
                break;
            case R.id.parnterfrag_sp_women:
             String logonames=starinfoList.get(position).getLogoname();
             logoImgMap=AssetsUtils.getContentlogoImgMap();
             Bitmap bitmap2=logoImgMap.get(logonames);
             womenIv.setImageBitmap(bitmap2);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}