package com.jiaop.knowledge.chatdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jiaop on 2016/6/13.
 */
public class MyAdapter extends BaseAdapter {

    private List<People> peoples;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context,List<People> peoples) {
        super();
        this.peoples = peoples;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return peoples.size();
    }

    @Override
    public People getItem(int position) {
        return peoples.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 第position个item时什么类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        People people = peoples.get(position);
        return people.getType();
    }

    /**
     * 不同布局的总数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            if(getItemViewType(position) == 0){
                convertView = layoutInflater.inflate(R.layout.item_left,null);
                viewHolder.img = (ImageView) convertView.findViewById(R.id.leftImg);
                viewHolder.text = (TextView) convertView.findViewById(R.id.leftText);
            }else{
                convertView = layoutInflater.inflate(R.layout.item_right,null);
                viewHolder.img = (ImageView) convertView.findViewById(R.id.rightImg);
                viewHolder.text = (TextView) convertView.findViewById(R.id.rightText);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setImageBitmap(peoples.get(position).getImg());
        viewHolder.text.setText(peoples.get(position).getText());
        return convertView;
    }

    public final class ViewHolder{
        public ImageView img;
        public TextView text;
    }
}
