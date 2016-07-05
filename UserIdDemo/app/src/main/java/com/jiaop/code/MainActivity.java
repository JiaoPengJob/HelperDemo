package com.jiaop.code;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zkteco.android.IDReader.WLTService;
import com.zkteco.android.biometric.core.device.ParameterHelper;
import com.zkteco.android.biometric.core.device.TransportType;
import com.zkteco.android.biometric.module.idcard.IDCardReader;
import com.zkteco.android.biometric.module.idcard.IDCardReaderFactory;
import com.zkteco.android.biometric.module.idcard.exception.IDCardReaderException;
import com.zkteco.android.biometric.module.idcard.meta.IDCardInfo;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int VID = 1024;
    private static final int PID = 50010;
    private IDCardReader idCardReader;

    private TextView tvShowRead;
    private TextView status;
    private Button start;
    private Button open;
    private Button close;
    private Button getSamID;
    private Button findCard;
    private Button selectCard;
    private Button readCard;
    private Button next;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShowRead = (TextView) findViewById(R.id.tvShowRead);

        start = (Button) findViewById(R.id.start);
        open = (Button) findViewById(R.id.open);
        close = (Button) findViewById(R.id.close);
        getSamID = (Button) findViewById(R.id.getSamID);
        findCard = (Button) findViewById(R.id.findCard);
        selectCard = (Button) findViewById(R.id.selectCard);
        readCard = (Button) findViewById(R.id.readCard);
        status = (TextView) findViewById(R.id.status);
        next = (Button) findViewById(R.id.next);
        photo = (ImageView) findViewById(R.id.photo);

        start.setOnClickListener(this);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        getSamID.setOnClickListener(this);
        findCard.setOnClickListener(this);
        selectCard.setOnClickListener(this);
        readCard.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                start();
                break;
            case R.id.open:
                open();
                break;
            case R.id.close:
                close();
                break;
            case R.id.getSamID:
                getSamID();
                break;
            case R.id.findCard:
                findCard();
                break;
            case R.id.selectCard:
                selectCard();
                break;
            case R.id.readCard:
                readCard();
                break;
            case R.id.next:
                Intent intent = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void start() {
        Map idrparams = new HashMap();
        idrparams.put(ParameterHelper.PARAM_KEY_VID, VID);
        idrparams.put(ParameterHelper.PARAM_KEY_PID, PID);
        idCardReader = IDCardReaderFactory.createIDCardReader(this, TransportType.USB, idrparams);
    }

    private void open() {

        try {
            idCardReader.open(0);
            status.setText(idCardReader.getStatus(0) + "");
            Toast.makeText(MainActivity.this, "连接设备成功!", Toast.LENGTH_SHORT).show();
        } catch (IDCardReaderException e) {
            Toast.makeText(MainActivity.this, "连接设备失败!", Toast.LENGTH_SHORT).show();
        }

    }

    private void close() {

        try {
            idCardReader.close(0);
            status.setText(idCardReader.getStatus(0) + "");
            Toast.makeText(MainActivity.this, "关闭设备成功!", Toast.LENGTH_SHORT).show();
        } catch (IDCardReaderException e) {
            Toast.makeText(MainActivity.this, "关闭设备失败!", Toast.LENGTH_SHORT).show();
        }

    }

    private void getSamID() {
        try {
            String samid = idCardReader.getSAMID(0);
            status.setText(idCardReader.getStatus(0) + "");
            Toast.makeText(MainActivity.this, "获取SamID为:" + samid, Toast.LENGTH_SHORT).show();
            Log.d("TAG", "获取SamID为:" + samid);
        } catch (IDCardReaderException e) {
            Toast.makeText(MainActivity.this, "获取samid失败!", Toast.LENGTH_SHORT).show();
        }
    }

    private void findCard() {
        try {
            idCardReader.findCard(0);
            status.setText(idCardReader.getStatus(0) + "");
            Toast.makeText(MainActivity.this, "寻卡成功!", Toast.LENGTH_SHORT).show();
        } catch (IDCardReaderException e) {
            Toast.makeText(MainActivity.this, "寻卡失败!", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectCard() {
        try {
            idCardReader.selectCard(0);
            status.setText(idCardReader.getStatus(0) + "");
            Toast.makeText(MainActivity.this, "选卡成功!", Toast.LENGTH_SHORT).show();
        } catch (IDCardReaderException e) {
            Toast.makeText(MainActivity.this, "选卡失败!", Toast.LENGTH_SHORT).show();
        }
    }

    public void readCard() {
        try {
            Log.d("TAG", "进入图片0");
            IDCardInfo idCardInfo = new IDCardInfo();
            Log.d("TAG", "进入图片1");
            status.setText(idCardReader.getStatus(0) + "=------readCard");
            Log.d("TAG", "进入图片2");
            boolean ret = idCardReader.readCard(0, 1, idCardInfo);
//            Log.d("TAG", "进入图片3ret:" + ret);
            Log.d("TAG", "进入图片3");
            if (ret) {
                Log.d("TAG", "进入图片4");
                tvShowRead.setText("姓名:" + idCardInfo.getName() + ",民族:" + idCardInfo.getNation() + ",住址:" + idCardInfo.getAddress() + ",身份证号:" + idCardInfo.getId());
                Log.d("TAG", "进入图片5");
                if (idCardInfo.getPhotolength() > 0) {
                    Log.d("TAG", "进入图片6");
                    byte[] buf = new byte[WLTService.imgLength];
                    if (1 == WLTService.wlt2Bmp(idCardInfo.getPhoto(), buf)) {
                        Log.d("TAG", "进入图片7");
                        photo.setImageBitmap(IDPhotoHelper.Bgr2Bitmap(buf));
                    }
                }
            }
        } catch (IDCardReaderException e) {
            Log.e("Error", "读取失败!" + e.toString());
            Toast.makeText(MainActivity.this, "读取失败!" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
