package com.jiaop.knowledge.listview_knowledge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jiaop on 2016/6/13.
 */
public class MyAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context,List<String> list){
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list,null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.itemText);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        return convertView;
    }

    public final class ViewHolder{
        public TextView textView;
    }
}
