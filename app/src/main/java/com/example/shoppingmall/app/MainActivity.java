package com.example.shoppingmall.app;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.community.fragment.CommunityFragment;
import com.example.shoppingmall.home.fragment.HomeFragment;
import com.example.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.example.shoppingmall.type.fragment.TypeFragment;
import com.example.shoppingmall.user.fragment.UserFragment;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
/////////////////////Hello World&&Hello Android///////////////////////////////
public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
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
    //装多个Fragment的实例集合
    public ArrayList<BaseFragment> fragments;
    private int position;
    //缓存的Fragment或者上次显示的Fragment
    private Fragment tempFragment;
    private BaseFragment mContext;
    private  Fragment  currentFragment=new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        //初始化Fragment
        fragments = new ArrayList<>();
        initFragment();
        //设置RadioGroup的监听
        initListener();//!!

    }

    private void initListener() {
        //默认设置首页
        rgMain.check(R.id.rb_home);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_type://分类
                        position = 1;
                        break;
                    case R.id.rb_community://发现
                        position = 2;
                        break;
                    case R.id.rb_cart://购物车
                        position = 3;
                        break;
                    case R.id.rb_user://用户中心
                        position = 4;
                        break;
                    default:
                        break;
                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);
//                switchFragment(tempFragment,baseFragment);
//                switchFragment( baseFragment);
                switchFragment(baseFragment);

            }
        });

    }

    //添加的时候要按照顺序
    private void initFragment() {
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());


    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }

        return null;
    }

    //    //    //切换Fragment
////    //formFragment,nextFragment
//    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
//        if (tempFragment != nextFragment) {
//            tempFragment = nextFragment;
//            if (nextFragment != null) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                //判断nextFragment是否添加
//                if (!nextFragment.isAdded()) {
//                    //隐藏当前Fragment
//                    if (fromFragment != null) {
//                        transaction.hide(fromFragment);
//                    }
//                    transaction.add(R.id.frameLayout, nextFragment).commit();
//                } else {
//                    //隐藏当前Fragment
//                    if (fromFragment != null) {
//                        transaction.hide(fromFragment);
//                    }
//                    transaction.show(nextFragment).commit();
//                }
//            }
//        }
//    //切换Fragment
//    private void switchFragment(BaseFragment baseFragment){
//        //1.得到FragmentManager
//        FragmentManager manager=getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction=manager.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.frameLayout,baseFragment);
//        //4.提交事务
//        transaction.commit();
//    }
    private void switchFragment(BaseFragment baseFragment) {
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






