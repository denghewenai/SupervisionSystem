package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import cn.gdut.xietong.supervisionsystem.R;

/**
 * 用户编辑个人信息界面
 * Created by 邓贺文 on 2016/1/19.
 */
public class UserInfoAcrtivity extends BaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initViews() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        CollapsingToolbarLayout clp_Toolbar = (CollapsingToolbarLayout) findViewById(R.id.id_collapsingToobar);
        clp_Toolbar.setTitle("用户名");
        clp_Toolbar.setCollapsedTitleTextColor(Color.WHITE);
        clp_Toolbar.setExpandedTitleColor(Color.BLUE);

    }

    @Override
    public void initEvent() {

        showLog(mToolbar+"");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
         protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_userinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
