package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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

/**
 * 登录界面
 * Created by mr.deng on 2016/2/22.
 */
public class LoginActivity extends BaseActivity{

    private Button btn_login;
    private EditText et_username;
    private EditText et_password;
    private Handler handler;

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
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        final String urlString = String.format(Config.URL_LOGIN,username,password);

        Log.i("info",urlString);

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
                Log.i("info","onFailure");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        showToast("登陆失败");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i("info","onResponse"+response);
                String result = response.body().string();
                Log.i("info",result);
                if(result.contains("1")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
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
