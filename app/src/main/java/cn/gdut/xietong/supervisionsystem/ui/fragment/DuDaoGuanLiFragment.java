package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.adapter.ManagerAdapter;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.view.XListView;
import cn.gdut.xietong.supervisionsystem.model.DuDaoBook;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;
import cn.gdut.xietong.supervisionsystem.utils.TimeUtil;

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
    private List<DuDaoBook> list_book;
    private ManagerAdapter myAdapter;
    private int Number = 5;
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
        dlist = new ArrayList<HashMap<String, Object>>();
        list_book = new ArrayList<DuDaoBook>();
        list_book = jsonHitoryData();
        mListView = (XListView) findViewById(R.id.listView_YuYue);// 你这个listview是在这个layout里面
        mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
        myAdapter = new ManagerAdapter(getActivity(),R.layout.fragment_ddmanager_list_item,list_book);
//        mAdapter1 = new SimpleAdapter(getActivity(), getData(),
//                R.layout.view_listview_items, new String[] { "name","place" },
//                new int[] { R.id.txt_name,R.id.txt_place });
        mListView.setAdapter(myAdapter);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
    }

    @Override
    public void initEvents(Bundle savedInstanceState) {

    }

    @Override
    public void onRefresh() {
        Number += 5;
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                list_book = jsonHitoryData();
//                myAdapter = new ManagerAdapter(getActivity(),R.layout.fragment_ddmanager_list_item,list_book);
                myAdapter.notifyDataSetChanged();
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
        Number += 5;
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
//                getData();
//                mAdapter1.notifyDataSetChanged();
                list_book = jsonHitoryData();
                myAdapter = new ManagerAdapter(getActivity(),R.layout.fragment_ddmanager_list_item,list_book);
                mListView.setAdapter(myAdapter);
                onLoad();
            }
        }, 2000);
        Log.i(TAG,"onLoadMore");
    }
    private List<DuDaoBook> jsonHitoryData(){
        final List<DuDaoBook> list = new ArrayList<DuDaoBook>();
        OkHttpUtils.getDataAsync(getActivity(),URL, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(TAG,"失败="+request);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject myJson = new JSONObject(data);
                    JSONArray myArray = myJson.getJSONArray("eduSurveybookingList");
                    for(int i = 0 ; i < myArray.length() && i < Number ;i++){
                        JSONObject data_object = myArray.getJSONObject(i);
                        DuDaoBook myBook = new DuDaoBook();
                        myBook.setId(data_object.getString("id"));
                        myBook.setTeacherName(data_object.getString("teacherName"));//教师名称
                        myBook.setSemester(data_object.getString("courseClassNo"));//学期
                        myBook.setCourseName(data_object.getString("courseName"));//课程
                        myBook.setTeachingClassGroup(data_object.getString("classgroup"));//班级
                        myBook.setClassroom(data_object.getString("classroom"));//教室
                        myBook.setWeekName(data_object.getString("weekName"));//星期
                        myBook.setSection(data_object.getString("section"));//节次
                        String Booking_date = TimeUtil.longToString(Long.parseLong(data_object.getString("date")) , "yyyy-MM-dd");
                        Log.i(TAG,"Booking_date="+Booking_date);
                        myBook.setBookingDate(Booking_date);//预约日期
                        myBook.setWeekNo(Integer.parseInt(data_object.getString("weekNo")));//查询：周次
                        myBook.setCommenceDept(data_object.getString("studentFaculty"));//学院名称
                        list.add(myBook);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },super_tag);
        return list;
    }
}
