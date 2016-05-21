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
import cn.gdut.xietong.supervisionsystem.adapter.RecordFragmentAdapter;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.view.XListView;
import cn.gdut.xietong.supervisionsystem.model.DuDaoLuRu;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;
import cn.gdut.xietong.supervisionsystem.utils.TimeUtil;

/**
 * Created by Administrator on 2016/1/19.
 */
public class RecorderFragment extends BaseFragment implements XListView.IXListViewListener{

    private String TAG = "RecorderFragment";
    private String URL = Config.URL_DUDAO_MANAGER;
    private int Number = 5;
    private List<DuDaoLuRu> list_book;
    private String super_tag = "RecorderFragment";
    private XListView mListView;
    private SimpleAdapter mAdapter1;
    private Handler mHandler;
    private ArrayList<HashMap<String, Object>> dlist;
    private RecordFragmentAdapter mAdapter;
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
        list_book = jsonHitoryData();
        mAdapter = new RecordFragmentAdapter(getActivity() , R.layout.fragment_record_list_item , list_book);
//        mAdapter1 = new SimpleAdapter(getActivity(), getData(),
//                R.layout.view_listview_items, new String[] { "name","place" },
//                new int[] { R.id.txt_name,R.id.txt_place });
        mListView.setAdapter(mAdapter);
        mListView.setXListViewListener(this);
        mHandler = new Handler();


    }

    @Override
    public void initEvents(Bundle savedInstanceState) {

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
        Number += 5;
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                list_book = jsonHitoryData();
//                myAdapter = new ManagerAdapter(getActivity(),R.layout.fragment_ddmanager_list_item,list_book);
                mAdapter.notifyDataSetChanged();
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    // 加载更多
    @Override
    public void onLoadMore() {
        Number += 5;
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                list_book = jsonHitoryData();
                mAdapter.notifyDataSetChanged();
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }
    private List<DuDaoLuRu> jsonHitoryData(){
        final List<DuDaoLuRu> list = new ArrayList<DuDaoLuRu>();
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
                        DuDaoLuRu myBook = new DuDaoLuRu();
                        myBook.setId(data_object.getString("id"));
                        String Booking_date = TimeUtil.longToString(Long.parseLong(data_object.getString("lessonDate")) , "yyyy-MM-dd");
                        myBook.setDate(Booking_date);//课程时间
                        myBook.setSection(data_object.getString("lessonSection"));//课程节数
                        myBook.setClassroom(data_object.getString("lessonClassroom"));//教室
                        myBook.setStudentFaculty(data_object.getString("studentFaculty"));//学院
                        myBook.setActualPopulation(Integer.parseInt(data_object.getString("actualNum")));//实际人数
                        myBook.setLateLeaveEarlyNum(Integer.parseInt(data_object.getString("lateLeaveearlyNum")));//早退人数
                        myBook.setTruantNum(Integer.parseInt(data_object.getString("truantNum")));//旷课情况（人数）
                        myBook.setVacateNum(Integer.parseInt(data_object.getString("vacateNum")));//请假情况（人数）
                        myBook.setPlayMobilNum(Integer.parseInt(data_object.getString("playMobilNum")));//玩手机人数
                        myBook.setFoodEatNum(Integer.parseInt(data_object.getString("foodEatNum")));//吃东西
                        myBook.setSleepSpeakNum(Integer.parseInt(data_object.getString("sleepSpeakNum")));//睡觉说话
                        myBook.setSlipperShortsNum(Integer.parseInt(data_object.getString("slipperShortsNum")));//穿拖鞋短裙
                        myBook.setSurveyTime(data_object.getString("surveyTime"));//调查时间，后台传过来的是null
                        myBook.setOtherSituation(data_object.getString("otherSituation"));//其他情况
                        myBook.setTeacherOntime(Integer.parseInt(data_object.getString("teacherOntime")));//老师是否准时
                        myBook.setSupervisor(data_object.getString("supervisor"));//督导员
                        myBook.setAddUser(data_object.getString("addUser"));
                        myBook.setAddTime(data_object.getString("addTime"));
                        myBook.setModifyUser(data_object.getString("modifyUser"));
                        myBook.setCourseClassNo(data_object.getString("courseClassNo"));//学期
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
