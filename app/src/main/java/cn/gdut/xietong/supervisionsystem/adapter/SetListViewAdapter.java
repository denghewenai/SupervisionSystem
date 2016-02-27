package cn.gdut.xietong.supervisionsystem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.gdut.xietong.supervisionsystem.R;
import cn.gdut.xietong.supervisionsystem.model.SetContent;

/**
 * Created by Administrator on 2016/1/21.
 */
public class SetListViewAdapter extends ArrayAdapter<SetContent>{

    private String TAG = "SetListViewAdapter";
    private int resourceId;
    private int resourceId_padding;
    public SetListViewAdapter(Context context, int resource, int resource_padding, List<SetContent> objects) {
        super(context, resource,objects);
        resourceId = resource;
        resourceId_padding = resource_padding;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SetContent mycontent = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        if(position==0 || position == 4 || position == 9){
            View view_padding = LayoutInflater.from(getContext()).inflate(resourceId_padding,null);
            Log.i(TAG,"间隔");
            return view_padding;
        }
        Log.i(TAG,"开始getView");
            ImageView Image_fuction = (ImageView) view.findViewById(R.id.imageView_ListItem);
            TextView txt_function_name = (TextView) view.findViewById(R.id.txt_functionName);
            TextView txt_promptContent = (TextView) view.findViewById(R.id.txt_promptContent);
            Image_fuction.setImageResource(mycontent.getImageId());
            txt_function_name.setText(mycontent.getFunctionContent());
            txt_promptContent.setText(mycontent.getPromptContent());
            Log.i(TAG,"正常的视图");
            return view;
        }
    }

