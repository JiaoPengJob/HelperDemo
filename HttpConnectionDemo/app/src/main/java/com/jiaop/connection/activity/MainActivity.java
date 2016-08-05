package com.jiaop.connection.activity;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jiaop.connection.R;
import com.jiaop.connection.util.NetworkAsyncTask;
import com.jiaop.connection.util.NetworkImgAsyncTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private NetworkAsyncTask networkAsyncTask;
    private String url;
    private String name;
    private String age;
    private String TAG = "MainActivity";
    private Map<String, String> paramMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 上传参数到服务器获取数据
     */
    private void getData() {
        //参数可以是以下格式拼接的字符串,可以为json字符串
        String param = "name=" + name + "&age=" + age;
        networkAsyncTask = new NetworkAsyncTask(url,
                "POST",
                "",
                param,
                handler,
                1);
        networkAsyncTask.execute();
    }

    /**
     * 上传文件到服务器获取数据
     */
    private void getImgData() {
        //设置上传文件时所需要的参数
        paramMap = new HashMap<String, String>();
        paramMap.put("kskm", "2");
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        //设置上传的文件
        Map<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("zp", new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/assess_fail.png"));

        NetworkImgAsyncTask networkImgAsyncTask = new NetworkImgAsyncTask(url,
                "", paramMap, fileMap, handler, 101);
        networkImgAsyncTask.execute();
    }

    /**
     * 接收服务器返回的结果
     */
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0://返回结果为空
                    Log.e(TAG, "返回结果为空!");
                    break;
                case 1:
                    //接收数据返回结果
                    Log.d(TAG, message.obj.toString());
                    break;
                case 101:
                    //接收文件文件返回数据
                    Log.d(TAG, message.obj.toString());
                    break;
            }
            return false;
        }
    });
}
