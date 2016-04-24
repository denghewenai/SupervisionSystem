package cn.gdut.xietong.supervisionsystem.app;

import android.app.Application;

/**
 * Created by mr.deng on 2016/1/18.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized App getInstance() {
        return instance;
    }

}
