package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.config.Config;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentInterface;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.SimpleDialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.view.LinkagePicker;
import cn.gdut.xietong.supervisionsystem.dialog.view.StringPicker;
import cn.gdut.xietong.supervisionsystem.model.InputBean;
import cn.gdut.xietong.supervisionsystem.utils.DialogManager;
/**
 * Created by 邓贺文 on 2016/1/19.
 *普通督导Fragment
 */
public class GeneralFragment extends BaseFragment implements View.OnClickListener,DialogFragmentCallbackProvider {


    private List<InputBean> mDatas;

    private DialogManager mDialogManager;

    private Button mButton;

    private TableLayout mTable;

    private String mString = "";

    private EditText mTableRow0;
    private EditText mTableRow1;
    private EditText mTableRow2;
    private EditText mTableRow3;
    private EditText mTableRow4;
    private EditText mTableRow5;
    private EditText mTableRow6;
    private EditText mTableRow7;
    private EditText mTableRow8;
    private EditText mTableRow9;
    private EditText mTableRow10;
    private EditText mTableRow11;
    private EditText mTableRow12;
    private EditText mTableRow13;
    private EditText mTableRow14;
    private EditText mTableRow15;
    private EditText mTableRow16;
    private EditText mTableRow17;
    private EditText mTableRow18;
    private EditText mTableRow19;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_general;
    }

    @Override
    protected void initViews(View mContentView) {

        mDatas = new ArrayList<InputBean>();
        mDialogManager = new DialogManager(getActivity());
        mTable = (TableLayout) findViewById(R.id.id_tabLayout);
        //简直日狗了,等我想到解决ListView错位的情况。。
        mTableRow0 = (EditText) findViewById(R.id.id_row0);
        mTableRow1 = (EditText) findViewById(R.id.id_row1);
        mTableRow2 = (EditText) findViewById(R.id.id_row2);
        mTableRow3 = (EditText) findViewById(R.id.id_row3);
        mTableRow4 = (EditText) findViewById(R.id.id_row4);
        mTableRow5 = (EditText) findViewById(R.id.id_row5);
        mTableRow6 = (EditText) findViewById(R.id.id_row6);
        mTableRow7 = (EditText) findViewById(R.id.id_row7);
        mTableRow8 = (EditText) findViewById(R.id.id_row8);
        mTableRow9 = (EditText) findViewById(R.id.id_row9);
        mTableRow10 = (EditText) findViewById(R.id.id_row10);
        mTableRow11 = (EditText) findViewById(R.id.id_row11);
        mTableRow12 = (EditText) findViewById(R.id.id_row12);
        mTableRow13 = (EditText) findViewById(R.id.id_row13);
        mTableRow14 = (EditText) findViewById(R.id.id_row14);
        mTableRow15 = (EditText) findViewById(R.id.id_row15);
        mTableRow16 = (EditText) findViewById(R.id.id_row16);
        mTableRow17 = (EditText) findViewById(R.id.id_row17);
        mTableRow18 = (EditText) findViewById(R.id.id_row18);
        mTableRow19 = (EditText) findViewById(R.id.id_row19);

        initDatas();

        mButton = (Button) findViewById(R.id.id_btn_submit);

    }

    @Override
    public void initEvents(Bundle savedInstanceState) {

        mTableRow0.setOnClickListener(this);
        mTableRow1.setOnClickListener(this);
        mTableRow2.setOnClickListener(this);
        mTableRow3.setOnClickListener(this);
        mTableRow4.setOnClickListener(this);
        mTableRow5.setOnClickListener(this);
        mTableRow6.setOnClickListener(this);
        mTableRow7.setOnClickListener(this);
        mTableRow8.setOnClickListener(this);
        mTableRow9.setOnClickListener(this);
        mTableRow10.setOnClickListener(this);
        mTableRow11.setOnClickListener(this);
        mTableRow12.setOnClickListener(this);
        mTableRow13.setOnClickListener(this);
        mTableRow14.setOnClickListener(this);
        mTableRow15.setOnClickListener(this);
        mTableRow16.setOnClickListener(this);
        mTableRow17.setOnClickListener(this);
        mTableRow18.setOnClickListener(this);
        mTableRow19.setOnClickListener(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!judgeAllEdit()){
                    showToast("有*号标记的输入信息不能为空，请完成信息的输入");
                }else{

                }
            }
        });
    }

    private boolean judgeAllEdit() {

        if(TextUtils.isEmpty(mTableRow0.getText()) ||TextUtils.isEmpty(mTableRow1.getText()) ||TextUtils.isEmpty(mTableRow2.getText())
                ||TextUtils.isEmpty(mTableRow3.getText()) ||TextUtils.isEmpty(mTableRow4.getText()) ||
                TextUtils.isEmpty(mTableRow5.getText()) ||TextUtils.isEmpty(mTableRow6.getText()) ||
                TextUtils.isEmpty(mTableRow7.getText()) ||TextUtils.isEmpty(mTableRow10.getText()) ||
                TextUtils.isEmpty(mTableRow11.getText()) ||TextUtils.isEmpty(mTableRow12.getText()) ||
                TextUtils.isEmpty(mTableRow13.getText()) ||TextUtils.isEmpty(mTableRow14.getText()) ||
                TextUtils.isEmpty(mTableRow15.getText()) ||TextUtils.isEmpty(mTableRow16.getText()) ||
                TextUtils.isEmpty(mTableRow17.getText()) ||TextUtils.isEmpty(mTableRow18.getText()) ||
                TextUtils.isEmpty(mTableRow19.getText())){
            return false;
        }
        return true;
    }

    private void initDatas() {

        for (String withIcon_content: Config.WITHICON_FIRST){
            InputBean input = new InputBean(InputBean.TYPE_WITHICON,withIcon_content);
            mDatas.add(input);
        }

        for (String justEdit_content:Config.JUSTEDIT_CONTENT) {

            InputBean input = new InputBean(InputBean.TYPE_JUSTEDIT,justEdit_content);
            mDatas.add(input);

        }

        for (String withIcon_content:Config.WITHICON_CONTENT){
            InputBean input = new InputBean(InputBean.TYPE_WITHICON,withIcon_content);
            mDatas.add(input);
        }

//        InputBean separator = new InputBean(InputBean.TYPE_SEPARATOR,"");
//        mDatas.add(separator);

    }

    /**
     * 对话框dismiss时的回调
     * @return
     */
    @Override
    public DialogFragmentCallback getDialogFragmentCallback() {
        return new SimpleDialogFragmentCallback(){

            @Override
            public void onStringSet(DialogFragmentInterface dialog, StringPicker stringPicker, String value) {
                mString = value;
                Log.i("info", dialog.getTag());
                setTextWitch(dialog);
            }

            @Override
            public void onDateSet(DialogFragmentInterface dialog, DatePicker datePicker, int year, int month, int day) {
                mString = year+"-"+(month+1)+"-"+day;
                mTableRow1.setText(mString);
            }

            @Override
            public void onNumberSet(DialogFragmentInterface dialog, NumberPicker numberPicker, int value) {
                mString = value+"";
                setTextWitch(dialog);
            }

            @Override
            public void onSingleChoiceClick(DialogFragmentInterface dialog, int position){
                Bundle extra = dialog.getExtra();
                String[] items = extra.getStringArray("items");
                mTableRow4.setText(items[position]);
            }

            @Override
            public void onResultSet(DialogFragmentInterface dialog, LinkagePicker linkagePicker, String value) {
                mTableRow3.setText(value);

            }
        };
    }

    @Override
    public void onClick(View v) {
        showWitch(v);
    }

    /**
     *   判断显示哪种对话框
     */
    private void showWitch(View v) {
        switch (v.getId()) {
            case R.id.id_row0:
                mDialogManager.showStringPickerDialog(this, getChildFragmentManager(), Config.SCHOOL,0);
                break;
            case R.id.id_row1:
                mDialogManager.showDatePickerDialog(this, getChildFragmentManager());
                break;
            case R.id.id_row2:
                mDialogManager.showStringPickerDialog(this, getChildFragmentManager(), Config.SECTION,2);
                break;
            case R.id.id_row3:
                mDialogManager.showLinkagePickerDialog(this,getChildFragmentManager(),Config.SCHOOL,
                        Config.TEACHGING_BUILDING,Config.CLASSROOM);
                if(TextUtils.isEmpty(mTableRow0.getText())){
                    showToast(R.string.toast_fristchooseschool);
                }else{
                    switch (mTableRow0.getText().toString()){
                        case "大学城":
                            break;
                        case "龙洞":
                            break;
                        case "东风路":
                            break;
                        case "商学院":
                            break;
                    }
                }
                break;
            case R.id.id_row4:
                mDialogManager.showSingleChoiceDialog(this, getChildFragmentManager(),new String[]{"是","否"},"老师是否按时上课");
                break;
            case R.id.id_row5:
                break;
            case R.id.id_row6:
                break;
            case R.id.id_row7:
                break;
            case R.id.id_row8:
                break;
            case R.id.id_row9:
                break;
            case R.id.id_row10:
                break;
            case R.id.id_row11:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),11);
                break;
            case R.id.id_row12:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),12);
                break;
            case R.id.id_row13:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),13);
                break;
            case R.id.id_row14:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),14);
                break;
            case R.id.id_row15:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),15);
                break;
            case R.id.id_row16:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),16);
                break;
            case R.id.id_row17:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),17);
                break;
            case R.id.id_row18:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),18);
                break;
            case R.id.id_row19:
                mDialogManager.showNumberPickerDialog(this, getChildFragmentManager(),19);
                break;
        }
    }

    /**
     * 判断为哪一个EditText设置数据
     * @param dialog
     */
    private void setTextWitch(DialogFragmentInterface dialog) {
        switch (dialog.getTag()){
            case 0+"" :
                mTableRow0.setText(mString);
                break;
            case 2+"" :
                mTableRow2.setText(mString);
                break;
            case 11+"" :
                mTableRow11.setText(mString);
                break;
            case 12+"" :
                mTableRow12.setText(mString);
                break;
            case 13+"" :
                mTableRow13.setText(mString);
                break;
            case 14+"" :
                mTableRow14.setText(mString);
                break;
            case 15+"" :
                mTableRow15.setText(mString);
                break;
            case 16+"" :
                mTableRow16.setText(mString);
                break;
            case 17+"" :
                mTableRow17.setText(mString);
                break;
            case 18+"" :
                mTableRow18.setText(mString);
                break;
            case 19+"" :
                mTableRow19.setText(mString);
                break;
        }
    }

}
