package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.view.XListView;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;

/**
 * Created by Administrator on 2016/1/19.
 */
public class DuDaoGuanLiFragment extends BaseFragment implements XListView.IXListViewListener{
    private String TAG = "DuDaoGuanLiFragment";
    private XListView mListView;
    private SimpleAdapter mAdapter1;
    private Handler mHandler;
    private ArrayList<HashMap<String, Object>> dlist;
    private String URL = Config.URL_MANAGE_ORDER;
    private String super_tag = "test";
    /** 初始化本地数据 */
    String data[] = new String[] { "姓名：吴德永1", "姓名：吴德永2", "姓名：吴德永3",
            "姓名：吴德永4", "姓名：吴德永5" };
    String data1[] = new String[] { "抚顺县救兵乡王木村", "抚顺县救兵乡王木村", "抚顺县救兵乡王木村",
            "抚顺县救兵乡王木村", "抚顺县救兵乡王木村" };
    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews(View mContentView) {
        jsonHitoryData();
        dlist = new ArrayList<HashMap<String, Object>>();
        mListView = (XListView) findViewById(R.id.listView_YuYue);// 你这个listview是在这个layout里面
        mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
        mAdapter1 = new SimpleAdapter(getActivity(), getData(),
                R.layout.view_listview_items, new String[] { "name","place" },
                new int[] { R.id.txt_name,R.id.txt_place });
        mListView.setAdapter(mAdapter1);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
    }

    @Override
    public void initEvents(Bundle savedInstanceState) {

    }
    private ArrayList<HashMap<String, Object>> getData() {
        for (int i = 0; i < data.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", data[i]);
            map.put("place", data1[i]);
            dlist.add(map);
        }
        return dlist;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getData();
                mListView.setAdapter(mAdapter1);
                onLoad();
            }
        }, 2000);
    }
    /** 停止刷新， */
    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getData();
                mAdapter1.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }
    private void jsonHitoryData(){
        OkHttpUtils.getDataAsync(getActivity(),URL, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(TAG,"失败="+request);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                Log.i(TAG,"data="+data);
            }
        },super_tag);
    }
}
