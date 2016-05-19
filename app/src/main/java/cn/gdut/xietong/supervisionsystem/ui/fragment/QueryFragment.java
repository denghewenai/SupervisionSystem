package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentInterface;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.SimpleDialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.view.StringPicker;
import cn.gdut.xietong.supervisionsystem.model.SurveyBookResult;
import cn.gdut.xietong.supervisionsystem.utils.CommonUtils;
import cn.gdut.xietong.supervisionsystem.utils.DialogManager;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;

/**
 * Created by mr.deng on 2016/1/19
 */
public class QueryFragment extends BaseFragment implements View.OnClickListener,DialogFragmentCallbackProvider {

    private EditText mTableRow1;
    private EditText mTableRow2;
    private EditText mTableRow3;
    private EditText mTableRow4;
    private EditText mTableRow5;
    private Button btn;

    private ViewSwitcher viewSwitcher;
    private ViewPager viewPager;
    private List<Fragment> mFragments;

    private Handler mHandler = new Handler();
    public static SurveyBookResult mBookResult;

    private String mString;
    private String[] datas = new String[5];
    private DialogManager dialogManager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initViews(View mContentView) {

        dialogManager = new DialogManager(getActivity());
        btn = (Button) findViewById(R.id.id_btn_submit);
        mTableRow1 = (EditText) findViewById(R.id.id_row1);
        mTableRow2 = (EditText) findViewById(R.id.id_row2);
        mTableRow3 = (EditText) findViewById(R.id.id_row3);
        mTableRow4 = (EditText) findViewById(R.id.id_row4);
        mTableRow5 = (EditText) findViewById(R.id.id_row5);

        viewSwitcher = (ViewSwitcher) findViewById(R.id.id_viewSwitcher);
        viewPager = (ViewPager) findViewById(R.id.id_viewPager);

        //一共ViewPager里面维持着三个Fragment,当一个滑动时左右两边均滑动
        mFragments = new ArrayList<>();
        for(int i = 0;i < 3;i++){
            Fragment mTab = new BookFragment();
            mFragments.add(mTab);
        }

    }

    @Override
    public void initEvents(Bundle savedInstanceState) {
        mTableRow1.setOnClickListener(this);
        mTableRow2.setOnClickListener(this);
        mTableRow3.setOnClickListener(this);
        mTableRow4.setOnClickListener(this);
        mTableRow5.setOnClickListener(this);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.id_btn_submit:
                if(!CommonUtils.isNetworkAvailable(getActivity()))
                {
                    showToast(R.string.net_enable);
                    return;
                }
                if(btn.getText().equals("提交记录")) {
                    submit();
                }else if(btn.getText().equals("督导预约")){

                }
                break;
            case R.id.id_row1:
                dialogManager.showDatePickerDialog(this,getChildFragmentManager());
                break;
            case R.id.id_row2:
                dialogManager.showStringPickerDialog(this,getChildFragmentManager(), Config.WEEK_NO,2);
                break;
            case R.id.id_row3:
                dialogManager.showStringPickerDialog(this,getChildFragmentManager(), Config.FACULTY,3);
                break;
            case R.id.id_row4:
                dialogManager.showStringPickerDialog(this,getChildFragmentManager(), Config.WEEK_NAME,4);
                break;
            case R.id.id_row5:
                dialogManager.showStringPickerDialog(this,getChildFragmentManager(), Config.SECTION,5);
                break;
            default:
                break;
        }
    }

    private void submit() {

        OkHttpUtils.getDataAsync(getActivity(), String.format(Config.URL_ORDER_QUERY, datas), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                Log.i("info",response.body().string());
                try {
                    mBookResult = gson.fromJson(response.body().string(),SurveyBookResult.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        viewSwitcher.showNext();
                        btn.setText("督导预约");
                        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
                            @Override
                            public Fragment getItem(int position) {
                                return mFragments.get(position);
                            }

                            @Override
                            public int getCount() {
                                return mFragments.size();
                            }
                        });
                    }
                });


            }
        },"query");

    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback() {
        return new SimpleDialogFragmentCallback(){

            @Override
            public void onDateSet(DialogFragmentInterface dialog, DatePicker datePicker, int year, int month, int day) {
                if (month > 7){
                    mString = year-1+"_"+year+"_1";
                }else{
                    mString = year-1+"_"+year+"_2";
                }
                datas[0] = mString;
                mTableRow1.setText(year+"-"+(month+1)+"-"+day);
            }

            @Override
            public void onStringSet(DialogFragmentInterface dialog, StringPicker stringPicker, String value) {
                mString = value;
                try {
                    setTextWitch(dialog);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void setTextWitch(DialogFragmentInterface dialog) throws UnsupportedEncodingException {
        switch (dialog.getTag()){
            case 1+"" :
                datas[1] = new String(mString.getBytes(),"utf-8");
                mTableRow1.setText(mString);
                break;
            case 2+"" :
                datas[1] = String.valueOf(mString.charAt(1));
                mTableRow2.setText(mString);
                break;
            case 3+"" :
                datas[2] = mString;
                mTableRow3.setText(mString);
                break;
            case 4+"" :
                datas[3] = mString;
                mTableRow4.setText(mString);
                break;
            case 5+"" :
                datas[4] = mString;
                mTableRow5.setText(mString);
                break;
            default:
                break;
        }
    }

}
