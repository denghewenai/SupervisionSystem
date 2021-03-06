package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.utils.CommonUtils;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;
import cn.gdut.xietong.supervisionsystem.utils.SPUtils;

/**
 * 登录界面
 * Created by mr.deng on 2016/2/22.
 */
public class LoginActivity extends BaseActivity{

    private Button btn_login;
    private EditText et_username;
    private EditText et_password;
    private Handler handler;

    private String username;
    private String password;

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

        username = (String) SPUtils.get(LoginActivity.this,"username","admin");
        password = (String) SPUtils.get(LoginActivity.this,"password","password");

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) ){
            et_username.setText(username);
            et_password.setText(password);
            login();
        }

        handler = new Handler();
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
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        final String urlString = String.format(Config.URL_LOGIN,username,password);

        if(TextUtils.isEmpty(username)){
            showToast("用户名不能为空");
            return;
        }

        if(TextUtils.isEmpty(password)){
            showToast("密码不能为空");
            return;
        }

        final  ProgressDialog pd = ProgressDialog.show(this,"正在登陆…","正在登陆…");

        OkHttpUtils.getDataAsync(LoginActivity.this,urlString, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        et_password.setText("");
                        showToast("登陆失败,请重试");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                if(result.contains("1")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            SPUtils.put(LoginActivity.this,"username",username);
                            SPUtils.put(LoginActivity.this,"password",password);
                            startAnimActivity(MainActivity.class);
                            pd.dismiss();
                            finish();
                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            showToast("登陆失败");
                        }
                    });
                }
            }
        }, "login");
    }

}
