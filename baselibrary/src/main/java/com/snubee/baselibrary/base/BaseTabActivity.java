package com.snubee.baselibrary.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.utilcode.utils.SizeUtils;
import com.snubee.baselibrary.R;
import com.snubee.baselibrary.utils.ALLog;
import com.snubee.baselibrary.widget.NestRadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部tab带标题的 ViewPager+Fragment通用类
 *
 * @author snubee
 * @time 2016/9/23 9:14
 */
public abstract class BaseTabActivity extends BaseActivity {

    private NestRadioGroup tabLayout;
    private List<Tab> tabs;

    protected ViewPager mPager;
    private TabFragmentPagerAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(R.layout.activity_main);
        initView();
        initTab();
        initViewPager();
    }

    private void initView() {

        mPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (NestRadioGroup) findViewById(R.id.rg_bottom_bar);
    }

    private void initViewPager() {
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());

        tabLayout.setOnCheckedChangeListener(new NestRadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(NestRadioGroup group, int checkedId) {
                ALLog.i("=====tabLayout=====" + checkedId);
                mPager.setCurrentItem(checkedId, false);
                setSelectStaus(checkedId);
                onTabSelected(checkedId);
            }
        });
        mPager.setOffscreenPageLimit(3);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                ALLog.i("=========viewpager:::position =======" + position);
            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /**
     * 设置tab状态
     *
     * @param selection
     */
    public void setSelectStaus(int selection) {
        for (int i = 0; i < tabs.size(); i++) {
            RadioButton button = (RadioButton) tabLayout.findViewById(i);
            button.setTextColor(tabs.get(i).defaultTextColor);
            button.setCompoundDrawablesWithIntrinsicBounds(0, tabs.get(i).defaultIcon, 0, 0);
        }
        Tab tab = tabs.get(selection);
        RadioButton selectedBtn = (RadioButton) tabLayout.findViewById(selection);
        selectedBtn.setCompoundDrawablesWithIntrinsicBounds(0, tab.selectIcon, 0, 0);
        selectedBtn.setTextColor(tab.selectedTextColor);
    }

    /**
     * 选择tab
     *
     * @param position
     */
    public void checkTab(int position) {
        tabLayout.check(position);
    }

    /**
     * 初始化底部tab
     */
    @SuppressWarnings("deprecation")
    private void initTab() {
        tabs = onSetTabs();
        if (tabs == null || (tabs != null && tabs.isEmpty())) return;

        for (int i = 0; tabs != null && i < tabs.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            Tab tab = tabs.get(i);

            radioButton.setId(i);
            radioButton.setText(tab.name);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.sp_12));
            radioButton.setCompoundDrawablesWithIntrinsicBounds(0, tab.defaultIcon, 0, 0);
            radioButton.setCompoundDrawablePadding(2);
            radioButton.setTextColor(tab.defaultTextColor);
            radioButton.setButtonDrawable(null);
            radioButton.setButtonDrawable(R.drawable.transparent);
            radioButton.setBackgroundResource(R.drawable.transparent);
            radioButton.setBackground(null);
            radioButton.setPadding(0, SizeUtils.dp2px(this, 3), 0, 0);


            NestRadioGroup.LayoutParams params = new NestRadioGroup.LayoutParams(
                    NestRadioGroup.LayoutParams.WRAP_CONTENT,
                    NestRadioGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            radioButton.setLayoutParams(params);
            radioButton.setGravity(android.view.Gravity.CENTER);
            tabLayout.addView(radioButton);

            if (tab.isDivide && i != tabs.size() - 1) {
                View divideView = new View(this);
                divideView.setBackgroundColor(tab.divideColor);
                NestRadioGroup.LayoutParams divideParams = new NestRadioGroup.LayoutParams(1,
                        NestRadioGroup.LayoutParams.MATCH_PARENT);
                tabLayout.addView(divideView, divideParams);
            }
            //添加fragments
            fragments.add(tab.fragment);
        }
        setSelectStaus(0);

    }


    /**
     * 设置Tab的回调函数
     *
     * @return tab列表数据
     */
    protected abstract List<Tab> onSetTabs();

    /**
     * tab切换回调
     *
     * @param position
     */
    protected abstract void onTabSelected(int position);


    public class Tab {
        public int selectIcon;//选中的图标
        public int defaultIcon;//默认图标
        public int defaultTextColor;//默认文字颜色
        public int selectedTextColor;//选中的文字颜色
        public Drawable bg;//Tab背景图片
        public Fragment fragment;//对应的fragment
        public String name;//tab文本
        public boolean isDivide = false; //是否有分割线
        public int divideColor;//分割线颜色

        public Tab(@DrawableRes int selectIcon, @DrawableRes int defaultIcon, @ColorInt int defaultTextColor, @ColorInt int selectedTextColor,
                   Fragment fragment, String name, boolean isDivide, @ColorInt int divideColor) {
            this.selectIcon = selectIcon;
            this.defaultIcon = defaultIcon;
            this.defaultTextColor = defaultTextColor;//默认没有选中的字体颜色
            this.selectedTextColor = selectedTextColor; //选中时候的颜色
            this.fragment = fragment;
            this.name = name;
            this.isDivide = isDivide;
            this.divideColor = divideColor;
        }

        public Tab(@DrawableRes int selectIcon, @DrawableRes int defaultIcon, @ColorInt int defaultTextColor, @ColorInt int selectedTextColor, Fragment fragment, String name) {
            this.selectIcon = selectIcon;
            this.defaultIcon = defaultIcon;
            this.defaultTextColor = defaultTextColor;
            this.selectedTextColor = selectedTextColor;
            this.fragment = fragment;
            this.name = name;
        }

    }

    /**
     * ViewPager 数据适配器
     */
    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);

        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }


}
