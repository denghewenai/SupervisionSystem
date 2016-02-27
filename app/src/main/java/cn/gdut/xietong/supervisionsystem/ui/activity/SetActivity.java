package cn.gdut.xietong.supervisionsystem.ui.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.adapter.SetListViewAdapter;
import cn.gdut.xietong.supervisionsystem.model.SetContent;

/**用户设置界面
 * Created by 邓贺文 on 2016/1/19.
 */
public class SetActivity extends BaseActivity{

    private String TAG = "SetActivity";
    private ListView listView_one;
    private SetListViewAdapter adapter_one;
    private List<SetContent> contentList_one = new ArrayList<SetContent>();
    private int [] ImageViewID = {0,R.drawable.set_listview_ownermessage,R.drawable.set_listview_setinform,R.drawable.set_listview_feedback,0,R.drawable.set_listview_seeour,R.drawable.set_listview_aboutour,R.drawable.set_listview_update,R.drawable.set_listview_exit,0};
    private String [] functionName = {"","账号设置","通知设置","反馈设置"," ","关注我们","关于我们","检查更新","退出登录"," "};
    private String [] promptContent = {"","编辑你的个人信息","设置是否接收系统通知","遇到问题？联系我们"," ","关注我们了解更多信息","了解我们的简单信息","获取最新版，获取更好服务","清除账户信息并退出到登录界面"," "};
    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initViews() {
        //分享

        initSetContent();
    }

    @Override
    public void initEvent() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listView_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:break;
                    case 1:break;
                    case 2:
                        startAnimActivity(SetNtfActivity.class);
                        break;
                    case 3:break;
                    case 4:break;
                    case 5:break;
                    case 6:break;
                    case 7:break;
                    case 8:break;
                    case 9:break;
                    case 10:break;
                }
            }
        });
    }

    private void initAdapter(){
        Log.i(TAG, "OK");
        adapter_one = new SetListViewAdapter(SetActivity.this,R.layout.listitm_number,R.layout.activity_set_view,contentList_one);
        listView_one = (ListView)findViewById(R.id.listView);
        listView_one.setAdapter(adapter_one);
        Log.i(TAG, "initAdapter");
    }
    private void initSetContent(){
        for(int i = 0; i < ImageViewID.length;i++){
            SetContent setContent_one = new SetContent(functionName[i], promptContent[i], ImageViewID[i]);
            contentList_one.add(setContent_one);
        }
        initAdapter();
    }
}
