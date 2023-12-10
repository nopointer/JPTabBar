package com.jpeng.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jpeng.jptabbar.BadgeDismissListener;
import com.jpeng.jptabbar.JPTabBar;
import com.jpeng.jptabbar.JPTabItem;
import com.jpeng.jptabbar.OnTabSelectListener;
import com.jpeng.jptabbar.anno.NorIcons;
import com.jpeng.jptabbar.anno.SeleIcons;
import com.jpeng.jptabbar.anno.Titles;

import java.util.ArrayList;
import java.util.List;

import static com.jpeng.demo.R.id.tabbar;


public class MainActivity extends AppCompatActivity implements BadgeDismissListener, OnTabSelectListener, View.OnClickListener {

    @Titles
    private List<Fragment> list = new ArrayList<>();

    private ViewPager mPager;

    private JPTabBar mTabbar;

    private Tab1Pager mTab1;

    private Tab2Pager mTab2;

    private Tab3Pager mTab3;

    private Tab4Pager mTab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabbar = (JPTabBar) findViewById(tabbar);
        mPager = (ViewPager) findViewById(R.id.view_pager);
//        mTabbar.setTitles("asd","页面二","页面三","页面四").setNormalIcons(R.mipmap.tab1_normal,R.mipmap.tab2_normal,R.mipmap.tab3_normal,R.mipmap.tab4_normal)
//                .setSelectedIcons(R.mipmap.tab1_selected,R.mipmap.tab2_selected,R.mipmap.tab3_selected,R.mipmap.tab4_selected).generate();
//        mTabbar.setTabTypeFace("fonts/Jaden.ttf");
        mTab1 = new Tab1Pager();
        mTab2 = new Tab2Pager();
        mTab3 = new Tab3Pager();
        mTab4 = new Tab4Pager();
        mTabbar.setGradientEnable(true);
        mTabbar.setPageAnimateEnable(true);
        mTabbar.setTabListener(this);
        list.add(mTab1);
        list.add(mTab2);
        list.add(mTab3);
        list.add(mTab4);

        mPager.setAdapter(new Adapter(getSupportFragmentManager(), list));
//        mTabbar.setContainer(mPager);
        //设置Badge消失的代理
        mTabbar.setDismissListener(this);
        mTabbar.setTabListener(this);
        if (mTabbar.getMiddleView() != null)
            mTabbar.getMiddleView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "中间点击", Toast.LENGTH_SHORT).show();
                    ;
                }
            });

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

//        style1();
    }

    @Override
    public void onDismiss(int position) {
        mTab1.clearCount();
    }


    @Override
    public void onTabSelect(int index) {
        Toast.makeText(MainActivity.this, "choose the tab index is " + index, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onInterruptSelect(int index) {
//        if(index==2){
//            //如果这里有需要阻止Tab被选中的话,可以return true
//            return true;
//        }
        return false;
    }

    public JPTabBar getTabbar() {
        return mTabbar;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                style1();
                break;
            case R.id.btn2:
                style2();
                break;
            case R.id.btn3:
                style3();
                break;
        }
    }

    private void style1() {
        log("ECG图标");
//        mTabbar.resetClear();
//        invoke(true);
        mTabbar.setNormalIcons(R.mipmap.nav_ecg_health_unselect, R.mipmap.nav_device_ecg_unselect, R.mipmap.nav_ecg_mine_unselect);
        mTabbar.setSelectedIcons(R.mipmap.nav_ecg_health_select, R.mipmap.nav_device_ecg_select, R.mipmap.nav_ecg_mine_select);
        mTabbar.setTitles(R.string.EMPTY, R.string.EMPTY, R.string.EMPTY);
//        mTabbar.generate();

//        mTabbar.setTabMargin(10);

        mTabbar.forceInvalidate();

        mTabbar.setTabMargin(12);
        mTabbar.setIconSize(10);

        mTabbar.setTabMargin(6, 0);
        mTabbar.setIconSize(25, 0);

        mTabbar.setSelectTab(0,false);
        mTabbar.setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int index) {

                Log.e("Tag", "index = " + index);
                mTabbar.forceInvalidate();

                mTabbar.setTabMargin(12);
                mTabbar.setIconSize(10);

                mTabbar.setTabMargin(6, index);
                mTabbar.setIconSize(25, index);

                mTabbar.setSelectTab(index,false);
//                mTabbar.setSelectTab(index);
            }

            @Override
            public boolean onInterruptSelect(int index) {
                return false;
            }
        });


//        for (JPTabItem jpTabItem : mTabbar.getJPTabItems()) {
//            jpTabItem.setPadding(20, 20, 20, 20);
//        }
//        mTabbar.getSelectedTab().setPadding(0, 0, 0, 0);
    }

    private void style2() {
        log("戒指图标");
        mTabbar.resetClear();
//        invoke(true);
        mTabbar.setNormalIcons(R.mipmap.nav_health_unselect, R.mipmap.nav_sleep_unselect, R.mipmap.nav_device_ring_unselect, R.mipmap.nav_mine_unselect);
        mTabbar.setSelectedIcons(R.mipmap.nav_health_select, R.mipmap.nav_sleep_select, R.mipmap.nav_device_ring_select, R.mipmap.nav_mine_select);
        mTabbar.setTitles(R.string.EMPTY, R.string.EMPTY, R.string.EMPTY, R.string.EMPTY);
        mTabbar.generate();
    }

    private void style3() {
        log("手表图标");
        mTabbar.resetClear();
//        invoke(true);
        mTabbar.setNormalIcons(R.mipmap.nav_health_unselect, R.mipmap.nav_sport_unselect, R.mipmap.nav_device_unselect, R.mipmap.nav_mine_unselect);
        mTabbar.setSelectedIcons(R.mipmap.nav_health_select, R.mipmap.nav_sport_select, R.mipmap.nav_device_select, R.mipmap.nav_mine_select);
        mTabbar.setTitles(R.string.EMPTY, R.string.EMPTY, R.string.EMPTY, R.string.EMPTY);
        mTabbar.generate();
    }

    public static void log(String msg) {
        Log.e("TAG", msg);
    }


}
