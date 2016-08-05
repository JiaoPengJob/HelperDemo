package com.jiaop.delayed;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 启动画面（一幅图、一个提示之类的），停留2-3秒，然后结束掉自己，启动要呈现的第一个activity
 */
public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.finish();
                //跳入到主页面
            }
        }, 3000);//时间间隔后跳转
    }
}
