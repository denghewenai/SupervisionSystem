package cn.gdut.xietong.supervisionsystem.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import java.util.Calendar;
import java.util.Date;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.material.AlertDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.DatePickerDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.LinkagePickerDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.NumberPickerDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.StringPickerDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.view.LinkagePicker;

/**
 * Created by Administrator on 2016/1/22.
 */
public class DialogManager {

    private Context mContext;
    private int i = 0;

    public DialogManager(Context context) {
        mContext = context;
    }

    public void showNumberPickerDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager,int lable) {
        int value = 0;
        int min = 0;
        int max = 300;
        NumberPickerDialogFragment dialogFragment = NumberPickerDialogFragment.newInstance(provider, R.style.CustomDialog,value, min, max);
        dialogFragment.setTitle("请选择或输入人数");
        dialogFragment.show(fragmentManager, lable+"");
    }

    public void showDatePickerDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialogFragment dialogFragment = DatePickerDialogFragment.newInstance(provider,R.style.CustomDialog, year, month, day);
        dialogFragment.setTitle("选择督导日期，默认当天");
        dialogFragment.show(fragmentManager, "DatePickerDialog");
    }

    public void showSingleChoiceDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager) {

        String[] items = {"是", "否"};
        Bundle extra = new Bundle();
        extra.putStringArray("items", items);

        new AlertDialogFragment.Builder(mContext,R.style.CustomDialog)
                .setTitle("老师是否按时上课")
                .setSingleChoiceItems(items, 0)
                .setExtra(extra)
                .show(fragmentManager, "SingleChoiceDialog");
    }

    public void showStringPickerDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager, String[] values,int lable) {

        StringPickerDialogFragment dialogFragment = StringPickerDialogFragment.newInstance(provider, R.style.CustomDialog,values);
        dialogFragment.setTitle("请选择");
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        dialogFragment.show(fragmentManager, lable+"");

    }

    public void showLinkagePickerDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager,
                                        String[] first_wheel_content,String[][] second_wheel_content,
                                        String[][][] third_wheel_content){
        LinkagePicker.setDisplayValues(first_wheel_content, second_wheel_content, third_wheel_content);
        LinkagePickerDialogFragment dialogFragment = LinkagePickerDialogFragment.newInstance(provider,R.style.CustomDialog);
        dialogFragment.setTitle("上课地点");

        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
        dialogFragment.show(fragmentManager,"1");

    }

    public void showAlertDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager,String message){
        new AlertDialogFragment.Builder(mContext).setIcon(R.mipmap.ic_launcher)
                .setMessage(message)
                .show(fragmentManager, "SingleChoiceDialog");
    }
}

