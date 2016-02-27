package cn.gdut.xietong.supervisionsystem.app;

import android.app.Application;

/**
 * Created by Administrator on 2016/1/18.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
