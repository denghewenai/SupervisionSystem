package cn.gdut.xietong.supervisionsystem.app;

import android.app.Application;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gdut.xietong.supervisionsystem.model.DuDaoLuRu;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;

/**
 * Created by mr.deng on 2016/1/18.
 */
public class App extends Application {
    private String TAG = "App";
    private static App instance;
    private Map<Integer,List<DuDaoLuRu>> DuDaoList = new HashMap<Integer, List<DuDaoLuRu>>();//缓存

    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpClient();
        instance = this;
        Log.i(TAG,"onCreate");
    }

    private void initOkHttpClient() {

        OkHttpClient okHttpClient = OkHttpUtils.getOkHttpClient();

    }
    public Map<Integer, List<DuDaoLuRu>> getContactList() {
        if(DuDaoList == null || DuDaoList.size()==0)return null;
//        Log.i(TAG,"true="+DuDaoList.containsKey(3));显示有啊
        Log.i(TAG,"contactList1="+DuDaoList.get(1));
        Log.i(TAG,"contactList2="+DuDaoList.get(2));
        Log.i(TAG,"contactList3="+DuDaoList.get(3));
        return DuDaoList;
    }
//    public List<DuDaoLuRu> getListDuDao(int page){
//        Log.i(TAG,"1="+DuDaoList.get(page));
//        return DuDaoList.get(page);
//    }
    public void setContactList(int page , List<DuDaoLuRu> list) {
//        DuDaoList.put(page,list);
        DuDaoList.put(page,list);
        Log.i(TAG,"page="+page+","+"list="+list);
    }

    public static synchronized App getInstance() {
        return instance;
    }

}
