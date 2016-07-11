package com.jiaop.img.img;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.send);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        ImgHttp.uploadFile("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.ceshi,"http://backend.dev.hongjitech.cn/uploadImage");
                    }
                }.start();
            }
        });
    }
}
