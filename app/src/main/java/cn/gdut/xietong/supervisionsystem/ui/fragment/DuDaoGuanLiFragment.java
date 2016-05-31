package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
    private final int MESSAGE_JSON = 1,DELETE_DUDAO = 2;//设置刷新完发送消息
    private XListView mListView;
    private Handler mHandler;
    private String URL = Config.URL_MANAGE_ORDER,URL_DELETE = Config.URL_DELETE_ORDER;
    private String super_tag = "test";
    private List<DuDaoBook> list_book;
    private ManagerAdapter myAdapter;
    private int Number = 5;
    private myThread mThread = new myThread();
    private myHandler handler = new myHandler();
    private ProgressDialog pd;
    private int delete_flag = 0;
    private List<DuDaoBook> list_delete = new ArrayList<DuDaoBook>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews(View mContentView) {
        list_book = new ArrayList<DuDaoBook>();
        mListView = (XListView) findViewById(R.id.listView_YuYue);// 你这个listview是在这个layout里面
        mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
        pd = ProgressDialog.show(getActivity(),"正在获取消息，请稍后","正在获取消息，请稍后……");
        jsonHitoryData();
//        myAdapter = new ManagerAdapter(getActivity(),R.layout.fragment_ddmanager_list_item,list_book);
//        mListView.setAdapter(myAdapter);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
        initOnclickListner();
    }

    private void initOnclickListner(){
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DuDaoBook delete_book = myAdapter.getItem(position - 1);
                showDeleDialog(getActivity(),delete_book);
                return false;
            }
        });
    }
    @Override
    public void initEvents(Bundle savedInstanceState) {

    }

    @Override
    public void onRefresh() {
        pd.show();
        Number += 5;
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                jsonHitoryData();//更改数据源
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
        pd.show();
        Number += 5;
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                jsonHitoryData();
//                myAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
        Log.i(TAG,"onLoadMore");
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
    class deteleThread extends Thread{
        @Override
        public void run() {
            super.run();
            Message msg = Message.obtain();
            msg.what = DELETE_DUDAO;
            handler.sendMessage(msg);
        }
    }
    class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MESSAGE_JSON){
                if(myAdapter==null){
                    Log.i(TAG,"list_book="+list_book);
                    myAdapter = new ManagerAdapter(getActivity(),R.layout.fragment_ddmanager_list_item,list_book);
                    mListView.setAdapter(myAdapter);
                }else {
                    myAdapter.notifyDataSetChanged();
                }
            }
            if(msg.what == DELETE_DUDAO){
                myAdapter.remove(list_delete.get(0));
                list_delete.clear();
                myAdapter.notifyDataSetChanged();
            }
        }
    }
    private List<DuDaoBook> jsonHitoryData(){
//        final ProgressDialog pd = ProgressDialog.show(getActivity(),"正在获取消息，请稍后","正在获取消息，请稍后……");
        OkHttpUtils.getDataAsync(getActivity(),URL, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(TAG,"失败="+request);
                pd.dismiss();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(list_book!=null){
                    list_book.clear();
                }
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
                        list_book.add(myBook);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    pd.dismiss();
                }
                mThread.run();
            }
        },super_tag);
        return list_book;
    }

    /**
     *
     * @param context 上下文内容
     * @param delet_book 要删除的数据
     */
    private void showDeleDialog(final Context context, final DuDaoBook delet_book){
        list_delete.add(delet_book);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context); //实例化一个AlterDialog对象
        dialog.setIcon(R.drawable.icon_help);
        dialog.setTitle("督导管理");                                       //设置弹出窗口的主题
        dialog.setMessage("确定要删除该"+delet_book.getTeacherName()+"老师的预约督导记录吗？");                                //设置弹出窗口的内容
        dialog.setCancelable(false);                                             //设置为false时候不能通过Back键取消掉窗口
        dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {     //监听点击OK键会发生什么
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OkHttpUtils.getDataAsync(getActivity(), URL_DELETE + "&ids=" + delet_book.getId(), new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.i(TAG,"request_failure="+request);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        String data = response.body().string();
                        Log.i(TAG,"data_delete="+data);
                        try {
                            JSONObject object_delete = new JSONObject(data);
                            delete_flag = object_delete.getInt("status");
                            if(delete_flag == 1){//删除成功
                                new deteleThread().run();
                            }else {
                                Toast.makeText(getActivity(),"删除失败",Toast.LENGTH_SHORT).show();
                            }
                            Log.i(TAG,"flag="+delete_flag);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, "delete_id");
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {    //监听点击Cancel会发生什么
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
