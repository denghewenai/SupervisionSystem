package cn.gdut.xietong.supervisionsystem.model;

import android.text.Editable;

/**
 * Created by Administrator on 2016/1/20.
 */
public class InputBean {

    public static final int TYPE_WITHICON = 0;
    public static final int TYPE_SEPARATOR = 1;
    public static final int TYPE_JUSTEDIT = 2;
    public static final int TYPE_TEXT=4;


    private int type;

    private String name;

    private CharSequence content;

    private boolean isFocus;

    public void setIsFocus(boolean isFocus) {
        this.isFocus = isFocus;
    }

    public boolean getIsFocus() {
       return isFocus;
    }

    public InputBean(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public void setContent(Editable content) {
        this.content = content;
    }

    public CharSequence getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
