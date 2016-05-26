package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
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
import cn.gdut.xietong.supervisionsystem.adapter.CommonAdapter;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentInterface;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.SimpleDialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.view.StringPicker;
import cn.gdut.xietong.supervisionsystem.model.DuDaoBook;
import cn.gdut.xietong.supervisionsystem.model.ItemListViewBean;
import cn.gdut.xietong.supervisionsystem.model.SurveyBookResult;
import cn.gdut.xietong.supervisionsystem.utils.CommonUtils;
import cn.gdut.xietong.supervisionsystem.utils.DialogManager;
import cn.gdut.xietong.supervisionsystem.utils.OkHttpUtils;
import cn.gdut.xietong.supervisionsystem.utils.ViewHolder;

/**
 * Created by mr.deng on 2016/1/19
 */
public class QueryFragment extends BaseFragment implements View.OnClickListener, DialogFragmentCallbackProvider {

    private EditText mTableRow1;
    private EditText mTableRow2;
    private EditText mTableRow3;
    private EditText mTableRow4;
    private EditText mTableRow5;
    private Button btn;

    private ViewSwitcher viewSwitcher;
    private ViewPager viewPager;
    private List<Fragment> mFragments;
    private View frameLayout;

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
        frameLayout = findViewById(R.id.id_fragmentLayout);

        //一共ViewPager里面维持着三个Fragment,当一个滑动时左右两边均滑动
        mFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
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

        //实现两侧的page也能滑动
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_submit:
                if (!CommonUtils.isNetworkAvailable(getActivity())) {
                    showToast(R.string.net_enable);
                    return;
                }
                if (btn.getText().equals("提交记录")) {
                    submit();
                } else if (btn.getText().equals("督导预约")) {

                }
                break;
            case R.id.id_row1:
                dialogManager.showDatePickerDialog(this, getChildFragmentManager());
                break;
            case R.id.id_row2:
                dialogManager.showStringPickerDialog(this, getChildFragmentManager(), Config.WEEK_NO, 2);
                break;
            case R.id.id_row3:
                dialogManager.showStringPickerDialog(this, getChildFragmentManager(), Config.FACULTY, 3);
                break;
            case R.id.id_row4:
                dialogManager.showStringPickerDialog(this, getChildFragmentManager(), Config.WEEK_NAME, 4);
                break;
            case R.id.id_row5:
                dialogManager.showStringPickerDialog(this, getChildFragmentManager(), Config.SECTION, 5);
                break;
            default:
                break;
        }
    }

    /**
     * 提交查询请求，并解析出结果进行显示
     */
    private void submit() {

        OkHttpUtils.getDataAsync(getActivity(), String.format(Config.URL_ORDER_QUERY, datas), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                try {
                    mBookResult = gson.fromJson(response.body().string(), SurveyBookResult.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("info", e.toString());
                }
                Log.i("info", mBookResult.getSurveyBookList().get(0).toString() + "mBookResult");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mBookResult != null && mBookResult.getStatus() == 1)
                            if (mBookResult.getSurveyBookList() != null && !mBookResult.getSurveyBookList().isEmpty()) {
                                viewSwitcher.showNext();
                                btn.setText("督导预约");
                                //设置page间间距
                                viewPager.setPageMargin(30);
                                //设置最大缓存的页面数目
                                viewPager.setOffscreenPageLimit(3);
                                viewPager.setPageTransformer(false,new AlphaPageTransformer());
                                viewPager.setAdapter(new PagerAdapter() {

                                    @Override
                                    public Object instantiateItem(ViewGroup container, int position) {
                                        ListView lv = new ListView(getActivity());
                                        int currentLayoutType = 0;
                                        List<ItemListViewBean> datas = initDatas(mBookResult.getSurveyBookList().get(position), currentLayoutType);
                                        BaseAdapter adapter = new CommonAdapter<ItemListViewBean>(getActivity(), datas,
                                                R.layout.listitem_2tv) {
                                            @Override
                                            public int getItemViewType(int position) {
                                                return getItem(position).getType();
                                            }

                                            @Override
                                            protected void initConvert(ViewHolder holder, ItemListViewBean itemListViewBean) {
                                                holder.setText(R.id.id_listItem_title, itemListViewBean.getTitle());
                                                holder.setText(R.id.id_listItem_content, itemListViewBean.getContent_text());
                                            }
                                        };
                                        lv.setAdapter(adapter);
                                        container.addView(lv);
                                        return lv;
                                    }

                                    @Override
                                    public void destroyItem(ViewGroup container, int position, Object object) {
                                        container.removeView((View) object);
                                    }

                                    @Override
                                    public int getCount() {
                                        return mBookResult.getSurveyBookList().size();
                                    }

                                    @Override
                                    public boolean isViewFromObject(View view, Object object) {
                                        return view == object;
                                    }
                                });
                                viewPager.setCurrentItem(mBookResult.getSurveyBookList().size() / 2);
                            }
                        else{
                            showToast("查询失败");
                        }
                    }
                });

            }
        }, "query");


    }

    private List<ItemListViewBean> initDatas(DuDaoBook duDaoBook, int type) {
        List<ItemListViewBean> datas = new ArrayList<>();
        ItemListViewBean item1 = new ItemListViewBean(type, "课程编号:", duDaoBook.getId());
        ItemListViewBean item2 = new ItemListViewBean(type, "教室:", duDaoBook.getClassroom());
        ItemListViewBean item3 = new ItemListViewBean(type, "课程:", duDaoBook.getCourseName());
        ItemListViewBean item4 = new ItemListViewBean(type, "授课教师:", duDaoBook.getTeacherName());
        ItemListViewBean item5 = new ItemListViewBean(type,"教学班组成:", duDaoBook.getTeachingClassGroup());
        ItemListViewBean item6 = new ItemListViewBean(type, "节次:", duDaoBook.getSection());
        ItemListViewBean item7 = new ItemListViewBean(type, "星期:", duDaoBook.getWeekName());

        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);
        datas.add(item5);
        datas.add(item6);
        datas.add(item7);
        return datas;
    }

    @Override
    public DialogFragmentCallback getDialogFragmentCallback() {
        return new SimpleDialogFragmentCallback() {

            @Override
            public void onDateSet(DialogFragmentInterface dialog, DatePicker datePicker, int year, int month, int day) {
                if (month > 7) {
                    mString = year - 1 + "-" + year + "-1";
                } else {
                    mString = year - 1 + "-" + year + "-2";
                }
                datas[0] = mString;
                mTableRow1.setText(year + "-" + (month + 1) + "-" + day);
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
        switch (dialog.getTag()) {
            case 1 + "":
                datas[1] = new String(mString.getBytes(), "utf-8");
                mTableRow1.setText(mString);
                break;
            case 2 + "":
                datas[1] = String.valueOf(mString.charAt(1));
                mTableRow2.setText(mString);
                break;
            case 3 + "":
                datas[2] = mString;
                mTableRow3.setText(mString);
                break;
            case 4 + "":
                datas[3] = mString;
                mTableRow4.setText(mString);
                break;
            case 5 + "":
                datas[4] = mString;
                mTableRow5.setText(mString);
                break;
            default:
                break;
        }
    }

    class AlphaPageTransformer implements ViewPager.PageTransformer{
        private static final float DEFAULT_MIN_ALPHA = 0.3f;
        private float mMinAlpha = DEFAULT_MIN_ALPHA;

        @Override
        public void transformPage(View view, float position) {

            if(position < -1){
                view.setAlpha(mMinAlpha);
            }else if(position <= 1){

                if(position < 0){
                    //计算滑动过程中透明度的变换
                    float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                    view.setAlpha(factor);
                }else {
                    float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                    view.setAlpha(factor);
                }

            }else{
                view.setAlpha(mMinAlpha);
            }

        }

    }

}
