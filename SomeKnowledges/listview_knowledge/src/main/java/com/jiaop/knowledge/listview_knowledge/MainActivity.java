package com.jiaop.knowledge.listview_knowledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView emptyText;
    private MyAdapter myAdapter;

    private int lastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        emptyText = (TextView) findViewById(R.id.emptyText);
        //添加数据
        List<String> list = new ArrayList<String>();
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");

        //向listView中导入数据
        myAdapter = new MyAdapter(MainActivity.this,list);
        listView.setAdapter(myAdapter);

        //设置listView需要显示在第几项
        listView.setSelection(1);//瞬间完成的动作

        //平滑移动的动作
        listView.smoothScrollBy(10,10);//平滑滚动 distance 个像素,持续 duration 毫秒
        listView.smoothScrollByOffset(10);//点击一次滚动数量
        listView.smoothScrollToPosition(1);//平滑滚动到指定的适配器位置

        //当修改了list后,可通过一下方式进行listView的动态修改
        //必须保证此list为传进adapter的list对象,否则无效
        list.add("06");
        myAdapter.notifyDataSetChanged();

        //处理空ListView,显示某个布局
        listView.setEmptyView(emptyText);

        //获取可视区域内最后一个子项的id
        listView.getLastVisiblePosition();
        //获取可视区域内第一个子项的id
        listView.getFirstVisiblePosition();

    }

    /**
     * 滑动事件监听
     */
    private void touchListener(){
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //触摸时操作
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动时操作
                        break;
                    case MotionEvent.ACTION_UP:
                        //离开时操作
                        break;
                }
                return false;
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        //滑动停止时
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        //正在滚动
                        break;
                    case SCROLL_STATE_FLING:
                        //手指用力滑动,离开listView后有惯性继续滑动
                        break;
                }
            }

            //firstVisibleItem:能看见的第一个子项的id(从0开始)
            //visibleItemCount:当前能看到的Item的总数
            //totalItemCount:整个listView的子项的总数
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //滚动时一直调用

                //判断是否滚动到最后一行
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0){

                }

                //判断滚动的方向
                if(firstVisibleItem > lastVisibleItemPosition){
                    //上滑
                }else if(firstVisibleItem < lastVisibleItemPosition){
                    //下滑
                }
                lastVisibleItemPosition = firstVisibleItem;
            }
        });

    }

}
