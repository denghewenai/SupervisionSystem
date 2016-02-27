package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.gdut.xietong.supervisionsystem.R;

/**
 * 登录界面
 * Created by Administrator on 2016/2/22.
 */
public class LoginActivity extends BaseActivity{

    private Button btn_login;

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void initEvent() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimActivity(MainActivity.class);
                finish();
            }
        });
    }
}
