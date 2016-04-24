package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.utils.CommonUtils;

/**
 * 登录界面
 * Created by mr.deng on 2016/2/22.
 */
public class LoginActivity extends BaseActivity{

    private Button btn_login;
    private EditText et_username;
    private EditText et_password;

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
        et_username = (EditText) findViewById(R.id.id_et_username);
        et_password = (EditText) findViewById(R.id.id_et_password);
    }

    @Override
    public void initEvent() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CommonUtils.isNetworkAvailable(LoginActivity.this)){
                    showToast(getString(R.string.net_enable));
                }else{
                    login();
                }
            }
        });
    }

    private void login() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        String url = Config.URL_LOGIN + "String userName=" + username + "&String  password=" + password;

        Log.i("info","url"+url);

        if(TextUtils.isEmpty(username)){
            showToast("用户名不能为空");
            return;
        }

        if(TextUtils.isEmpty(password)){
            showToast("密码不能为空");
            return;
        }

        final  ProgressDialog pd = ProgressDialog.show(this,"正在登陆…","正在登陆…");
        startAnimActivity(MainActivity.class);
        pd.dismiss();
        finish();

    }
}
