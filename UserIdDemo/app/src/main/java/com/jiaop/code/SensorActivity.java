package com.jiaop.code;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkteco.android.biometric.core.device.ParameterHelper;
import com.zkteco.android.biometric.core.device.TransportType;
import com.zkteco.android.biometric.core.utils.LogHelper;
import com.zkteco.android.biometric.core.utils.ToolUtils;
import com.zkteco.android.biometric.module.fingerprintreader.FingerprintCaptureListener;
import com.zkteco.android.biometric.module.fingerprintreader.FingerprintSensor;
import com.zkteco.android.biometric.module.fingerprintreader.FingprintFactory;
import com.zkteco.android.biometric.module.fingerprintreader.ZKFingerService;
import com.zkteco.android.biometric.module.fingerprintreader.exception.FingerprintException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SensorActivity extends AppCompatActivity {
    private static final int VID = 6997;
    private static final int PID = 288;
    private TextView textView = null;
    private ImageView imageView = null;
    private boolean bstart = false;
    private boolean isRegister = false;
    private int uid = 1;
    private byte[][] regtemparray = new byte[3][2048];  //register template buffer array
    private int enrollidx = 0;
    private byte[] lastRegTemp = new byte[2048];

    private FingerprintSensor fingerprintSensor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);

        startFingerprintSensor();
    }


    private void startFingerprintSensor() {
        // Define output log level
        LogHelper.setLevel(Log.WARN);
        // Start fingerprint sensor
        Map fingerprintParams = new HashMap();
        //set vid
        fingerprintParams.put(ParameterHelper.PARAM_KEY_VID, VID);
        //set pid
        fingerprintParams.put(ParameterHelper.PARAM_KEY_PID, PID);
        fingerprintSensor = FingprintFactory.createFingerprintSensor(this, TransportType.USB, fingerprintParams);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveBitmap(Bitmap bm) {
        File f = new File("/sdcard/fingerprint", "test.bmp");
        if (f.exists()) {
            f.delete();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void OnBnBegin(View view) throws FingerprintException
    {
        try {
            if (bstart) return;
            fingerprintSensor.open(0);
            final FingerprintCaptureListener listener = new FingerprintCaptureListener() {
                @Override
                public void captureOK(final byte[] fpImage) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(null != fpImage)
                            {
                                ToolUtils.outputHexString(fpImage);
                                LogHelper.i("width=" + fingerprintSensor.getImageWidth() + "\nHeight=" + fingerprintSensor.getImageHeight());
                                Bitmap bitmapFp = ToolUtils.renderCroppedGreyScaleBitmap(fpImage, fingerprintSensor.getImageWidth(), fingerprintSensor.getImageHeight());
                                //saveBitmap(bitmapFp);
                                imageView.setImageBitmap(bitmapFp);
                            }
                            //textView.setText("FakeStatus:" + fingerprintSensor.getFakeStatus());
                        }
                    });
                }
                @Override
                public void captureError(FingerprintException e) {
                    final FingerprintException exp = e;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogHelper.d("captureError  errno=" + exp.getErrorCode() +
                                    ",Internal error code: " + exp.getInternalErrorCode() + ",message=" + exp.getMessage());
                        }
                    });
                }
                @Override
                public void extractError(final int err)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("extract fail, errorcode:" + err);
                        }
                    });
                }

                @Override
                public void extractOK(final byte[] fpTemplate)
                {
                    final byte[] tmpBuffer = fpTemplate;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isRegister) {
                                byte[] bufids = new byte[256];
                                int ret = ZKFingerService.identify(tmpBuffer, bufids, 55, 1);
                                if (ret > 0)
                                {
                                    String strRes[] = new String(bufids).split("\t");
                                    textView.setText("the finger already enroll by " + strRes[0] + ",cancel enroll");
                                    isRegister = false;
                                    enrollidx = 0;
                                    return;
                                }
                                if (enrollidx > 0 && ZKFingerService.verify(regtemparray[enrollidx-1], tmpBuffer) <= 0)
                                {
                                    textView.setText("please press the same finger 3 times for the enrollment");
                                    return;
                                }
                                System.arraycopy(tmpBuffer, 0, regtemparray[enrollidx], 0, 2048);
                                enrollidx++;
                                if (enrollidx == 3) {
                                    byte[] regTemp = new byte[2048];
                                    if (0 < (ret = ZKFingerService.merge(regtemparray[0], regtemparray[1], regtemparray[2], regTemp))) {
                                        ZKFingerService.save(regTemp, "test" + uid++);
                                        System.arraycopy(regTemp, 0, lastRegTemp, 0, ret);
                                        //Base64 Template
                                        String strBase64 = Base64.encodeToString(regTemp, 0, ret, Base64.NO_WRAP);
                                        textView.setText("enroll succ");
                                    } else {
                                        textView.setText("enroll fail");
                                    }
                                    isRegister = false;
                                } else {
                                    textView.setText("You need to press the " + (3 - enrollidx) + "time fingerprint");
                                }
                            } else {
                                byte[] bufids = new byte[256];
                                int ret = ZKFingerService.identify(tmpBuffer, bufids, 55, 1);
                                if (ret > 0) {
                                    String strRes[] = new String(bufids).split("\t");
                                    textView.setText("identify succ, userid:" + strRes[0] + ", score:" + strRes[1]);
                                } else {
                                    textView.setText("identify fail");
                                }
                                //Base64 Template
                                //String strBase64 = Base64.encodeToString(tmpBuffer, 0, fingerprintSensor.getLastTempLen(), Base64.NO_WRAP);
                            }
                        }
                    });
                }


            };
            fingerprintSensor.setFingerprintCaptureListener(0, listener);
            fingerprintSensor.startCapture(0);
            bstart = true;
            textView.setText("start capture succ");
        }catch (FingerprintException e)
        {
            textView.setText("begin capture fail.errorcode:"+ e.getErrorCode() + "err message:" + e.getMessage() + "inner code:" + e.getInternalErrorCode());
        }
    }

    public void OnBnStop(View view) throws FingerprintException
    {
        try {
            if (bstart)
            {
                //stop capture
                fingerprintSensor.stopCapture(0);
                bstart = false;
                fingerprintSensor.close(0);
                textView.setText("stop capture succ");
            }
            else
            {
                textView.setText("already stop");
            }
        } catch (FingerprintException e) {
            textView.setText("stop fail, errno=" + e.getErrorCode() + "\nmessage=" + e.getMessage());
        }
    }

    public void OnBnEnroll(View view) {
        if (bstart) {
            isRegister = true;
            enrollidx = 0;
            textView.setText("You need to press the 3 time fingerprint");
        }
        else
        {
            textView.setText("please begin capture first");
        }
    }

    public void OnBnVerify(View view) {
        if (bstart) {
            isRegister = false;
            enrollidx = 0;
        }else {
            textView.setText("please begin capture first");
        }
    }

}
