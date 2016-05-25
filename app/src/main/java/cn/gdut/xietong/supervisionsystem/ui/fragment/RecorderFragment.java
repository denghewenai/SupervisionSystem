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
    private final int ROW_MAX = 20,PAGE_MIN = 1;
    private String data;
    private String TAG = "RecorderFragment";
    private int row = 5,page = 1;
    private String URL = "http://10.21.71.50:8088/jeecg3.6.2/phonesurveyController.do?manager&page="+page+"&row=20";
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
    private ProgressDialog pd;
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
        pd = ProgressDialog.show(getActivity(),"正在获取第"+((Integer)page).toString()+"页消息，请稍后","正在获取消息，请稍后");
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
        if(page > PAGE_MIN){
            page --;
//            row = 20;
            pd = ProgressDialog.show(getActivity(),"正在切换第"+((Integer)page).toString()+"页消息，请稍后","正在获取消息，请稍后");
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    jsonHitoryData();
                    onLoad();
                }
            }, 2000);
        }else {
            showToast("已经是第一页了,点击最下面加载下一页");
            onLoad();
        }
    }

    // 加载更多
    @Override
    public void onLoadMore() {
//        if(row == ROW_MAX){
            page ++;
//            row = 5;
            pd = ProgressDialog.show(getActivity(),"正在获取第"+((Integer)page).toString()+"页消息，请稍后","正在获取消息，请稍后");
//        }else {
//            pd = ProgressDialog.show(getActivity(),"正在获取第"+((Integer)page).toString()+"页消息，请稍后","正在获取消息，请稍后");
//            row += 5;
//            pd.show();
//        }
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
        URL = "http://10.21.71.50:8088/jeecg3.6.2/phonesurveyController.do?manager&page="+page+"&row=20";
        Log.i(TAG,"URL="+URL);
//        final ProgressDialog pd = ProgressDialog.show(getActivity(),"正在获取消息，请稍后","正在获取消息，请稍后");
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
                        if(data_object.getString("actualNum")=="null"){
                            myBook.setActualPopulation("0");
                        }else{
                            myBook.setActualPopulation(data_object.getString("actualNum"));//实际人数
                        }
                        if(data_object.getString("truantNum")=="null"){
                            myBook.setTruantNum("0");
                        }else{
                            myBook.setTruantNum(data_object.getString("truantNum"));//旷课情况（人数)
                        }
                        if(data_object.getString("lateLeaveearlyNum")=="null"){
                            myBook.setLateLeaveEarlyNum("0");
                        }else {
                            myBook.setLateLeaveEarlyNum(data_object.getString("lateLeaveearlyNum"));//早退人数
                        }
                        if(data_object.getString("vacateNum")=="null"){
                            myBook.setVacateNum("0");
                        }else {
                            myBook.setVacateNum(data_object.getString("vacateNum"));//请假情况（人数）测试到这里了
                        }
                        if(data_object.getString("playMobilNum")=="null"){
                            myBook.setPlayMobilNum("0");
                        }else {
                            myBook.setPlayMobilNum(data_object.getString("playMobilNum"));//玩手机人数
                        }
                        if(data_object.getString("foodEatNum")=="null"){
                            myBook.setFoodEatNum("0");
                        }else {
                            myBook.setFoodEatNum(data_object.getString("foodEatNum"));//吃东西
                        }
                        if(data_object.getString("sleepSpeakNum")=="null"){
                            myBook.setSleepSpeakNum("0");
                        }else {
                            myBook.setSleepSpeakNum(data_object.getString("sleepSpeakNum"));//睡觉说话
                        }
                        if(data_object.getString("slipperShortsNum")=="null"){
                            myBook.setSlipperShortsNum("0");//穿拖鞋短裙
                        }else {
                            myBook.setSlipperShortsNum(data_object.getString("slipperShortsNum"));//穿拖鞋短裙
                        }
                        if(data_object.getString("surveyTime")=="null"){
                            myBook.setSurveyTime("0");//穿拖鞋短裙
                        }else {
                            myBook.setSurveyTime(data_object.getString("surveyTime"));//调查时间，后台传过来的是null
                        }
                        if(data_object.getString("otherSituation")=="null"){
                            myBook.setOtherSituation("无数据");//其他情况
                        }else{
                            myBook.setOtherSituation(data_object.getString("otherSituation"));//其他情况
                        }
                        if(data_object.getString("teacherOntime")=="null"){
                            myBook.setTeacherOntime("无数据");//其他情况
                        }else{
                            myBook.setTeacherOntime(data_object.getString("teacherOntime"));//老师是否准时
                        }
                        if(data_object.getString("supervisor")=="null"){
                            myBook.setSupervisor("无数据");//督导员
                        }else{
                            myBook.setSupervisor(data_object.getString("supervisor"));//督导员
                        }
                        if(data_object.getString("addUser")=="null"){
                            myBook.setAddUser("无数据");//督导员
                        }else{
                            myBook.setAddUser(data_object.getString("addUser"));
                        }
                        if(data_object.getString("addTime")=="null"){
                            myBook.setAddTime("无数据");//督导员
                        }else{
                            myBook.setAddTime(data_object.getString("addTime"));
                        }
                        if(data_object.getString("modifyUser")=="null"){
                            myBook.setModifyUser("无数据");//督导员
                        }else{
                            myBook.setModifyUser(data_object.getString("modifyUser"));
                        }
                        if(data_object.getString("courseClassNo")=="null"){
                            myBook.setCourseClassNo("无数据");//督导员
                        }else{
                            myBook.setCourseClassNo(data_object.getString("courseClassNo"));//学期
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
