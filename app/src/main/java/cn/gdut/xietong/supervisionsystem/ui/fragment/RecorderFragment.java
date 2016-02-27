package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.dialog.view.XListView;

/**
 * Created by Administrator on 2016/1/19.
 */
public class RecorderFragment extends BaseFragment implements XListView.IXListViewListener{

    private XListView mListView;
    private SimpleAdapter mAdapter1;
    private Handler mHandler;
    private ArrayList<HashMap<String, Object>> dlist;
    /** 初始化本地数据 */
    String data[] = new String[] { "姓名：吴德永1", "姓名：吴德永2", "姓名：吴德永3",
            "姓名：吴德永4", "姓名：吴德永5" };
    String data1[] = new String[] { "抚顺县救兵乡王木村", "抚顺县救兵乡王木村", "抚顺县救兵乡王木村",
            "抚顺县救兵乡王木村", "抚顺县救兵乡王木村" };
    @Override
    public int getLayoutId() {
        return R.layout.fragment_recorder;
    }

    @Override
    protected void initViews(View mContentView) {
        /** 下拉刷新，上拉加载 */
        dlist = new ArrayList<HashMap<String, Object>>();
        mListView = (XListView) findViewById(R.id.listView_xListView);// 你这个listview是在这个layout里面
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

    /** 停止刷新， */
    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    // 刷新
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

    // 加载更多
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
}
