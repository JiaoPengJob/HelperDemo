package com.jiaop.leakcanary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RefWatcher watch =  MyApplication.getRefWatcher(this);
        watch.watch("");
    }
}
