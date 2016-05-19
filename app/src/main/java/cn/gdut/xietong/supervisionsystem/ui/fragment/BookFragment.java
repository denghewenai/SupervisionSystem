package cn.gdut.xietong.supervisionsystem.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.adapter.SupervisionOrderAdaper;
import cn.gdut.xietong.supervisionsystem.model.DuDaoBook;
import cn.gdut.xietong.supervisionsystem.model.ItemListViewBean;
import cn.gdut.xietong.supervisionsystem.model.SurveyBookResult;

/**
 * Created by Administrator on 2016/4/30
 */
public class BookFragment extends BaseFragment{

    private ListView listView;
    private SupervisionOrderAdaper mAdapter;
    private List<ItemListViewBean> mDatas;
    private DuDaoBook duDaoBook;
    private int[] layoutIds;
    private SurveyBookResult mBookResult;
    private int fragmentPosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    protected void initViews(View mContentView) {

        listView = (ListView) findViewById(R.id.id_list);
        duDaoBook = QueryFragment.mBookResult.getSurveyBookList().get(0);
        initData();
        layoutIds = new int[]{R.layout.fragment_book};
        mAdapter = new SupervisionOrderAdaper(getActivity(),mDatas,layoutIds);
        listView.setAdapter(mAdapter);

    }



    @Override
    public void initEvents(Bundle savedInstanceState) {

    }

    private void initData() {

        ItemListViewBean item1 = new ItemListViewBean("课程编号",duDaoBook.getId());
        ItemListViewBean item2 = new ItemListViewBean("教室",duDaoBook.getClassroom());
        ItemListViewBean item3 = new ItemListViewBean("日期",duDaoBook.getBookingDate().toString());
        ItemListViewBean item4 = new ItemListViewBean("星期",duDaoBook.getWeekName());
        ItemListViewBean item5 = new ItemListViewBean("节次",duDaoBook.getSection());
        ItemListViewBean item6 = new ItemListViewBean("周次",String.valueOf(duDaoBook.getWeekNo()));

    }
}
