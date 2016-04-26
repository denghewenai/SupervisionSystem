package cn.gdut.xietong.supervisionsystem.app;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;

/**
 * Created by mr.deng on 2016/1/18.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpClient();
        instance = this;
    }

    private void initOkHttpClient() {

        OkHttpClient okHttpClient = OkHttpUtils.getOkHttpClient();

    }

    public static synchronized App getInstance() {
        return instance;
    }

}
