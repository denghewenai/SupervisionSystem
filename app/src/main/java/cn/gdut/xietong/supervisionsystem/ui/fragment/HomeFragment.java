package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.adapter.MyFragmentPagerAdapter;

/**
 * Created by 邓贺文 on 2016/1/19.
 * 主页Fragment
 */
public class HomeFragment extends BaseFragment{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mPagerAdapter;

    private List<Fragment> list_fragment;
    private List<String> list_title;

    private GeneralFragment mGeneralFragment;
    private GeneralFragment mSubscribeFragment;
    private RecorderFragment mRecorderFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View mContentView) {

        mTabLayout = (TabLayout) findViewById(R.id.id_tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewPager);

        //设置TabLayout的模式为可滑动
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        initData();

        mPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);

        //为ViewPager设置适配器
        mViewPager.setAdapter(mPagerAdapter);

        //TabLayout加载ViewPager
        mTabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        mGeneralFragment = new GeneralFragment();
        mSubscribeFragment = new GeneralFragment();
        mRecorderFragment = new RecorderFragment();

        //初始化Fragment列表
        list_fragment = new ArrayList<Fragment>();
        list_fragment.add(mGeneralFragment);
        list_fragment.add(mSubscribeFragment);
        list_fragment.add(mRecorderFragment);

        //初始化标题列表
        list_title = new ArrayList<String>();
        list_title.add("普通督导");
        list_title.add("预约督导");
        list_title.add("督导记录");

        //为TabLayout添加Tab并设置名称
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));
    }

    @Override
    public void initEvents(Bundle savedInstanceState) {

    }
}
