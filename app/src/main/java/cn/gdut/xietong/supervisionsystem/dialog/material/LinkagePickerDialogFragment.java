package cn.gdut.xietong.supervisionsystem.dialog.material;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallback;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentCallbackProvider;
import cn.gdut.xietong.supervisionsystem.dialog.interfaces.DialogFragmentInterface;
import cn.gdut.xietong.supervisionsystem.dialog.view.LinkagePicker;

/**
 * Created by Administrator on 2016/3/9.
 */
public class LinkagePickerDialogFragment extends AlertDialogFragment implements DialogFragmentInterface
            ,LinkagePickerDialogInterface,LinkagePickerDialogInterface.OnResultSetListener{


    private static final String FIRST_WHEEL = "first";
    private static final String SECOND_WHEEL = "second";
    private static final String THIRD_WHEEL = "third";
    private LinkagePickerDialog linkagePickerDialog;

    private String value;

    public static LinkagePickerDialogFragment newInstance(DialogFragmentCallbackProvider provider){
        return newInstanceInternal(provider, VALUE_NULL);
    }

    public static LinkagePickerDialogFragment newInstance(DialogFragmentCallbackProvider provider, int theme){
        return newInstanceInternal(provider, theme);
    }

    private static LinkagePickerDialogFragment newInstanceInternal(DialogFragmentCallbackProvider provider, int theme){
        assertListenerBindable(provider);
        LinkagePickerDialogFragment fragment = new LinkagePickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(THEME, theme);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Bundle args = getArguments();
        final int theme = args.getInt(THEME);

        if(theme == VALUE_NULL){
            linkagePickerDialog = new LinkagePickerDialog(getActivity(),this);
        }else{
            linkagePickerDialog = new LinkagePickerDialog(getActivity(),theme,this);
        }
        if(iconId != VALUE_NULL){
            setIcon(iconId);
        }
        if(title != null){
            setTitle(title);
        }
        if(titleId != VALUE_NULL){
            setTitle(titleId);
        }
        return linkagePickerDialog;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.w(TAG, "onDestroyView");
        linkagePickerDialog = null;
    }

    @Override
    public Dialog getDialog(){
        return linkagePickerDialog;
    }

    @Override
    public LinkagePicker getLinkagePicker() {
        return linkagePickerDialog.getLinkagePicker();
    }

    @Override
    public String getDisplayedValue() {
        if(linkagePickerDialog == null){
            return value;
        }else{
            return linkagePickerDialog.getDisplayedValue();
        }
    }

    @Override
    public void onStringSet(LinkagePicker linkagePicker, String value) {
        DialogFragmentCallback listener = getDialogFragmentCallback();
        if(listener != null){
            listener.onResultSet(this, linkagePicker, value);
        }
    }
}
