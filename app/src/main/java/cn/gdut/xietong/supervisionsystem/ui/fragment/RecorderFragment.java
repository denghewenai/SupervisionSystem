package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import cn.gdut.xietong.supervisionsystem.dialog.view.XListView;
import cn.gdut.xietong.supervisionsystem.model.DuDaoLuRu;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;
import cn.gdut.xietong.supervisionsystem.utils.TimeUtil;

/**
 * Created by Administrator on 2016/1/19.
 */
public class RecorderFragment extends BaseFragment implements XListView.IXListViewListener{

    private final int MESSAGE_JSON = 1;//设置刷新完发送消息
    private final int ROW_MAX = 20;
    private String data;
    private String TAG = "RecorderFragment";
    private int row = 5,page = 1;
    private String URL = "http://10.21.71.50:8088/jeecg3.6.2/phonesurveyController.do?manager&page="+page+"&row="+row;
    private XListView mListView;
    private SimpleAdapter mAdapter1;
    private Handler mHandler;
    private ArrayList<HashMap<String, Object>> dlist;
    private String record = "record";
    private RecordFragmentAdapter mAdapter;
    private List<DuDaoLuRu> list_book = new ArrayList<DuDaoLuRu>();
    private boolean flag = false;
    private myThread mThread = new myThread();
    private myHandler handler = new myHandler();
//    /** 初始化本地数据 */
//    String data[] = new String[] { "姓名：吴德永1", "姓名：吴德永2", "姓名：吴德永3",
//            "姓名：吴德永4", "姓名：吴德永5" };
//    String data1[] = new String[] { "抚顺县救兵乡王木村", "抚顺县救兵乡王木村", "抚顺县救兵乡王木村",
//            "抚顺县救兵乡王木村", "抚顺县救兵乡王木村" };
    @Override
    public int getLayoutId() {
        Log.i(TAG,"getLayoutId");
        return R.layout.fragment_recorder;
    }

    @Override
    protected void initViews(View mContentView) {
        /** 下拉刷新，上拉加载 */
        dlist = new ArrayList<HashMap<String, Object>>();
        mListView = (XListView) findViewById(R.id.listView_ListView);// 你这个listview是在这个layout里面
        mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
        jsonHitoryData();
//        mAdapter = new RecordFragmentAdapter(getActivity(), R.layout.fragment_record_list_item , list_book);
//        mListView.setAdapter(mAdapter);
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
        if(row == ROW_MAX){
            page ++;
            row = 5;
        }else {
            row += 5;
        }
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
//                getData();
//                mListView.setAdapter(mAdapter1);
                jsonHitoryData();
//                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    // 加载更多
    @Override
    public void onLoadMore() {
        if(row == ROW_MAX){
            page ++;
            row = 5;
        }else {
            row += 5;
        }
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                jsonHitoryData();
                onLoad();
            }
        }, 2000);
    }
    class myThread extends Thread{
        @Override
        public void run() {
            super.run();
            Message msg = Message.obtain();
            msg.what = MESSAGE_JSON;
            handler.sendMessage(msg);
        }
    }
    class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MESSAGE_JSON){
                if(mAdapter==null){
                    Log.i(TAG,"list_book="+list_book);
                    mAdapter = new RecordFragmentAdapter(getActivity(), R.layout.fragment_record_list_item , list_book);
                    mListView.setAdapter(mAdapter);
                }else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    private void jsonHitoryData(){
        URL = "http://10.21.71.50:8088/jeecg3.6.2/phonesurveyController.do?manager&page="+page+"&row="+row;
        Log.i(TAG,"URL="+URL);
        final ProgressDialog pd = ProgressDialog.show(getActivity(),"正在获取消息，请稍后","正在获取消息，请稍后");
        OkHttpUtils.getDataAsync(getActivity(),URL, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(TAG,"失败="+request);
                pd.dismiss();
                showToast("获取消息失败，请重新刷新");
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if(list_book!=null){
                    list_book.clear();
                }
                data = response.body().string();
                Log.i(TAG,"data="+data);
                try {
                    JSONObject myJson = new JSONObject(data);
                    JSONArray myArray = myJson.getJSONArray("edusurveylist");
                    for(int i = 0 ; i < myArray.length();i++){
                        JSONObject data_object = myArray.getJSONObject(i);
                        DuDaoLuRu myBook = new DuDaoLuRu();
                        myBook.setId(data_object.getString("id"));
                        String Booking_date = TimeUtil.longToString(Long.parseLong(data_object.getString("lessonDate")) , "yyyy-MM-dd");
                        myBook.setDate(Booking_date);//课程时间
                        myBook.setSection(data_object.getString("lessonSection"));//课程节数
                        myBook.setClassroom(data_object.getString("lessonClassroom"));//教室
//                        Log.i(TAG,"studentFaculty="+data_object.getString("studentFaculty"));
                        myBook.setStudentFaculty(data_object.getString("studentFaculty"));//学院
                        try {
                            myBook.setActualPopulation(data_object.getInt("actualNum"));//实际人数
                        }catch (Exception e){
                            myBook.setActualPopulation(0);
                        }

                        try {
                            myBook.setTruantNum(data_object.getInt("truantNum"));//旷课情况（人数)
                        }catch (Exception e){
                            myBook.setTruantNum(0);
                        }

                        try {
                            myBook.setLateLeaveEarlyNum(Integer.parseInt(data_object.getString("lateLeaveearlyNum")));//早退人数
                        }catch (Exception e){
                            myBook.setLateLeaveEarlyNum(0);
                        }

                        try{
                            myBook.setVacateNum(Integer.parseInt(data_object.getString("vacateNum")));//请假情况（人数）测试到这里了
                        }catch(Exception e) {
                            myBook.setVacateNum(0);//请假情况（人数）
                        }

                        try{
                            myBook.setPlayMobilNum(data_object.getInt("playMobilNum"));//玩手机人数
                        }catch(Exception e) {
                            myBook.setPlayMobilNum(0);//玩手机人数
                        }

                        try{
                            myBook.setFoodEatNum(data_object.getInt("foodEatNum"));//吃东西
                        }catch(Exception e) {
                            myBook.setFoodEatNum(0);//吃东西
                        }

                        try{
                            myBook.setSleepSpeakNum(data_object.getInt("sleepSpeakNum"));//睡觉说话
                        }catch(Exception e) {
                            myBook.setSleepSpeakNum(0);//睡觉说话
                        }

                        try{
                            myBook.setSlipperShortsNum(data_object.getInt("slipperShortsNum"));//穿拖鞋短裙
                        }catch(Exception e) {
                            myBook.setSlipperShortsNum(0);//穿拖鞋短裙
                        }

                        try{
                            myBook.setSurveyTime(data_object.getString("surveyTime"));//调查时间，后台传过来的是null
                        }catch(Exception e) {
                            myBook.setSurveyTime("无数据");//调查时间
                        }

                        try{
                            myBook.setOtherSituation(data_object.getString("otherSituation"));//其他情况
                        }catch(Exception e) {
                            myBook.setSurveyTime("无数据");//其他情况
                        }

                        try{
                            myBook.setTeacherOntime(data_object.getInt("teacherOntime"));//老师是否准时
                        }catch(Exception e) {
                            myBook.setTeacherOntime(0);//老师是否准时
                        }

                        try{
                            myBook.setSupervisor(data_object.getString("supervisor"));//督导员
                        }catch(Exception e) {
                            myBook.setSupervisor("无数据");//督导员
                        }

                        try{
                            myBook.setAddUser(data_object.getString("addUser"));
                        }catch(Exception e) {
                            myBook.setAddUser("无数据");
                        }

                        try{
                            myBook.setAddTime(data_object.getString("addTime"));
                        }catch(Exception e) {
                            myBook.setAddTime("无数据");
                        }

                        try{
                            myBook.setModifyUser(data_object.getString("modifyUser"));
                        }catch(Exception e) {
                            myBook.setModifyUser("无数据");
                        }

                        try{
                            myBook.setCourseClassNo(data_object.getString("courseClassNo"));//学期
                        }catch(Exception e) {
                            myBook.setCourseClassNo("无数据");
                        }
                        list_book.add(myBook);
                        Log.i(TAG,"myBook="+myBook);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    pd.dismiss();
                }
                mThread.run();
            }
        },record);
//            mAdapter.notifyDataSetChanged();
    }
}
