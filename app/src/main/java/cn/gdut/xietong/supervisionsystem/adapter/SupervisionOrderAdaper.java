package cn.gdut.xietong.supervisionsystem.adapter;

import android.content.Context;

import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.model.ItemListViewBean;
import cn.gdut.xietong.supervisionsystem.utils.ViewHolder;

/**
 * Created by mr.deng on 2016/4/30.
 */
public class SupervisionOrderAdaper extends CommonAdapter<ItemListViewBean>{

    public SupervisionOrderAdaper(Context context, List<ItemListViewBean> datas, int... layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    protected void initConvert(ViewHolder holder, ItemListViewBean itemListViewBean) {
        holder.setText(R.id.id_listItem_title,itemListViewBean.getTitle());
        holder.setText(R.id.id_listItem_content,itemListViewBean.getContent_text());
    }
}
