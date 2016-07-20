package jiaop.intent.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * IntentService是继承于Service并处理异步请求的一个类.在IntentService内有一个工作线程(onHandleIntent())来处理耗时操作
 * 启动IntentService的方式和启动传统Service一样
 * 当任务执行完后，IntentService会自动停止，而不需要我们去手动控制。
 *
 * 生命周期:onCreate() - onStartCommand() - onStart() - onHandlerIntent() - onDestroy()
 *
 * IntentService每次只会执行一个工作线程，执行完第一个再执行第二个，以此类推.
 * 每一次访问新线程时,重新调用onHandlerIntent(),直至onHandlerIntent()中没有任务可执行,自动调用onDestroy()。
 * 每启动一次，就会新建一个work thread，但IntentService的实例始终只有一个,
 * 会调用一次onCreate(),视任务数开启onStartCommand()和onStart(),开启了相同数量的Work Thread。
 * 别忘了在AndroidManifest.xml中配置IntentSevice.
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent("jiaop.intent.service.MyIntentService");
                intent.putExtra("tag","1");
                startService(intent);

                Intent intent1 =  new Intent("jiaop.intent.service.MyIntentService");
                intent1.putExtra("tag","2");
                startService(intent1);
            }
        });
    }
}
