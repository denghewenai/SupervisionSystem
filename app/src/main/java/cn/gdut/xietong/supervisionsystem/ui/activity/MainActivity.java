package cn.gdut.xietong.supervisionsystem.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.ui.fragment.DuDaoGuanLiFragment;
import cn.gdut.xietong.supervisionsystem.ui.fragment.HomeFragment;
import cn.gdut.xietong.supervisionsystem.ui.fragment.QueryFragment;

public class MainActivity extends BaseActivity {

    private ImageView mHead;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;

    private HomeFragment homeFragment;
    private DuDaoGuanLiFragment newsFragment;
    private QueryFragment queryFragment;

    private Fragment nowFragment;

    private static final int HELP_CLOSE_DRAWER = 0x100;
    private static final int HEAD_CLOSE_DRAWER = 0x101;
    private static final int SET_CLOSE_DRAWER =0x102 ;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == HELP_CLOSE_DRAWER){
                startAnimActivity(HelpActivity.class);
            }else if(msg.what == HEAD_CLOSE_DRAWER){
                startAnimActivity(UserInfoAcrtivity.class);
            }else if(msg.what == SET_CLOSE_DRAWER){
                startAnimActivity(SetActivity.class);
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.id_navigationView);
        View view =  mNavigationView.getHeaderView(0);
        mHead = (ImageView) view.findViewById(R.id.id_nv_headphoto);

        mToggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerLayout,mToolbar,R.string.open,R.string.close);
        mToggle.syncState();
        mDrawerLayout.setDrawerListener(mToggle);

        mNavigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    protected void initFragment(Bundle savedInstanceState) {

        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if(savedInstanceState==null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if(homeFragment==null) {
                homeFragment = new HomeFragment();
            }
            nowFragment = homeFragment;
            ft.replace(R.id.id_frameMain, homeFragment).commit();
        }

    }

    @Override
    public void initEvent() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String title = (String) menuItem.getTitle();
//                menuItem.setChecked(true);

                switch (title) {
                    case "主页":
                        restoreTitle(title);
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                        }
                        swichContent(nowFragment, homeFragment);
                        break;
                    case "预约管理":
                        restoreTitle(title);
                        if (newsFragment == null) {
                            newsFragment = new DuDaoGuanLiFragment();
                        }
                        swichContent(nowFragment, newsFragment);
                        break;
                    case "预约查询":
                        restoreTitle(title);
                        if (queryFragment == null) {
                            queryFragment = new QueryFragment();
                        }
                        swichContent(nowFragment, queryFragment);
                        break;
                    case "帮助":
                        mHandler.sendEmptyMessageDelayed(HELP_CLOSE_DRAWER, 300);
                        break;
                    case "设置":
                        mHandler.sendEmptyMessageDelayed(SET_CLOSE_DRAWER,300);
                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        mHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessageDelayed(HEAD_CLOSE_DRAWER,300);
                mDrawerLayout.closeDrawers();
            }
        });

    }

    /**
     * 当fragment进行切换时，
     * 采用隐藏与显示的方法加载fragment
     * 以防止数据的重复加载
     *
     * @param from 当前Fragment
     * @param to   要显示的Fragment
     */
    private void swichContent(Fragment from, Fragment to) {

        if (nowFragment != to) {
            nowFragment = to;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            ft.hide(from).add(R.id.id_frameMain, to).commit();
        } else {
            // 隐藏当前的fragment，显示下一个
            ft.hide(from).show(to).commit();
        }

    }

    private void restoreTitle(CharSequence title) {

        if (TextUtils.isEmpty(title)) {
            mToolbar.setTitle("主页");
            return;
        }

        mToolbar.setTitle(title);
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
        boolean soved = false;
        if(nowFragment instanceof QueryFragment){
            QueryFragment fragment = (QueryFragment) nowFragment;
            soved = fragment.onKeyDown(keyCode);
            if(soved){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
