package cn.gdut.xietong.supervisionsystem.ui.activity;

import android.os.Bundle;
import android.view.View;

import cn.gdut.xietong.supervisionsystem.R;

/**帮助界面
 * Created by deng on 2016/1/19.
 */
public class HelpActivity extends BaseActivity{

    @Override
    protected void initFragment(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    public void initViews() {
        mToolbar.setTitle("帮助");
    }

    @Override
    public void initEvent() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
