package cn.gdut.xietong.supervisionsystem.dialog.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.dialog.weelview.AbstractWheelTextAdapter;
import cn.gdut.xietong.supervisionsystem.dialog.weelview.ArrayWheelAdapter;
import cn.gdut.xietong.supervisionsystem.dialog.weelview.OnWheelChangedListener;
import cn.gdut.xietong.supervisionsystem.dialog.weelview.WheelView;

/**
 * Created by MR.deng on 2016/3/9.
 */
public class LinkagePicker extends LinearLayout{

    private Context mContext;

    private WheelView[] stage;

    private LayoutParams [] paramses;

    private View contentView;

    //将当前滑动后的结果保存起来，用-隔开
    private String current_display;

    //三个WheelView中应该显示的内容
    private static String[] first_wheel_content;
    private static String[][] second_wheel_content;
    private static String[][][] third_wheel_content;


    public LinkagePicker(Context context) {
        super(context);
        mContext = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_linkagepicker,null);

        stage = new WheelView[3];
        paramses = new LayoutParams[3];

        stage[0] = (WheelView) contentView.findViewById(R.id.wheel_first_stage);
        stage[1] = (WheelView) contentView.findViewById(R.id.wheel_second_stage);
        stage[2] = (WheelView) contentView.findViewById(R.id.wheel_third_stage);

        addView(contentView);

        setDefaultViewItem();

        initScroolEvent();

        stage[0].setCurrentItem(1);
        stage[1].setCurrentItem(1);
        stage[2].setCurrentItem(1);

    }

    /**
     * 为三个Wheel设置监听事件
     */
    private void initScroolEvent() {
        stage[0].addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateFirstWheel(stage[1], second_wheel_content,newValue);
                changeResult();

            }
        });

        stage[1].addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                Log.i("info",getCurrent_display()+"");
                updateSecondWheel(stage[2],stage[0].getCurrentItem(),newValue);
                changeResult();
            }
        });

        stage[2].addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                changeResult();
            }
        });
    }

    private void changeResult() {
        current_display = first_wheel_content[stage[0].getCurrentItem()]
                + " | "
                + second_wheel_content[stage[0].getCurrentItem()][stage[1]
                .getCurrentItem()]
                + " | "
                + third_wheel_content[stage[0].getCurrentItem()][stage[1]
                .getCurrentItem()][stage[2].getCurrentItem()];
    }

    /**
     * 设置默认显示的items数目
     */
    private void setDefaultViewItem() {
        stage[0].setVisibleItems(3);
        stage[0].setViewAdapter(new FirstWheelAdapter(mContext,first_wheel_content));
        stage[1].setVisibleItems(0);
        stage[2].setVisibleItems(0);
    }

    /**
     * Updates the first wheel
     */
    private void updateFirstWheel(WheelView first_stage, String[][] second_wheel_content,int index) {
        Log.i("info",getCurrent_display()+"");
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(mContext,
                second_wheel_content[index]);
        adapter.setTextSize(18);
        first_stage.setViewAdapter(adapter);
        first_stage.setCurrentItem(0);
    }

    /**
     * Updates the second wheel
     */
    private void updateSecondWheel(WheelView second_stage, int index,
                               int index2) {
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(mContext,
                third_wheel_content[index][index2]);
        adapter.setTextSize(18);
        second_stage.setViewAdapter(adapter);
        second_stage.setCurrentItem(0);
    }

    /**
     * 得到当前显示的内容
     * @return 所选中的内容
     */
    public String getCurrent_display(){
        return current_display;
    }

    /**
     * 为三个WheelView分别设置上要显示的内容
     * @param first_wheel_content 第一个WheelView
     * @param second_wheel_content 第二个WheelView
     * @param third_wheel_content 第三个WheelView
     */
    public static void  setDisplayValues(String[] first_wheel_content,String[][] second_wheel_content,String[][][] third_wheel_content) {
        LinkagePicker.first_wheel_content = first_wheel_content;LinkagePicker.second_wheel_content = second_wheel_content;
        LinkagePicker.third_wheel_content = third_wheel_content;
    }

    private class FirstWheelAdapter extends AbstractWheelTextAdapter {

        private String[] first_wheel_content;

        protected FirstWheelAdapter(Context context,String[] first_wheel_content) {
            super(context, R.layout.wheel_item_layout,NO_RESOURCE);
            setItemTextResource(R.id.wheel_item_name);
            this.first_wheel_content = first_wheel_content;
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return first_wheel_content[index];
        }

        @Override

        public int getItemsCount() {
            return first_wheel_content.length;
        }
    }

}
