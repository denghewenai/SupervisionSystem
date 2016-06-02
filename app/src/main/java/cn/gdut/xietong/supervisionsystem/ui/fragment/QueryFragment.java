package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.adapter.CommonAdapter;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentInterface;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.SimpleDialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.material.ProgressDialogFragment;
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
    private View frameLayout;

    private Handler mHandler = new Handler();
    private   SurveyBookResult mBookResult;

    private String mString;
    private String[] data = new String[5];
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
                    subscribe();
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

        final ProgressDialogFragment dialogFragment = ProgressDialogFragment.newInstance(this,R.style.CustomDialog);
        dialogFragment.setMessage("正在查询…");
        dialogFragment.show(getChildFragmentManager(),"tag");

        OkHttpUtils.getDataAsync(getActivity(), String.format(Config.URL_ORDER_QUERY, (Object[]) data), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialogFragment.dismiss();
                        showToast("查询失败");
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Gson gson = new Gson();
                try {
                    mBookResult = gson.fromJson(response.body().string(), SurveyBookResult.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialogFragment.dismiss();
                        if (mBookResult != null && mBookResult.getStatus() == 1)
                            if (mBookResult.getSurveyBookList() != null && !mBookResult.getSurveyBookList().isEmpty()) {
                                viewSwitcher.showNext();
                                btn.setText("督导预约");
                                //设置page间间距
                                viewPager.setPageMargin(20);
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

    /**
     * 确认督导
     */
    private void subscribe() {
        DuDaoBook nowSelect = mBookResult.getSurveyBookList().get(viewPager.getCurrentItem());

        //Post请求的键值对
        Map<String,String> map = new HashMap<>();
        map.put("id",nowSelect.getId());
        map.put("classroom",nowSelect.getClassroom());
        map.put("semester",nowSelect.getSemester());
        map.put("weekName",nowSelect.getWeekName());
        map.put("section",nowSelect.getSection());
        map.put("weekNo", data[1] + "");

        OkHttpUtils.postKeyValuePairAsync(getActivity(), Config.URL_ORDER, map, new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast(e.toString());
                    }
                });
            }

            @Override
            public void onResponse( Response response) throws IOException {
                final String result = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                            showToast(result);
                    }
                });
            }
        },"subscribe");
    }

    /**
     * 初始化List中的数据
     * @param duDaoBook 查询的结果
     * @param type 布局类型
     * @return 一个List要显示的内容
     */
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
                data[0] = mString;
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

    /**
     * 判断为哪一个ET设置数据
     * @param dialog 对话框
     * @throws UnsupportedEncodingException
     */
    private void setTextWitch(DialogFragmentInterface dialog) throws UnsupportedEncodingException {
        switch (dialog.getTag()) {
            case 1 + "":
                data[1] = new String(mString.getBytes(), "utf-8");
                mTableRow1.setText(mString);
                break;
            case 2 + "":
                data[1] = String.valueOf(mString.charAt(1));
                mTableRow2.setText(mString);
                break;
            case 3 + "":
                data[2] = mString;
                mTableRow3.setText(mString);
                break;
            case 4 + "":
                data[3] = mString;
                mTableRow4.setText(mString);
                break;
            case 5 + "":
                data[4] = mString;
                mTableRow5.setText(mString);
                break;
            default:
                break;
        }
    }

    /**
     * ViewPager切换时根据位置不同显示不同的透明度
     */
    private class AlphaPageTransformer implements ViewPager.PageTransformer{
        private static final float DEFAULT_MIN_ALPHA = 0.5f;
        private static final float MIN_SCALE = 0.85f;
        private float mMinAlpha = DEFAULT_MIN_ALPHA;

        @Override
        public void transformPage(View view, float position) {

            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            Log.i("info","position="+position+"view="+viewPager.getCurrentItem());

            if(position < -1){
                view.setAlpha(mMinAlpha);
                view.setScaleY(MIN_SCALE);
                view.setScaleX(MIN_SCALE);
            }else if(position <= 1){
                //缩放因数
                float scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(position));
                //缩放时View减少的边距
                float horMargin = pageWidth * (1 - scaleFactor) / 2;
                float verMargin = pageHeight * (1 - scaleFactor)/2;

                if(position < 0){
                    //计算滑动过程中透明度的变换
                    float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                    view.setAlpha(factor);
                    view.setTranslationX(horMargin - verMargin / 2);
                }else {
                    float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                    view.setAlpha(factor);
                    //向左滑应该向左动，补上由于View缩小造成的间距
                    view.setTranslationX(-horMargin + verMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            }else{
                view.setAlpha(mMinAlpha);
                view.setScaleY(MIN_SCALE);
                view.setScaleX(MIN_SCALE);
            }

        }

    }

    /**
     * 接收返回按钮点击事件
     * @param keyCode 按键编码
     * @return 是否处理这个返回事件
     */
    public boolean onKeyDown(int keyCode){
        if(keyCode == KeyEvent.KEYCODE_BACK && viewSwitcher.getCurrentView().getId() == R.id.id_fragmentLayout){
            viewSwitcher.showPrevious();
            return true;
        }
        return false;
    }

}
