package cn.gdut.xietong.supervisionsystem.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import cn.gdut.xietong.supervisionsystem.R;


/**
 * Created by 林思旭 on 2016/1/22
 * 该Activity是设置是否接收系统通知的Activity
 */
public class SetNtfActivity extends BaseActivity {
    private ToggleButton mToggleButton;
    @Override
    protected void initFragment(Bundle savedInstanceState) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ntf;
    }

    @Override
    public void initViews() {
        mToggleButton = (ToggleButton)findViewById(R.id.TogBtn_information);
    }

    @Override
    public void initEvent() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    Toast.makeText(SetNtfActivity.this,"已关闭系统消息提示",Toast.LENGTH_SHORT).show();
                    showLog("关闭");
                } else {
                    Toast.makeText(SetNtfActivity.this,"已开启系统消息提示",Toast.LENGTH_SHORT).show();
                    showLog("打开");
                }
            }
        });
    }
}
