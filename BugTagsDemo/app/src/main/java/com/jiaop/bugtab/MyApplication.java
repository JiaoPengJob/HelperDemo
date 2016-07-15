package com.jiaop.bugtab;

import android.app.Application;

import com.bugtags.library.Bugtags;

/**
 * 设置Application
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //在这里初始化
        Bugtags.start("10c51733d3887d9a57d51dd815a30b9c", this, Bugtags.BTGInvocationEventNone);
    }
}
