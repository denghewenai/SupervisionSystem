package cn.gdut.xietong.supervisionsystem.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import java.util.Calendar;
import java.util.Date;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.material.AlertDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.DatePickerDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.NumberPickerDialogFragment;
import cn.gdut.xietong.supervisionsystem.dialog.material.StringPickerDialogFragment;

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
        NumberPickerDialogFragment dialogFragment = NumberPickerDialogFragment.newInstance(provider, value, min, max);
        dialogFragment.setIcon(R.mipmap.ic_launcher);
        dialogFragment.setTitle("请选择或输入人数");
        dialogFragment.show(fragmentManager, lable+"");
    }

    public void showDatePickerDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialogFragment dialogFragment = DatePickerDialogFragment.newInstance(provider, year, month, day);
        dialogFragment.setIcon(R.mipmap.ic_launcher);
        dialogFragment.setTitle("选择督导日期，默认当天");
        dialogFragment.show(fragmentManager, "DatePickerDialog");
    }

    public void showSingleChoiceDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager) {

        String[] items = {"是", "否"};
        Bundle extra = new Bundle();
        extra.putStringArray("items", items);

        new AlertDialogFragment.Builder(mContext).setIcon(R.mipmap.ic_launcher)
                .setTitle("老师是否按时上课")
                .setSingleChoiceItems(items, 0)
                .setExtra(extra)
                .show(fragmentManager, "SingleChoiceDialog");
    }

    public void showStringPickerDialog(DialogFragmentCallbackProvider provider, FragmentManager fragmentManager, String[] values,int lable) {

        StringPickerDialogFragment dialogFragment = StringPickerDialogFragment.newInstance(provider, values);
        dialogFragment.setIcon(R.mipmap.ic_launcher);
        dialogFragment.setTitle("请选择");
        dialogFragment.show(fragmentManager, lable+"");

    }
}

