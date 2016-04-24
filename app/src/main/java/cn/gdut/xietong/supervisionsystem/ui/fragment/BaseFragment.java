package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.gdut.xietong.supervisionsystem.app.App;


/**
 * Created by Administrator on 2016/1/18.
 */
public abstract class BaseFragment extends Fragment {

    public App mApplication;
    public LayoutInflater mInflater;

    private View mContentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = App.getInstance();
        mInflater = LayoutInflater.from(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutId(), container, false);
            initViews(mContentView);
            initEvents(savedInstanceState);
        }
        // 缓存的mContentView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个mContentView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }

        return mContentView;

    }

    /**复写本方法返回一个Fragment需要加载的Layout 布局
     * @return
     */
    public abstract int getLayoutId();

    /**在其中实现findViewById等初始化布局操作
     * @param mContentView 容器view可以通过其findViewById找到其包含的view
     */
    protected abstract void initViews(View mContentView);

    public abstract  void initEvents(Bundle savedInstanceState);

    public View findViewById(int paramInt) {
        return mContentView.findViewById(paramInt);
    }


    /**显示一个Toast
     * @param info 要显示的内容
     */
    public void showToast(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId){
        Toast.makeText(getActivity(),resId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
