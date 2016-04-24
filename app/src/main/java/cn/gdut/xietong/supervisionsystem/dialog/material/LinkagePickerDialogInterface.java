package cn.gdut.xietong.supervisionsystem.dialog.material;

import cn.gdut.xietong.supervisionsystem.dialog.view.LinkagePicker;

/**
 * Created by Administrator on 2016/3/13.
 */
interface LinkagePickerDialogInterface {

    LinkagePicker getLinkagePicker();

    String getDisplayedValue();

    /**
     * The callback interface used to indicate the user is done filling in
     * the time (they clicked on the 'Set' button).
     */
    interface OnResultSetListener {

        /**
         * @param linkagePicker The view associated with this listener.
         * @param value The value that was set.
         */
        void onStringSet(LinkagePicker linkagePicker, String value);
    }

}
