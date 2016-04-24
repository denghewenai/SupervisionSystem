package cn.gdut.xietong.supervisionsystem.dialog.material;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import cn.gdut.xietong.supervisionsystem.dialog.view.LinkagePicker;


/**
 * Created by deng on 2016/3/13.
 */
public class LinkagePickerDialog extends AlertDialog implements LinkagePickerDialogInterface, DialogInterface.OnClickListener {

    private static final String VALUE = "value";
    private LinkagePickerDialogInterface.OnResultSetListener listener;
    private LinkagePicker linkagePicker;

    protected LinkagePickerDialog(Context context, LinkagePickerDialogInterface.OnResultSetListener listener){
        this(context, 0, listener);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected LinkagePickerDialog(Context context, int theme, LinkagePickerDialogInterface.OnResultSetListener listener){
        super(context, theme);
        this.listener = listener;

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getText(android.R.string.cancel), (OnClickListener)null);

        linkagePicker = new LinkagePicker(themeContext);
        setView(linkagePicker);

    }

    private LinkagePicker newLinkagePicker(Context context){
        return new LinkagePicker(context);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(listener != null){
            linkagePicker.clearFocus();
            listener.onStringSet(linkagePicker, linkagePicker.getCurrent_display());
        }
    }

    @Override
    public LinkagePicker getLinkagePicker() {
        return linkagePicker;
    }

    @Override
    public String getDisplayedValue() {
        return linkagePicker.getCurrent_display();
    }

}
