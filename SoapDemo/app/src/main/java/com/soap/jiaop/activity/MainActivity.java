package com.soap.jiaop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.soap.jiaop.R;
import com.soap.jiaop.manager.KSOAPManager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void userd() {
        Map<String, Object> map = new HashMap<String, Object>();
        KSOAPManager web = new KSOAPManager("", "", "");
        web.execInfor(map);
        String result = web.getResult();
        while (result == null) {
            result = web.getResult();
        }
        if (result != null) {
            //获取到数据
        }
    }
}
