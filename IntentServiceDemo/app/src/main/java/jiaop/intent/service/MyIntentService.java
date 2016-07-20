package jiaop.intent.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * IntentService
 */
public class MyIntentService extends IntentService {

    private static String TAG = "MyIntentService";
    private boolean b1;
    private boolean b2;
    private int index = -1;
    private int index1 = -1;

    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        Log.d(TAG,"------------setIntentRedelivery");
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"------------onCreate");
        b1 = true;
        b2 = true;
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"------------onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"------------onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"------------onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"------------onBind");
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent.getStringExtra("tag") != null){
            switch (intent.getStringExtra("tag")){
                case "1":
                    while (b1){
                        if(index == 10){
                            b1 = false;
                            break;
                        }else{
                            Log.d(TAG,"index-----=====:"+index++);
                        }
                    }
                    break;
                case "2":
                    while (b2){
                        if(index1 == 10){
                            b2 = false;
                            break;
                        }else{
                            Log.d(TAG,"index1-----=====:"+index1++);
                        }
                    }
                    break;
            }
        }
        Log.d(TAG,"------------onHandleIntent");
    }
}
