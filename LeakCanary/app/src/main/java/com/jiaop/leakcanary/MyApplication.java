package com.jiaop.leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by jiaop on 2016/6/11.
 */
public class MyApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

}
