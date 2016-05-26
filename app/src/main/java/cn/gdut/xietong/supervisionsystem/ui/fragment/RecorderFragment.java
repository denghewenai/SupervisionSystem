package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.adapter.RecordFragmentAdapter;
import cn.gdut.xietong.supervisionsystem.app.App;
import cn.gdut.xietong.supervisionsystem.dialog.view.XListView;
import cn.gdut.xietong.supervisionsystem.model.DuDaoLuRu;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;
import cn.gdut.xietong.supervisionsystem.utils.TimeUtil;

/**
 * Created by Administrator on 2016/1/19.
 */
public class RecorderFragment extends BaseFragment implements XListView.IXListViewListener{

    private final int MAX_CACHE = 5;//最大缓存数量(已经成功)
    private final int MESSAGE_JSON = 1;//设置刷新完发送消息
    private final int ROW_MAX = 20,PAGE_MIN = 1;
    private String data;
    private String TAG = "RecorderFragment";
    private int page = 1;
    private String URL = "http://10.21.71.50:8088/jeecg3.6.2/phonesurveyController.do?manager&page="+page+"&row=20";
    private XListView mListView;
    private Handler mHandler;
    private Map<Integer,List<DuDaoLuRu>> map_LuRu;
    private String record = "record";
    private RecordFragmentAdapter mAdapter;
    private List<DuDaoLuRu> list_book,list_temp;
    private myThread mThread = new myThread();
    private myHandler handler = new myHandler();
    private ProgressDialog pd;
    @Override
    public int getLayoutId() {
        Log.i(TAG,"getLayoutId");
        return R.layout.fragment_recorder;
    }

    @Override
    protected void initViews(View mContentView) {
        /** 下拉刷新，上拉加载 */
        list_book = new ArrayList<DuDaoLuRu>();
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
            pd = ProgressDialog.show(getActivity(),"正在切换第"+((Integer)page).toString()+"页消息，请稍后","正在获取消息，请稍后");
            map_LuRu = App.getInstance().getContactList();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if(map_LuRu.containsKey(page)){
                        for(int i = 0 ;i < list_book.size();i++){
                            list_book.set(i,map_LuRu.get(page).get(i));
                        }
                        mAdapter = new RecordFragmentAdapter(getActivity(), R.layout.fragment_record_list_item , list_book);
                        mListView.setAdapter(mAdapter);
                        pd.dismiss();
                        showToast("已经缓存好了");
                        mAdapter.notifyDataSetChanged();
                    }else {
                        jsonHitoryData();//添加了新数据，下面对应删除旧数据
                        if(App.getInstance().getContactList().containsKey(page + MAX_CACHE)){
                            App.getInstance().getContactList().remove(page + MAX_CACHE); //缓存中存在前面数据删除掉
                        }
                    }
//                    jsonHitoryData();改
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
        page ++;
        pd = ProgressDialog.show(getActivity(),"正在获取第"+((Integer)page).toString()+"页消息，请稍后","正在获取消息，请稍后");
        map_LuRu = App.getInstance().getContactList();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if(map_LuRu.containsKey(page)){
                    for(int i = 0 ;i < list_book.size();i++){
                        list_book.set(i,map_LuRu.get(page).get(i));
                    }
                    mAdapter = new RecordFragmentAdapter(getActivity(), R.layout.fragment_record_list_item , list_book);
                    mListView.setAdapter(mAdapter);
                    pd.dismiss();
                    showToast("已经缓存好了");
                    mAdapter.notifyDataSetChanged();
                }else {
                    jsonHitoryData();//添加了新数据，下面对应删除旧数据
                    if(map_LuRu.containsKey(page - MAX_CACHE)){
                        if(page - MAX_CACHE > 0){
                            Log.i(TAG,"remove");
                            map_LuRu.remove(page - MAX_CACHE); //缓存中存在前面数据删除掉
                        }
                    }
                }
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
                    }
//                    App.getInstance().setContactList(page,list_book);
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    pd.dismiss();
//                    Log.i(TAG,"appPage="+page+","+list_book);
                    list_temp = new ArrayList<DuDaoLuRu>();//必须要new一个对象，要不然map数组的数据会全部被覆盖,改变它的数据源
                    list_temp.addAll(list_book);
                    App.getInstance().setContactList(page,list_temp);
                }
                mThread.run();
            }
        },record);
//            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getInstance().getContactList().clear();
//        Log.i(TAG,"onDestroy");
    }
}
