package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.os.Bundle;
import android.os.Handler;

import cn.gdut.xietong.supervisionsystem.R;

/**
 * Created by Administrator on 2016/2/6
 */
public class WelcomeActivity extends BaseActivity{

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initViews() {

        //会先显示一下theme的背景颜色
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimActivity(LoginActivity.class);
                finish();
            }
        },3000);
    }

    @Override
    public void initEvent() {

    }
}
