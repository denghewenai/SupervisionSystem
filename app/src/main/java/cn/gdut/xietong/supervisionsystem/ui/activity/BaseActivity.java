package cn.gdut.xietong.supervisionsystem.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.app.App;

/**
 * Created by Administrator on 2016/1/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

     App mApplication;
     LayoutInflater mInflater;
     ActionBar mActionBar;
     Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mApplication = App.getInstance();
        mInflater = LayoutInflater.from(this);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);

        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            mActionBar = getSupportActionBar();

            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        initFragment(savedInstanceState);
        initEvent();
    }

    protected abstract void initFragment(Bundle savedInstanceState);


    /**
     * @return 返回一个布局ID
     */
    public abstract int getLayoutId() ;


    /**
     * 初始化视图
     */
    public abstract void initViews();

    /**
     * 注册点击事件
     */
    public abstract  void initEvent();

    /**
     * Activity切换
     * @param cla 要切换到的目标Activity.class
     */
    public void startAnimActivity(Class<?> cla) {
        this.startActivity(new Intent(this, cla));
    }


    /** 打印Log
     * ShowLog
     * @return void
     * @throws
     */
    public void showLog(String msg){
        Log.i("info", msg);
    }

    public void showToast(String info){
        Toast.makeText(BaseActivity.this, info, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId){
        Toast.makeText(BaseActivity.this,resId,Toast.LENGTH_SHORT).show();
    }

}
