package com.jiaop.socket.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jiaop.socket.R;
import com.jiaop.socket.socket.Const;
import com.jiaop.socket.socket.NetManager;
import com.jiaop.socket.socket.SocketThreadManager;

/**
 * Socket又称套接字，在程序内部提供了与外界通信的端口，即端口通信。通过建立socket连接，可为通信双方的数据传输传提供通道。socket的主要特点有数据丢失率低，使用简单且易于移植。
 * TCP/IP协议族
 * Socket类型为流套接字（streamsocket）和数据报套接字(datagramsocket)。流套接字将TCP作为其端对端协议，提供了一个可信赖的字节流服务。数据报套接字使用UDP协议，提供数据打包发送服务。
 */
public class MainActivity extends AppCompatActivity {

    public static Context s_context;
    private BroadcastReceiver bcReceiver;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s_context = this;
        NetManager.instance().init(this);
        SocketThreadManager.sharedInstance();

        regBroadcast();
        //发送信息
        SocketThreadManager.sharedInstance().sendMsg("123456".getBytes(), handler);

        handler = new Handler( getMainLooper() )
        {
            @Override
            public void handleMessage(Message msg)
            {
                switch(msg.what){
                    case 0:
                        Log.d("消息发送","发送socket失败");
                        break;
                    case 1:
                        Log.d("消息发送","发送socket成功");
                        break;
                }
            }
        };
    }

    public void regBroadcast()
    {
        bcReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                final String value = intent.getStringExtra("response");

                runOnUiThread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        Log.d("Result",value);
                    }
                });

            }
        };
        IntentFilter intentToReceiveFilter = new IntentFilter();
        intentToReceiveFilter.addAction(Const.BC);
        registerReceiver(bcReceiver, intentToReceiveFilter);
    }
}
