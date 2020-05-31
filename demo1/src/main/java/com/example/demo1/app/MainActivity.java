package com.example.demo1.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.demo1.R;
import com.example.demo1.base.BaseFragment;
import com.example.demo1.community.fragment.CommunityFragment;
import com.example.demo1.home.fragment.HomeFragment;
import com.example.demo1.shoppingcart.fragment.ShoppingcartFragment;
import com.example.demo1.type.fragment.TypeFragment;
import com.example.demo1.user.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_type)
    RadioButton rbType;
    @BindView(R.id.rb_community)
    RadioButton rbCommunity;
    @BindView(R.id.rb_cart)
    RadioButton rbCart;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private Context mContext;
    //添加继承了BaseFragment的各个Fragment;
    private ArrayList<BaseFragment> fragments;
    int position;
    //缓存的Fragment或者上次显示的Fragment
    private Fragment tempFragment;
    private Fragment currentFragment=new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rgMain= (RadioGroup) findViewById(R.id.rg_main);
        //初始化添加Fragment;
        fragments=new ArrayList<>();
        initFragment();
        initListener();
    }

    private void initListener() {
        //默认设置首页
        rgMain.check(R.id.rb_home);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;

                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment=getFragment(position);
//                switchFragment(tempFragment,baseFragment);
                switchFragment(baseFragment);
            }
        });
    }

    private void initFragment() {

        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingcartFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

//    private void switchFragment(Fragment formFragemnt, BaseFragment nextFragment) {
//     if (tempFragment!=nextFragment){
//         tempFragment=nextFragment;
//         if (nextFragment!=null){
//             //开启事务
//             FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//         if (!nextFragment.isAdded()){
//             if (formFragemnt!=null){
//                 transaction.hide(formFragemnt);
//             }
//             transaction.add(R.id.frameLayout,nextFragment).commit();
//         }else{
//             if (nextFragment!=null){
//                 transaction.hide(formFragemnt);
//             }
//             transaction.show(nextFragment).commit();
//         }
//         }
//
   private void switchFragment(BaseFragment baseFragment){
       //1.得到FragmentManager
       FragmentManager manager = getSupportFragmentManager();
       //2.开启事务
       FragmentTransaction transaction = manager.beginTransaction();
       if (!baseFragment.isAdded()){
           if (currentFragment!=null){
               transaction.hide(currentFragment);
               transaction.add(R.id.frameLayout,baseFragment);
           }else{
               transaction.hide(currentFragment).show(baseFragment);
           }

       }else{
           transaction.hide(currentFragment).show(baseFragment);
       }
       currentFragment=baseFragment;
       transaction.commit();//提交事务
   }
    }


