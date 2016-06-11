package com.jiaop.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //activity注入格式
        ButterKnife.inject(MainActivity.this);
        //Fragment：
        // ButterKnife.inject(this, view);
        //ViewHolder:
        //ButterKnife.inject(this, view);
    }
    @OnClick({ R.id.textView, R.id.button })
    public void click(View view){
        switch (view.getId()){

        }
    }
}
