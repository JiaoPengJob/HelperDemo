package com.jiaop.pull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动线程
        new MyThread3().start();
    }

    //    启动一个新线程解析xml
    class MyThread3 extends Thread {
        @Override
        public void run() {
            //获取到xml文件
            //InputStream inputStream = HttpUtils.httpMethod(PATH, "utf-8");
            InputStream inputStream = null;
            List<Person> persons = null;
            try {
                persons = PullParserUtils.parserXmlByPull(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
