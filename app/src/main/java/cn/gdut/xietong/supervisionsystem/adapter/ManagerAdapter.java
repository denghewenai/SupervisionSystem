package cn.gdut.xietong.supervisionsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.model.DuDaoBook;

/**
 * Created by 林思旭 on 2016/5/21.
 */
public class ManagerAdapter extends ArrayAdapter<DuDaoBook> {
    private List<DuDaoBook> objects;
    private int resource;
    private Context context;
    public ManagerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ManagerAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ManagerAdapter(Context context, int resource, List<DuDaoBook> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource,null);
            holder.txt_name = (TextView)convertView.findViewById(R.id.txt_DDmanager_name);
            holder.txt_place = (TextView)convertView.findViewById(R.id.txt_DDmanager_place);
            holder.txt_week = (TextView)convertView.findViewById(R.id.txt_DDmanager_week);
            holder.txt_months = (TextView)convertView.findViewById(R.id.txt_DDmanager_months);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_name.setText(objects.get(position).getTeacherName());
        holder.txt_place.setText(objects.get(position).getClassroom());
        holder.txt_week.setText(objects.get(position).getWeekName());
        holder.txt_months.setText(objects.get(position).getBookingDate());
        return convertView;
    }
    public final class ViewHolder{
        public TextView txt_name;
        public TextView txt_place;
        public TextView txt_week;
        public TextView txt_months;
    }
}
