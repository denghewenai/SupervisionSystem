package cn.gdut.xietong.supervisionsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.model.DuDaoLuRu;

/**
 * Created by Administrator on 2016/5/21.
 */
public class RecordFragmentAdapter extends ArrayAdapter<DuDaoLuRu>{
    private List<DuDaoLuRu> objects;
    private int resource;
    private Context context;
    public RecordFragmentAdapter(Context context, int resource) {
        super(context, resource);
    }

    public RecordFragmentAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public RecordFragmentAdapter(Context context, int resource, List<DuDaoLuRu> objects) {
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
            holder.txt_studentFaculty = (TextView)convertView.findViewById(R.id.txt_DDmanager_week);
            holder.txt_months = (TextView)convertView.findViewById(R.id.txt_DDmanager_months);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_name.setText("督导员:"+objects.get(position).getModifyUser());
        holder.txt_place.setText("教室:"+objects.get(position).getClassroom());
        holder.txt_studentFaculty.setText("学院:"+objects.get(position).getStudentFaculty());
        holder.txt_months.setText("日期:"+objects.get(position).getDate());
        return convertView;
    }
    public final class ViewHolder{
        public TextView txt_name;
        public TextView txt_place;
        public TextView txt_studentFaculty;
        public TextView txt_months;
    }
}
